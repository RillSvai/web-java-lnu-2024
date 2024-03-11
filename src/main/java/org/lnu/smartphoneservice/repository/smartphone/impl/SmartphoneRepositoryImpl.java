package org.lnu.smartphoneservice.repository.smartphone.impl;

import lombok.AllArgsConstructor;
import org.lnu.smartphoneservice.entity.smartphone.SmartphoneEntity;
import org.lnu.smartphoneservice.repository.smartphone.SmartphoneRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SmartphoneRepositoryImpl implements SmartphoneRepository {
    private static String SELECT_SMARTPHONES_QUERY = """
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
        return jdbcTemplate.query(SELECT_SMARTPHONES_QUERY, SMARTPHONE_ENTITY_ROW_MAPPER);
    }
}
