package com.home.in.house.navigation.system.controller;

import com.home.in.house.navigation.system.controller.response.MobileStationPositionResponse;
import com.home.in.house.navigation.system.exception.MobileStationNotFoundException;
import com.home.in.house.navigation.system.service.MobileStationPositionService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class MobileStationPositionController {
    @Autowired
    MobileStationPositionService mobileStationPositionService;
    
    @GetMapping("/{id}")
    public MobileStationPositionResponse getMobileStationPositionById(@PathVariable UUID id) throws MobileStationNotFoundException {
        return mobileStationPositionService.getMobileStationPositionById(id);
    }
}
