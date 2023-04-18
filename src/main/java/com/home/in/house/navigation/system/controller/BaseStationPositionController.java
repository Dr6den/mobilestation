package com.home.in.house.navigation.system.controller;

import com.home.in.house.navigation.system.controller.response.BaseStationResponse;
import com.home.in.house.navigation.system.exception.BaseStationNotFoundException;
import com.home.in.house.navigation.system.service.BaseStationPositionService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base-station")
public class BaseStationPositionController {
    @Autowired
    BaseStationPositionService baseStationPositionService;
    
    @GetMapping("/{id}")
    public BaseStationResponse getMobileStationPositionById(@PathVariable UUID id) throws BaseStationNotFoundException {
        return baseStationPositionService.getBaseStationReportById(id);
    }
}
