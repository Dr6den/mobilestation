package com.home.in.house.navigation.system.exception;

import java.util.UUID;

public class BaseStationNotFoundException extends Exception {
    public BaseStationNotFoundException(UUID uuid) {
        super("Base Station with such id " + uuid.toString() + " was not found." );
    }
}
