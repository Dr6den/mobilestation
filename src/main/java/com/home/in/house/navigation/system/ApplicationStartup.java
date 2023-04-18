package com.home.in.house.navigation.system;

import com.home.in.house.navigation.system.entity.BaseStation;
import com.home.in.house.navigation.system.entity.MobileStation;
import com.home.in.house.navigation.system.repository.BaseStationRepository;
import com.home.in.house.navigation.system.repository.MobileStationRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);    
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();
    
    @Autowired
    private MobileStationRepository mobileStationRepository;
    @Autowired
    private BaseStationRepository baseStationRepository;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        seedData();
    }

    private void seedData() {
        logger.info("Start seeding initial data");
        List<UUID> uuids = null;
        List<MobileStation> mobileStations = new ArrayList<>();
        List<BaseStation> baseStations = new ArrayList<>();
        Random randomGenerator = new Random();
        
        try {
            uuids = Files.readAllLines(Paths.get("src/main/resources/uuids")).stream().map(UUID::fromString)
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            logger.error(ex.toString());
        }
        
        uuids.forEach((UUID u) -> {
            MobileStation ms = new MobileStation(randomGenerator.nextFloat(), randomGenerator.nextFloat());
            ms.setId(u.toString());
            mobileStations.add(ms);
            BaseStation bs = new BaseStation(randomString(9), randomGenerator.nextFloat(), randomGenerator.nextFloat(),
                    randomGenerator.nextFloat() / 10);
            bs.setUuid(u.toString());
            baseStations.add(bs);
        });
        
        mobileStationRepository.saveAll(mobileStations);
        baseStationRepository.saveAll(baseStations);
        
        logger.info("Finish seeding initial data");
    }
    
    private String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }
}
