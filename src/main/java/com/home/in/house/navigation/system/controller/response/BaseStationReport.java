package com.home.in.house.navigation.system.controller.response;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public class BaseStationReport {
    private UUID mobile_station_id;
    private Float distance;
    private Timestamp timestamp;

    public BaseStationReport() {
        this.timestamp = Timestamp.from(Instant.now());
    }

    public BaseStationReport(UUID mobile_station_id, Float distance) {
        this.mobile_station_id = mobile_station_id;
        this.distance = distance;
        this.timestamp = Timestamp.from(Instant.now());
    }

    public UUID getMobile_station_id() {
        return mobile_station_id;
    }

    public void setMobile_station_id(UUID mobile_station_id) {
        this.mobile_station_id = mobile_station_id;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
    
    
}
