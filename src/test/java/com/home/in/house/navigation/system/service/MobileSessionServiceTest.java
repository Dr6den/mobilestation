package com.home.in.house.navigation.system.service;

import com.home.in.house.navigation.system.controller.response.MobileStationPositionResponse;
import com.home.in.house.navigation.system.entity.BaseStation;
import com.home.in.house.navigation.system.entity.MobileStation;
import com.home.in.house.navigation.system.entity.MobileStationErrors;
import com.home.in.house.navigation.system.exception.MobileStationNotFoundException;
import com.home.in.house.navigation.system.repository.BaseStationRepository;
import com.home.in.house.navigation.system.repository.MobileStationRepository;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class MobileSessionServiceTest {
    @Autowired
    private MobileStationRepository mobileStationRepository;
    @Autowired
    private BaseStationRepository baseStationRepository;
    @Autowired
    private MobileStationPositionService mobileStationService;
    
    @Test
    public void getMobileStationPositionByIdGreenTest() throws MobileStationNotFoundException {
        String uuid = "7c72fb7c-b87d-4c7f-b8b7-3bf2419df376";
        Float mx = 50f;
        Float my = -50f;
        
        MobileStation m = new MobileStation(mx, my);
        m.setId(uuid);
        mobileStationRepository.save(m);
        
        BaseStation b = new BaseStation("Base0", 1f, 1f, 100f);
        b.setUuid(uuid);
        baseStationRepository.save(b);
        
        MobileStationPositionResponse response = mobileStationService.getMobileStationPositionById(UUID.fromString(uuid));
        
        assertNotNull(response);
        assertEquals(response.getMobileId(), UUID.fromString(uuid));
        assertEquals(response.getX(), mx);
        assertEquals(response.getY(), my);
        assertNull(response.getError_description());
        assertNull(response.getError_code());
    }
    
    @Test
    public void getMobileStationPositionByIdErrorOutOfServiceErrorTest() throws MobileStationNotFoundException {
        String uuid = "7c72fb7c-b87d-4c7f-b8b7-3bf2419df375";
        Float mx = 50f;
        Float my = 50f;
        
        MobileStation m = new MobileStation(mx, my);
        m.setId(uuid);
        mobileStationRepository.save(m);
        
        BaseStation b = new BaseStation("Base0", 1f, 1f, 40f);
        b.setUuid(uuid);
        baseStationRepository.save(b);
        
        MobileStationPositionResponse response = mobileStationService.getMobileStationPositionById(UUID.fromString(uuid));
        
        assertNotNull(response);
        assertEquals(response.getMobileId(), UUID.fromString(uuid));
        assertEquals(response.getX(), mx);
        assertEquals(response.getY(), my);
        assertEquals(response.getError_description(), MobileStationErrors._102.getErrorDescription());
        assertEquals(response.getError_code(), MobileStationErrors._102.getErrorCode());
    }
    
    @Test
    public void getMobileStationPositionByIdErrorIsOffTest() throws MobileStationNotFoundException {
        String uuid = "7c72fb7c-b87d-4c7f-b8b7-3bf2419df375";
        Float mx = null;
        Float my = 50f;
        
        MobileStation m = new MobileStation(mx, my);
        m.setId(uuid);
        mobileStationRepository.save(m);
        
        BaseStation b = new BaseStation("Base0", 1f, 1f, 100f);
        b.setUuid(uuid);
        baseStationRepository.save(b);
        
        MobileStationPositionResponse response = mobileStationService.getMobileStationPositionById(UUID.fromString(uuid));
        
        assertNotNull(response);
        assertEquals(response.getMobileId(), UUID.fromString(uuid));
        assertEquals(response.getX(), mx);
        assertEquals(response.getY(), my);
        assertEquals(response.getError_description(), MobileStationErrors._101.getErrorDescription());
        assertEquals(response.getError_code(), MobileStationErrors._101.getErrorCode());
    }
}
