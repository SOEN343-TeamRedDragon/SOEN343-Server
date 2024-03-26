package dev.TeamRedDragon.SmartHomeSimulator.Zone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ZoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ZoneService zoneService;

    JSONObject obj = new JSONObject();

    @BeforeEach
    public void setUp() { obj = new JSONObject();}


    @Test
    @Order(1)
    void createZone() throws Exception {
        // Arrange
        obj.put("zone", 2);
        obj.put("AM", 5.5);
        obj.put("PM", 6.5);
        obj.put("NIGHT", 7.5);


        // Act
        ResultActions result = mockMvc.perform(post("/ZoneController/CreateZone")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.zoneId").value(2))
                .andExpect(jsonPath("$.amTemp").value(5.5))
                .andExpect(jsonPath("$.pmTemp").value(6.5))
                .andExpect(jsonPath("$.nightTemp").value(7.5));

    }

    @Test
    @Order(2)
    void addRoomToZoneByZoneIdAndRoomId() throws Exception {
        // Arrange
        obj.put("zoneId", 2);
        obj.put("roomId", 2);

        // Act
        ResultActions result = mockMvc.perform(post("/ZoneController/AddRoomToZone")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.roomList[0]").value(2));
    }

    @Test
    @Order(3)
    void getZones() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/ZoneController/GetZones"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[?($.zoneId)]").exists());

    }

    @Test
    @Order(4)
    void updateZoneTemperature() throws Exception {
        // Arrange
        obj.put("zoneId", 2);
        obj.put("timeOfDay", "PM");
        obj.put("newTemperature", 10.0);


        // Act
        ResultActions result = mockMvc.perform(put("/ZoneController/UpdateZoneTemperature")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.pmTemp").value(10.0));
    }

    @Test
    @Order(5)
    void removeRoomFromZoneByZoneIdAndRoomId() throws Exception {
        // Arrange
        obj.put("zoneId", 2);
        obj.put("roomId", 2);

        // Act
        ResultActions result = mockMvc.perform(post("/ZoneController/RemoveRoomFromZone")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk());

    }

}