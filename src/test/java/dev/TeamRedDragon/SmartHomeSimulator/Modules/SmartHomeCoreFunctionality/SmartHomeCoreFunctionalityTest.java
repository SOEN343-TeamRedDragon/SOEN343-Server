package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeCoreFunctionality;

import org.junit.jupiter.api.*;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest
@AutoConfigureMockMvc
class SmartHomeCoreFunctionalityTest {


    @Test
    void getSmartHomeCoreFunctionality() {
        // Assert
        assertDoesNotThrow(SmartHomeCoreFunctionality::getSmartHomeCoreFunctionality);
    }

    @Test
    void update() {
        // Arrange
        SmartHomeCoreFunctionality smartHomeCoreFunctionality = SmartHomeCoreFunctionality.getSmartHomeCoreFunctionality();
        // Assert
        assertDoesNotThrow(smartHomeCoreFunctionality::update);
    }
}