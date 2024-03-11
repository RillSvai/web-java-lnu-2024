package org.lnu.smartphoneservice.repository.smartphone.impl;

import lombok.AllArgsConstructor;
import org.lnu.smartphoneservice.entity.smartphone.SmartphoneEntity;
import org.lnu.smartphoneservice.exception.DataConflictException;
import org.lnu.smartphoneservice.exception.NotFoundException;
import org.lnu.smartphoneservice.repository.smartphone.SmartphoneRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<SmartphoneEntity> findAll() {
        return jdbcTemplate
                .query(SELECT_SMARTPHONES_QUERY, SMARTPHONE_ENTITY_ROW_MAPPER);
    }

    @Override
    public SmartphoneEntity findOneById(Long id) {
        try {
            return jdbcTemplate
                    .queryForObject(SELECT_SMARTPHONE_BY_ID_QUERY, new MapSqlParameterSource("id", id), SMARTPHONE_ENTITY_ROW_MAPPER);
        }
        catch (EmptyResultDataAccessException exception) {
            throw new NotFoundException("Smartphone with id: " + id + " does not exist");
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
}
