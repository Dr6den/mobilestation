package com.home.in.house.navigation.system.controller.response;

import com.home.in.house.navigation.system.entity.MobileStation;
import com.home.in.house.navigation.system.entity.MobileStationErrors;
import java.util.UUID;

public class MobileStationPositionResponse {
    private UUID mobileId;
    private Float x;
    private Float y;
    private Float error_radius;
    private Integer error_code;
    private String error_description;

    public MobileStationPositionResponse(UUID mobileId, Float x, Float y) {
        this.mobileId = mobileId;
        this.x = x;
        this.y = y;
    }

    public MobileStationPositionResponse() {
    }
    
    public static MobileStationPositionResponse convertMobileStationToResponse(MobileStation ms, boolean isOutOfService) {
        MobileStationPositionResponse response = new MobileStationPositionResponse(UUID.fromString(ms.getId()),
                ms.getLastKnownX(),ms.getLastKnownY());
        
        if(ms.getLastKnownX() == null || ms.getLastKnownY() == null) {
            response.setError_code(MobileStationErrors._101.getErrorCode());
            response.setError_description(MobileStationErrors._101.getErrorDescription());
            return response;
        }
        
        if(isOutOfService) {
            response.setError_code(MobileStationErrors._102.getErrorCode());
            response.setError_description(MobileStationErrors._102.getErrorDescription());
        }
        
        return response;
    }

    public UUID getMobileId() {
        return mobileId;
    }

    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public Float getError_radius() {
        return error_radius;
    }

    public Integer getError_code() {
        return error_code;
    }

    public String getError_description() {
        return error_description;
    }

    public void setMobileId(UUID mobileId) {
        this.mobileId = mobileId;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public void setError_radius(Float error_radius) {
        this.error_radius = error_radius;
    }

    public void setError_code(Integer error_code) {
        this.error_code = error_code;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }
    
}
