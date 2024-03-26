package dev.TeamRedDragon.SmartHomeSimulator.Home;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HomeTest {
    Home home = Home.getHome();

    @Test
    @Order(1)
    void setHomeId() {
        // Assert
        assertDoesNotThrow(() -> home.setHomeId(2));
        assertDoesNotThrow(() -> home.setHomeId(1));
    }

    @Test
    @Order(2)
    void getHomeId() {
        // Assert
        assertEquals(1, home.getHomeId());

    }

    @Test
    @Order(3)
    void testToString() {
        // Assert
        assertDoesNotThrow(() -> home.toString());
    }
}