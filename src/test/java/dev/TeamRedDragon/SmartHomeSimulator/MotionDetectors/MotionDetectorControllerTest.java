package dev.TeamRedDragon.SmartHomeSimulator.MotionDetectors;

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
class MotionDetectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    JSONObject obj = new JSONObject();

    @BeforeEach
    void setUp() {
        obj = new JSONObject();
    }

    @Test
    void setMotionDetectorServiceByRoomById() throws Exception {
        // Arrange
        obj.put("roomId",1);

        // Act
        ResultActions result = mockMvc.perform(post("/MotionDetectorController/AddByRoomId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.elementType")
                        .value("MotionDetector"));
    }
}