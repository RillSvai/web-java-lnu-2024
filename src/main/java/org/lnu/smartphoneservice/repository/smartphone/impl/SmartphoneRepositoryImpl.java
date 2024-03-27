package org.lnu.smartphoneservice.repository.smartphone.impl;

import lombok.AllArgsConstructor;
import org.lnu.smartphoneservice.annotation.TrackExecution;
import org.lnu.smartphoneservice.dto.smartphone.SmartphonePatch;
import org.lnu.smartphoneservice.dto.smartphone.query.params.SmartphoneFilterOptions;
import org.lnu.smartphoneservice.entity.smartphone.SmartphoneEntity;
import org.lnu.smartphoneservice.exception.DataConflictException;
import org.lnu.smartphoneservice.exception.NotFoundException;
import org.lnu.smartphoneservice.repository.smartphone.SmartphoneRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@TrackExecution
@Repository
@AllArgsConstructor
public class SmartphoneRepositoryImpl implements SmartphoneRepository {
    private static final String SELECT_SMARTPHONES_QUERY= """
            SELECT
                id,
                brand,
                model,
                os,
                screen_size,
                resolution,
                cpu_model,
                ram,
                storage,
                battery_capacity,
                camera_resolution,
                nfc_support
            FROM smartphones
            """;
    private static final String SELECT_SMARTPHONE_BY_ID_QUERY= """
            SELECT
                id,
                brand,
                model,
                os,
                screen_size,
                resolution,
                cpu_model,
                ram,
                storage,
                battery_capacity,
                camera_resolution,
                nfc_support
            FROM smartphones
            WHERE id = :id
            """;
    
    private static final String INSERT_SMARTPHONE_QUERY = """
            INSERT INTO smartphones 
            (
                brand,
                model,
                os,
                screen_size,
                resolution,
                cpu_model,
                ram,
                storage,
                battery_capacity,
                camera_resolution,
                nfc_support
           )
           VALUES 
           (
                :brand,
                :model,
                :os,
                :screen_size,
                :resolution,
                :cpu_model,
                :ram,
                :storage,
                :battery_capacity,
                :camera_resolution,
                :nfc_support
           )
            """;

    private static final String UPDATE_SMARTPHONE_BY_ID_QUERY= """
            UPDATE smartphones SET
                brand = :brand,
                model = :model,
                os = :os,
                screen_size = :screen_size,
                resolution = :resolution,
                cpu_model = :cpu_model,
                ram = :ram,
                storage = :storage,
                battery_capacity = :battery_capacity,
                camera_resolution = :camera_resolution,
                nfc_support = :nfc_support
            WHERE id = :id
            """;
    
    private static final String DELETE_SMARTPHONE_BY_ID_QUERY= """
            DELETE FROM smartphones
            WHERE id = :id
            """;

    private static final String PATCH_SMARTPHONE_BY_ID_QUERY_TEMPLATE= """
            UPDATE smartphones SET
                %s
            WHERE id = :id
            """;
    private static final String SELECT_SMARTPHONES_COUNT_QUERY = """
            SELECT COUNT(1) FROM smartphones
            """;
    
    private static final RowMapper<SmartphoneEntity> SMARTPHONE_ENTITY_ROW_MAPPER
        = (rs, rowNumber) -> {
        SmartphoneEntity smartphoneEntity = new SmartphoneEntity();
        smartphoneEntity.setId(rs.getObject("id", Long.class));
        smartphoneEntity.setBrand(rs.getString("brand"));
        smartphoneEntity.setModel(rs.getString("model"));
        smartphoneEntity.setOs(rs.getString("os"));
        smartphoneEntity.setScreenSize(rs.getObject("screen_size", Double.class));
        smartphoneEntity.setResolution(rs.getString("resolution"));
        smartphoneEntity.setCpuModel(rs.getString("cpu_model"));
        smartphoneEntity.setRam(rs.getObject("ram", Integer.class));
        smartphoneEntity.setStorage(rs.getObject("storage", Integer.class));
        smartphoneEntity.setBatteryCapacity(rs.getObject("battery_capacity", Integer.class));
        smartphoneEntity.setCameraResolution(rs.getString("camera_resolution"));
        smartphoneEntity.setNfcSupport(rs.getObject("nfc_support", Boolean.class));
        return smartphoneEntity;
    };
    private final NamedParameterJdbcTemplate jdbcTemplate;
    
    @Override
    public List<SmartphoneEntity> findAll(SmartphoneFilterOptions filterOptions, Integer limit, Integer offset) {
        StringBuilder queryBuilder = new StringBuilder(SELECT_SMARTPHONES_QUERY);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        
        appendConditions(queryBuilder, parameters, filterOptions);
        
        if (limit != null) {
            queryBuilder.append(" LIMIT :limit");
            parameters.addValue("limit", limit);
        }
        
        if(offset != null && offset != 0) {
            queryBuilder.append(" OFFSET :offset ");
            parameters.addValue("offset", offset);
        }
        
        String query = queryBuilder.toString();
        
        return jdbcTemplate
                .query(query, parameters, SMARTPHONE_ENTITY_ROW_MAPPER);
    }

    @Override
    public Integer count(SmartphoneFilterOptions filterOptions) {
        StringBuilder queryBuilder = new StringBuilder(SELECT_SMARTPHONES_COUNT_QUERY);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        
        appendConditions(queryBuilder, parameters, filterOptions);
        
        String query = queryBuilder.toString();
        
        return jdbcTemplate.queryForObject(query, parameters, Integer.class);
    }

    @Override
    public SmartphoneEntity findOneById(Long id) {
        try {
            return jdbcTemplate
                    .queryForObject(SELECT_SMARTPHONE_BY_ID_QUERY, new MapSqlParameterSource("id", id), SMARTPHONE_ENTITY_ROW_MAPPER);
        }
        catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("Smartphone with id " + id + " does not exist");
        }
        
    }

    @Override
    public SmartphoneEntity create(SmartphoneEntity smartphoneEntity) {
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("brand", smartphoneEntity.getBrand())
                .addValue("model", smartphoneEntity.getModel())
                .addValue("os", smartphoneEntity.getOs())
                .addValue("screen_size", smartphoneEntity.getScreenSize())
                .addValue("resolution", smartphoneEntity.getResolution())
                .addValue("cpu_model", smartphoneEntity.getCpuModel())
                .addValue("ram", smartphoneEntity.getRam())
                .addValue("storage", smartphoneEntity.getStorage())
                .addValue("battery_capacity", smartphoneEntity.getBatteryCapacity())
                .addValue("camera_resolution", smartphoneEntity.getCameraResolution())
                .addValue("nfc_support", smartphoneEntity.isNfcSupport());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        try {
            jdbcTemplate.update(INSERT_SMARTPHONE_QUERY, sqlParameters, keyHolder);
        }
        catch (DuplicateKeyException exception) {
            throw new DataConflictException(String.format("Smartphone with model '%s' already exist", smartphoneEntity.getModel()));
        }
       
        
        Long id = (Long) keyHolder.getKeys().get("id");
        smartphoneEntity.setId(id);
        
        return smartphoneEntity;
    }

    @Override
    public void update(SmartphoneEntity smartphoneEntity) {
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource()
                .addValue("id", smartphoneEntity.getId())
                .addValue("brand", smartphoneEntity.getBrand())
                .addValue("model", smartphoneEntity.getModel())
                .addValue("os", smartphoneEntity.getOs())
                .addValue("screen_size", smartphoneEntity.getScreenSize())
                .addValue("resolution", smartphoneEntity.getResolution())
                .addValue("cpu_model", smartphoneEntity.getCpuModel())
                .addValue("ram", smartphoneEntity.getRam())
                .addValue("storage", smartphoneEntity.getStorage())
                .addValue("battery_capacity", smartphoneEntity.getBatteryCapacity())
                .addValue("camera_resolution", smartphoneEntity.getCameraResolution())
                .addValue("nfc_support", smartphoneEntity.isNfcSupport());
        int affectedRows = 0;
        try {
            affectedRows = jdbcTemplate.update(UPDATE_SMARTPHONE_BY_ID_QUERY, sqlParameters);
        }
        catch (DuplicateKeyException exception) {
            throw new DataConflictException(String.format("Smartphone with model '%s' already exist", smartphoneEntity.getModel()));
        }
        if (affectedRows == 0) {
            throw new NotFoundException("Smartphone with id " + smartphoneEntity.getId() + " does not exist");
        }
        
    }

    @Override
    public void patch(Long id, SmartphonePatch smartphonePatch) {
        List<String> assignments = new ArrayList<>();
        MapSqlParameterSource sqlParameters = new MapSqlParameterSource("id", id);
        
        if (smartphonePatch.isBrandUpdated()) {
            assignments.add("brand = :brand");
            sqlParameters.addValue("brand", smartphonePatch.getBrand());
        }
        if (smartphonePatch.isModelUpdated()) {
            assignments.add("model = :model");
            sqlParameters.addValue("model", smartphonePatch.getModel());
        }
        if(smartphonePatch.isOsUpdated()) {
            assignments.add("os = :os");
            sqlParameters.addValue("os", smartphonePatch.getOs());
        }
        if(smartphonePatch.isScreenSizeUpdated()) {
            assignments.add("screen_size = :screen_size");
            sqlParameters.addValue("screen_size", smartphonePatch.getScreenSize());
        }
        if (smartphonePatch.isResolutionUpdated()) {
            assignments.add("resolution = :resolution");
            sqlParameters.addValue("resolution", smartphonePatch.getResolution());
        }
        if (smartphonePatch.isCpuModelUpdated()) {
            assignments.add("cpu_model = :cpu_model");
            sqlParameters.addValue("cpu_model", smartphonePatch.getModel());
        }
        if(smartphonePatch.isRamUpdated()) {
            assignments.add("ram = :ram");
            sqlParameters.addValue("ram", smartphonePatch.getRam());
        }
        if(smartphonePatch.isStorageUpdated()) {
            assignments.add("storage = :storage");
            sqlParameters.addValue("storage", smartphonePatch.getStorage());
        }
        if(smartphonePatch.isBatteryCapacityUpdated()) {
            assignments.add("battery_capacity = :battery_capacity");
            sqlParameters.addValue("battery_capacity", smartphonePatch.getBatteryCapacity());
        }
        if(smartphonePatch.isCameraResolutionUpdated()) {
            assignments.add("camera_resolution = :camera_resolution");
            sqlParameters.addValue("camera_resolution", smartphonePatch.getCameraResolution());
        }
        if(smartphonePatch.isNfcSupportUpdated()) {
            assignments.add("nfc_support = :nfc_support");
            sqlParameters.addValue("nfc_support", smartphonePatch.getNfcSupport());
        }
        String assigmentStr = String.join(",", assignments);
        String query = String.format(PATCH_SMARTPHONE_BY_ID_QUERY_TEMPLATE, assigmentStr);

        int affectedRows = 0;
        try {
            affectedRows = jdbcTemplate.update(query, sqlParameters);
        }
        catch (DuplicateKeyException exception) {
            throw new DataConflictException(String.format("Smartphone with model '%s' already exist", smartphonePatch.getModel()));
        }
        if (affectedRows == 0) {
            throw new NotFoundException("Smartphone with id " + id + " does not exist");
        }
        
    }

    @Override
    public void delete(Long id) {
        int affectedRows = jdbcTemplate
                .update(DELETE_SMARTPHONE_BY_ID_QUERY, 
                        new MapSqlParameterSource().addValue("id", id));
        
        if (affectedRows == 0) {
            throw new NotFoundException("Smartphone with id " + id + " does not exist");
        }
    }

    private void appendConditions(StringBuilder queryBuilder, MapSqlParameterSource parameters, SmartphoneFilterOptions filterOptions) {
        List<String> conditions = new ArrayList<>();

        String brandParam = filterOptions.getBrand();
        if (brandParam != null) {
            conditions.add("brand LIKE(:brand)");
            parameters.addValue("brand", "%" + brandParam + "%");
        }

        String modelParam = filterOptions.getModel();
        if (modelParam != null) {
            conditions.add("model LIKE(:model)");
            parameters.addValue("model", "%" + modelParam + "%");
        }
        
        Integer ram = filterOptions.getRam();
        if (ram != null) {
            conditions.add("ram = :ram");
            parameters.addValue("ram", ram);
        }
        
        Integer storage = filterOptions.getStorage();
        if (storage != null) {
            conditions.add("storage = :storage");
            parameters.addValue("storage", storage);
        }
        
        Integer batteryCapacity = filterOptions.getBatteryCapacity();
        if(batteryCapacity != null) {
            conditions.add("battery_capacity = :battery_capacity");
            parameters.addValue("battery_capacity", batteryCapacity);
        }
            
        if (!conditions.isEmpty()) {
            String conditionStr = String.join(" AND ",  conditions);

            queryBuilder.append(" WHERE ");
            queryBuilder.append(conditionStr);
        }
    }
}
