package com.home.in.house.navigation.system.service;

import com.home.in.house.navigation.system.controller.response.MobileStationPositionResponse;
import com.home.in.house.navigation.system.entity.MobileStation;
import com.home.in.house.navigation.system.exception.MobileStationNotFoundException;
import com.home.in.house.navigation.system.repository.BaseStationRepository;
import com.home.in.house.navigation.system.repository.MobileStationRepository;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MobileStationPositionService {
    Logger logger = LoggerFactory.getLogger(MobileStationPositionService.class);
    
    @Autowired
    private MobileStationRepository mobileStationRepository;
    @Autowired
    private BaseStationRepository baseStationRepository;
    
    public MobileStationPositionResponse getMobileStationPositionById(UUID id) throws MobileStationNotFoundException {
        boolean isOutOfService = true;

        MobileStation ms = mobileStationRepository.findById(id.toString()).orElseThrow(() -> new MobileStationNotFoundException(id));
        if(baseStationRepository.countOfNearestStations(ms.getLastKnownX(), ms.getLastKnownY()) > 0) {
            isOutOfService = false;
        }
        
        MobileStationPositionResponse response = MobileStationPositionResponse.convertMobileStationToResponse(ms, isOutOfService);
        return response;
    }
}
