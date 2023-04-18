package com.home.in.house.navigation.system.repository;

import com.home.in.house.navigation.system.entity.MobileStation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileStationRepository extends JpaRepository<MobileStation, String> {
    @Query("SELECT m FROM MobileStation m WHERE (m.lastKnownX - :xo) * (m.lastKnownX - :xo) "
            + "+ (m.lastKnownY - :yo) * (m.lastKnownY - :yo) < :radius * :radius")
    List<MobileStation> getAvailiableMobileStationsToBaseStation(Float xo, Float yo, Float radius);
}
