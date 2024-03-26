package dev.TeamRedDragon.SmartHomeSimulator.SimulationParameters;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SimulationParametersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SimulationParametersService simulationService;

    JSONObject obj = new JSONObject();
    int testId;
    List<SimulationParameters> simulationParametersList;

    @BeforeEach
    public void setUp() {obj = new JSONObject();}

    @Test
    @Order(1)
    void addSimulationParameters() throws Exception {
        // Arrange
        LocalDate date = LocalDate.now();
        obj.put("parameterId", 100);
        obj.put("insideTemperature", 24.0);
        obj.put("outsideTemperature", 10.9);
        obj.put("dateTime", date.toString());
        obj.put("timeSpeed", 1.2);


        // Act
        ResultActions result = mockMvc.perform(post("/SimulationParameters/AddSimulationParameters")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void getSimulationParameters() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/SimulationParameters/"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[?($.insideTemperature)]").exists())
                .andExpect(jsonPath("$[?($.outsideTemperature)]").exists())
                .andExpect(jsonPath("$[?($.parameterId)]").exists())
                .andExpect(jsonPath("$[?($.dateTime)]").exists())
                .andExpect(jsonPath("$[?($.timeSpeed)]").exists());
    }

    @Test
    @Order(3)
    void getSimulationParametersById() throws Exception {
        // Arrange
        simulationParametersList = simulationService.getSimulationParameters();
        testId = simulationParametersList.get(simulationParametersList.size()-1).getParameterId();
        obj.put("parameterId", testId);

        //Act
        ResultActions result = mockMvc.perform(post("/SimulationParameters/GetById")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.parameterId").value(testId));
    }

    @Test
    @Order(4)
    void updateSimulationParameter() throws Exception {
        // Arrange
        simulationParametersList = simulationService.getSimulationParameters();
        testId = simulationParametersList.get(simulationParametersList.size()-1).getParameterId();
        LocalDate date = LocalDate.now();
        obj.put("parameterId", testId);
        obj.put("insideTemperature", 37.0);
        obj.put("outsideTemperature", 37.9);
        obj.put("dateTime", date.toString());
        obj.put("timeSpeed", 3.7);

        // Act
        ResultActions result = mockMvc.perform(patch("/SimulationParameters/Update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        //Assert
        result.andExpect(status().isOk());

    }

    @Test
    @Order(5)
    void deleteSimulationParameter() throws Exception {
        // Arrange
        simulationParametersList = simulationService.getSimulationParameters();
        testId = simulationParametersList.get(simulationParametersList.size()-1).getParameterId();
        obj.put("parameterId", testId);

        // Act
        ResultActions result = mockMvc.perform(delete("/SimulationParameters/DeleteById")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk());
    }
}