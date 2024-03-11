package org.lnu.smartphoneservice.dto.smartphone;

import lombok.Data;

@Data
public class BaseSmartphoneDto {
    private String brand;
    private String model;
    private String os;
    private double screenSize;
    private String resolution;
    private String cpuModel;
    private int ram; 
    private int storage;
    private int batteryCapacity;
    private String cameraResolution;
    private boolean nfcSupport;
}
