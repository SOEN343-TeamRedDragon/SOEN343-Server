package dev.TeamRedDragon.SmartHomeSimulator.TemperatureData;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TemperatureDataServiceTest {

    @Test
    @Order(1)
    void generateTemperatureCSV() throws IOException {
        // Assert
        assertDoesNotThrow(() -> TemperatureDataService.generateTemperatureCSV(1000));
        Path path = Paths.get("src/main/resources/data/temperatureData1000.csv");
        assertTrue(Files.exists(path));
        Files.delete(path);


    }
}