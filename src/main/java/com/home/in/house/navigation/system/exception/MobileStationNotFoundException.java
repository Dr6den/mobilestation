package com.home.in.house.navigation.system.exception;

import java.util.UUID;

public class MobileStationNotFoundException extends Exception {
    public MobileStationNotFoundException(UUID uuid) {
        super("Mobile Station with such id " + uuid.toString() + " was not found." );
    }
}
