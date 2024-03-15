package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
class SmartElementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    JSONObject obj = new JSONObject();

    @BeforeEach
    public void setUp() {obj = new JSONObject();}
    @Test
    void getElementByRoomIdAndElementId() throws Exception {
        // Arrange
        obj.put("roomId", 1);
        obj.put("elementId", 1);

        // Act
        ResultActions result = mockMvc.perform(post("/SmartElementController/SmartElement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.elementType").exists())
                .andExpect(jsonPath("$.elementId").value(1));
    }

    @Test
    void toggleElementByRoomIdAndElementId() throws Exception {
        // Arrange
        obj.put("roomId", 1);
        obj.put("elementId", 1);
        JSONParser parser = new JSONParser();

        // Act
        ResultActions firstResult = mockMvc.perform(post("/SmartElementController/ToggleSmartElement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        String res = firstResult.andReturn().getResponse().getContentAsString();
        JSONObject json = (JSONObject) parser.parse(res);
        Boolean isOpen = Boolean.valueOf(json.get("isOpen").toString());
        firstResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.elementId").value(obj.get("elementId")))
                .andExpect(jsonPath("$.elementType").exists())
                .andExpect(jsonPath("$.isOpen").value(isOpen.toString()));

        // Act
        ResultActions secondResult = mockMvc.perform(post("/SmartElementController/ToggleSmartElement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        isOpen = !isOpen;
        secondResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.isOpen").value(isOpen.toString()));
    }
}