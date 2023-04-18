package com.home.in.house.navigation.system.service;

import com.home.in.house.navigation.system.controller.response.BaseStationReport;
import com.home.in.house.navigation.system.controller.response.BaseStationResponse;
import com.home.in.house.navigation.system.entity.BaseStation;
import com.home.in.house.navigation.system.entity.MobileStation;
import com.home.in.house.navigation.system.exception.BaseStationNotFoundException;
import com.home.in.house.navigation.system.repository.BaseStationRepository;
import com.home.in.house.navigation.system.repository.MobileStationRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseStationPositionService {
    Logger logger = LoggerFactory.getLogger(BaseStationPositionService.class);
    
    @Autowired
    private MobileStationRepository mobileStationRepository;
    @Autowired
    private BaseStationRepository baseStationRepository;
    
    public BaseStationResponse getBaseStationReportById(UUID id) throws BaseStationNotFoundException {
        
        BaseStation bs = baseStationRepository.findById(id.toString()).orElseThrow(() -> new BaseStationNotFoundException(id));
        List<MobileStation> mss = mobileStationRepository.getAvailiableMobileStationsToBaseStation(bs.getX(), bs.getY(),
                bs.getDetectionRadiusInMeters());
        
        List<BaseStationReport> reports = mss.stream().map(m -> new BaseStationReport(UUID.fromString(m.getId()),
                (float) Math.sqrt(Math.pow(m.getLastKnownX() - bs.getX(), 2) + Math.pow(m.getLastKnownY() - bs.getY(), 2))))
                .collect(Collectors.toList());
        
        BaseStationResponse response = new BaseStationResponse(UUID.fromString(bs.getUuid()),
                reports.toArray((BaseStationReport[]) new BaseStationReport[0]));
        return response;
    }
}
