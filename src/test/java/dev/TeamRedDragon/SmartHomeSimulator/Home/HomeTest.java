package dev.TeamRedDragon.SmartHomeSimulator.Home;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HomeTest {
    static Home home = Home.getHome();

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

    @Test
    @Order(4)
    void testSetHomeType() {
        // Assert
        assertDoesNotThrow(() -> home.setHomeType("Test"));
    }

    @Test
    @Order(5)
    void testGetHomeType(){
        // Assert
        assertDoesNotThrow(() -> home.getHomeType());
        assertEquals("Test", home.getHomeType());
    }

    @Test
    @Order(6)
    void testGetRoomById() {
        // Assert
        assertDoesNotThrow(() -> home.getRoomById(1));
        assertNull(home.getRoomById(9999));
    }

    @AfterAll
    static void tearDown() {
        home.setHomeType("simpleHome");
    }

}