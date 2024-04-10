package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest
class SmartHomeSecurityModuleServiceTest {

    @Autowired
    SmartHomeSecurityModuleService smartHomeSecurityModuleService;

    @Test
    void subscribe() {
        // Assert
        assertDoesNotThrow(() -> smartHomeSecurityModuleService.subscribe(smartHomeSecurityModuleService));
    }

    @Test
    void unsubscribe() {
        // Assert
        assertDoesNotThrow(() -> smartHomeSecurityModuleService.unsubscribe(smartHomeSecurityModuleService));
    }

    @Test
    void notifyObservers() {
        // Assert
        assertDoesNotThrow(() -> smartHomeSecurityModuleService.notifyObservers("SecurityAway"));
        assertDoesNotThrow(() -> smartHomeSecurityModuleService.notifyObservers("SecurityActive"));
    }

    @Test
    void update() {
        // Assert
        assertDoesNotThrow(() -> smartHomeSecurityModuleService.update("Heating"));
    }
}