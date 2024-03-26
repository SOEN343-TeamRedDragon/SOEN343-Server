package dev.TeamRedDragon.SmartHomeSimulator.TemperatureData;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TemperatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    JSONObject obj = new JSONObject();

    @BeforeEach
    public void setUp() {obj = new JSONObject();}

    @Test
    void getTemperatureFromClockAndTemperatureData() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/TemperatureController/GetOutdoorTemp"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber());

    }
}