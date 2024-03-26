package dev.TeamRedDragon.SmartHomeSimulator.SimulationClock;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SimulationClockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SimulationClockService simulationClockService;

    JSONObject obj = new JSONObject();

    @BeforeEach
    public void setUp() { obj = new JSONObject();}


    @Test
    void getSimulationClockTime() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/SimClock/"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$").isString());
    }

    @Test
    void updateTimeSpeed() throws Exception {
        // Arrange
        obj.put("timeSpeed", 10);

        // Act
        ResultActions result = mockMvc.perform(post("/SimClock/UpdateTimeSpeed")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk());
    }

    @Test
    void stopSimulationClock() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(post("/SimClock/Stop"));

        // Assert
        result.andExpect(status().isOk());
    }
}