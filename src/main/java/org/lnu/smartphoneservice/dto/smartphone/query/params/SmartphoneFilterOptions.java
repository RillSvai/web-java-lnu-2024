package org.lnu.smartphoneservice.dto.smartphone.query.params;

import lombok.Data;

@Data
public class SmartphoneFilterOptions {
    private String model;
    private String brand;
    private Integer ram;
    private Integer storage;
    private Integer batteryCapacity;
}
