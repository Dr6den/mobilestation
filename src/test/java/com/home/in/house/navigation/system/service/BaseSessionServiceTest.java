package com.home.in.house.navigation.system.service;

import com.home.in.house.navigation.system.controller.response.BaseStationReport;
import com.home.in.house.navigation.system.controller.response.BaseStationResponse;
import com.home.in.house.navigation.system.entity.BaseStation;
import com.home.in.house.navigation.system.entity.MobileStation;
import com.home.in.house.navigation.system.exception.BaseStationNotFoundException;
import com.home.in.house.navigation.system.repository.BaseStationRepository;
import com.home.in.house.navigation.system.repository.MobileStationRepository;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class BaseSessionServiceTest {
    @Autowired
    private MobileStationRepository mobileStationRepository;
    @Autowired
    private BaseStationRepository baseStationRepository;
    @Autowired
    private BaseStationPositionService baseStationService;
    
    @Test
    public void getBaseStationByIdTest() throws BaseStationNotFoundException {
        String uuid1 = "7c72fb7c-b87d-4c7f-b8b7-3bf2419df373";
        String uuid2 = "6c72fb7c-b87d-4c7f-b8b7-3bf2419df373";
        Float mxIn = 70f;
        Float myIn = 70f;
        Float mxOut = 170f;
        Float myOut = 170f;
        
        MobileStation m1 = new MobileStation(mxIn, myIn);
        m1.setId(uuid1);
        mobileStationRepository.save(m1);
        
        MobileStation m2 = new MobileStation(mxOut, myOut);
        m2.setId(uuid2);
        mobileStationRepository.save(m2);
        
        BaseStation b = new BaseStation("Base0", 50f, 50f, 50f);
        b.setUuid(uuid1);
        baseStationRepository.save(b);
        
        BaseStationResponse response = baseStationService.getBaseStationReportById(UUID.fromString(uuid1));
        
        assertNotNull(response);
        assertEquals(response.getBase_station_id(), UUID.fromString(uuid1));
        
        BaseStationReport[] reports = response.getReports();
        assertNotNull(reports);
        assertEquals(reports.length, 1);
        
        BaseStationReport report = reports[0];
        assertNotNull(report.getTimestamp());
        assertEquals(report.getDistance(), 28.284271f);
        assertEquals(report.getMobile_station_id(), UUID.fromString(uuid1));
    }
}
