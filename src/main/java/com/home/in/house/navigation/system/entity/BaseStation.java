package com.home.in.house.navigation.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "BASE_STATION")
public class BaseStation {
    @Id
    @GeneratedValue(generator = "inquisitive-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "inquisitive-uuid", strategy = "com.home.in.house.navigation.system.entity.UseExistingOrGenerateIdGenerator")
    @Column(name = "id")
    private String uuid;
    private String name;
    private Float x;
    private Float y;
    private Float detectionRadiusInMeters;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getDetectionRadiusInMeters() {
        return detectionRadiusInMeters;
    }

    public void setDetectionRadiusInMeters(Float detectionRadiusInMeters) {
        this.detectionRadiusInMeters = detectionRadiusInMeters;
    }

    public BaseStation(String name, Float x, Float y, Float detectionRadiusInMeters) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.detectionRadiusInMeters = detectionRadiusInMeters;
    }

    public BaseStation() {
    }

    @Override
    public String toString() {
        return "BaseStation{" + "uuid=" + uuid + ", name=" + name + ", x=" + x + ", y=" + y + ", detectionRadiusInMeters=" 
                + detectionRadiusInMeters + '}';
    }
}
