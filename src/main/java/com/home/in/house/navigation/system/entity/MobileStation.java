package com.home.in.house.navigation.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "MOBILE_STATION")
public class MobileStation {
    @Id
    @GeneratedValue(generator = "inquisitive-uuid", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "inquisitive-uuid", strategy = "com.home.in.house.navigation.system.entity.UseExistingOrGenerateIdGenerator")
    @Column(name = "id")
    private String id;
    private Float lastKnownX;
    private Float lastKnownY;

    public MobileStation() {
    }

    public MobileStation(Float lastKnownX, Float lastKnownY) {
        this.lastKnownX = lastKnownX;
        this.lastKnownY = lastKnownY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getLastKnownX() {
        return lastKnownX;
    }

    public void setLastKnownX(Float lastKnownX) {
        this.lastKnownX = lastKnownX;
    }

    public Float getLastKnownY() {
        return lastKnownY;
    }

    public void setLastKnownY(Float lastKnownY) {
        this.lastKnownY = lastKnownY;
    }

    @Override
    public String toString() {
        return "MobileStation{" + "id=" + id + ", lastKnownX=" + lastKnownX + ", lastKnownY=" + lastKnownY + '}';
    }
}
