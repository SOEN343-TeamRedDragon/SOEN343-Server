package dev.TeamRedDragon.SmartHomeSimulator.Room;

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
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RoomService roomService;

    JSONObject obj = new JSONObject();
    int testId;
    List<Room> roomList;

    @BeforeEach
    public void setUp() {obj = new JSONObject();}

    @Test
    @Order(1)
    void getRooms() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/RoomController"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[?($.roomId)]").exists())
                .andExpect(jsonPath("$[?($.roomType)]").exists())
                .andExpect(jsonPath("$[?($.elementCount)]").exists());
    }

    @Test
    @Order(2)
    void getRoomById() throws Exception {
        // Arrange
        roomList = roomService.getRooms();
        testId = roomList.get(roomList.size()-1).getRoomId();
        obj.put("roomId", testId);

        // Act
        ResultActions result = mockMvc.perform(post("/RoomController/GetById")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value(testId));

    }

    @Test
    void toggleAllElementsInRoomByRoomIdAndElementType() throws Exception {
        // Arrange
        obj.put("roomId", 1);
        obj.put("elementType", "Light");

        JSONParser parser = new JSONParser();

        // Act
        ResultActions firstResult = mockMvc.perform(post("/RoomController/ToggleRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        String res = firstResult.andReturn().getResponse().getContentAsString();
        JSONObject json = (JSONObject) parser.parse(res);
        JSONArray innerArray = (JSONArray) json.get("smartElementList");
        JSONObject innerElement = (JSONObject) innerArray.get(2);
        Boolean isOpen = Boolean.valueOf(innerElement.get("isOpen").toString());
        firstResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.smartElementList[?(@.elementType=='Light')].elementType").value(obj.get("elementType")))
                .andExpect(jsonPath("$.smartElementList[?(@.elementType=='Light')].isOpen")
                        .value(isOpen));

        // Act
        ResultActions secondResult = mockMvc.perform(post("/RoomController/ToggleRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        isOpen = !isOpen;
        secondResult.andExpect(status().isOk())
                .andExpect(jsonPath("$.smartElementList[?(@.elementType=='Light')].isOpen").value(isOpen));

    }

    @Test
    void changeUserLocationByUserNameAndRoomId() {
    }

    @Test
    void removeUserFromRoomByRoomIdAndUserName() {

    }

    @Test
    void addUserToRoomByRoomIdAndUserName() {
    }
}