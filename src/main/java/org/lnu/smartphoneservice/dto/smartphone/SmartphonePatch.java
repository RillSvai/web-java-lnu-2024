package org.lnu.smartphoneservice.dto.smartphone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class SmartphonePatch {
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
    
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean empty = true;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isBrandUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isModelUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isOsUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isScreenSizeUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isResolutionUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isCpuModelUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isRamUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isStorageUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isBatteryCapacityUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isCameraResolutionUpdated;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    private boolean isNfcSupportUpdated;

    public void setBrand(String brand) {
        empty = false;
        isBrandUpdated = true;
        this.brand = brand;
    }

    public void setModel(String model) {
        empty = false;
        isModelUpdated = true;
        this.model = model;
    }

    public void setOs(String os) {
        empty = false;
        isOsUpdated = true;
        this.os = os;
    }

    public void setScreenSize(double screenSize) {
        empty = false;
        isScreenSizeUpdated = true;
        this.screenSize = screenSize;
    }

    public void setResolution(String resolution) {
        empty = false;
        isResolutionUpdated = true;
        this.resolution = resolution;
    }

    public void setCpuModel(String cpuModel) {
        empty = false;
        isCpuModelUpdated = true;
        this.cpuModel = cpuModel;
    }

    public void setRam(int ram) {
        empty = false;
        isRamUpdated = true;
        this.ram = ram;
    }

    public void setStorage(int storage) {
        empty = false;
        isStorageUpdated = true;
        this.storage = storage;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        empty = false;
        isBatteryCapacityUpdated = true;
        this.batteryCapacity = batteryCapacity;
    }

    public void setCameraResolution(String cameraResolution) {
        empty = false;
        isCameraResolutionUpdated = true;
        this.cameraResolution = cameraResolution;
    }

    public void setNfcSupport(boolean nfcSupport) {
        empty = false;
        isNfcSupportUpdated = true;
        this.nfcSupport = nfcSupport;
    }
    
    public boolean  getNfcSupport() {
        return this.nfcSupport;
    }
}
