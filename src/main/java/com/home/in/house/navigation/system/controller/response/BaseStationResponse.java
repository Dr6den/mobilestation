package com.home.in.house.navigation.system.controller.response;

import java.util.UUID;

public class BaseStationResponse {
    private UUID base_station_id;
    private BaseStationReport[] reports;

    public BaseStationResponse() {
    }

    public BaseStationResponse(UUID base_station_id, BaseStationReport[] reports) {
        this.base_station_id = base_station_id;
        this.reports = reports;
    }

    public UUID getBase_station_id() {
        return base_station_id;
    }

    public BaseStationReport[] getReports() {
        return reports;
    }

    public void setBase_station_id(UUID base_station_id) {
        this.base_station_id = base_station_id;
    }

    public void setReports(BaseStationReport[] reports) {
        this.reports = reports;
    }

    @Override
    public String toString() {
        return "BaseStationResponse{" + "base_station_id=" + base_station_id + ", reports=" + reports + '}';
    }
    
    
}
