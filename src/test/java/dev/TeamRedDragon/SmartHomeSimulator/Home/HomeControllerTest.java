package dev.TeamRedDragon.SmartHomeSimulator.Home;

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
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    JSONObject obj = new JSONObject();

    @BeforeEach
    public void setUp() {obj = new JSONObject();}

    @Test
    void getHomeLayout() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/HomeController/Home"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.homeId").exists());
    }

    @Test
    void setAllElementsStateByBooleanAndTypeTrue() throws Exception {
        // Arrange
        obj.put("isOpen", true);
        obj.put("elementType", "Light");

        // Act
        ResultActions result = mockMvc.perform(post("/HomeController/SetAllElements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.roomList[0].smartElementList[?(@.elementType=='Light')].isOpen")
                        .value(true));

    }

    @Test
    void setAllElementsStateByBooleanAndTypeFalse() throws Exception {
        // Arrange
        obj.put("isOpen", false);
        obj.put("elementType", "Light");

        // Act
        ResultActions result = mockMvc.perform(post("/HomeController/SetAllElements")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.roomList[0].smartElementList[?(@.elementType=='Light')].isOpen")
                        .value(false));

    }
}