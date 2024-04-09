package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest
@AutoConfigureMockMvc
class SmartHeatingModuleServiceTest {

    @Autowired
    SmartHeatingModuleService smartHeatingModuleService;

    @Test
    void updateRoomTempByOutdoorTemp() {
        // Assert
        assertDoesNotThrow(() -> smartHeatingModuleService.updateRoomTempByOutdoorTemp());
    }

    @Test
    void changeRoomTempAlgorithm() {
        // Assert
        assertDoesNotThrow(() -> smartHeatingModuleService.changeRoomTempAlgorithm());
    }

    @Test
    void update() {
        // Assert
        assertDoesNotThrow(() -> smartHeatingModuleService.update("Heating"));
    }
}