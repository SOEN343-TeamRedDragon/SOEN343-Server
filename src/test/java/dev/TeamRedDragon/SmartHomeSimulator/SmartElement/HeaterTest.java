package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HeaterTest {

    Heater heater = new Heater(1, "Heater", false);

    @Test
    @Order(1)
    void setTemperature() {
        // Assert
        assertDoesNotThrow(() -> heater.setTemperature(25));
    }

    @Test
    @Order(2)
    void getTemperature() {
        // Arrange
        heater.setTemperature(25);

        // Assert
        assertDoesNotThrow(() -> heater.getTemperature());
        assertEquals(25, heater.getTemperature());
    }

    @Test
    @Order(3)
    void testToString() {
        // Assert
        assertDoesNotThrow(() -> heater.toString());
    }
}