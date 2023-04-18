package com.home.in.house.navigation.system.entity;

public enum MobileStationErrors {
    _101("Mobile station is off"),
    _102("Mobile station is out of the nearest base station");
    
    private String errorDescription;
    
    MobileStationErrors(String errorDescription) {
        this.errorDescription = errorDescription;
    }
    
    public int getErrorCode() {
        return Integer.valueOf(this.name().replace("_", ""));
    }
    
    public String getErrorDescription() {
        return this.errorDescription;
    }
}
