package org.lnu.smartphoneservice.entity.smartphone;

import lombok.Data;

@Data
public class SmartphoneEntity {
    private Long id;
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
