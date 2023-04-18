package com.home.in.house.navigation.system.repository;

import com.home.in.house.navigation.system.entity.BaseStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseStationRepository extends JpaRepository<BaseStation, String> {
    @Query("SELECT COUNT(b) FROM BaseStation b WHERE (:x - b.x) * (:x - b.x) + (:y - b.y) * (:y - b.y) < "
            + "b.detectionRadiusInMeters * b.detectionRadiusInMeters")
    long countOfNearestStations(Float x, Float y);
}
