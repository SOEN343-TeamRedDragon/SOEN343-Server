package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule;

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
class SmartHomeSecurityModuleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SmartHomeSecurityModuleController smartHomeSecurityModuleController;

    JSONObject obj = new JSONObject();

    @BeforeEach
    public void setUp() {obj = new JSONObject();}

    @Test
    void getSmartHomeSecurityState() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/GetState"));

        // Assert


    }

    @Test
    void turnOnAwayMode() {
    }

    @Test
    void turnOffAwayMode() {
    }
}