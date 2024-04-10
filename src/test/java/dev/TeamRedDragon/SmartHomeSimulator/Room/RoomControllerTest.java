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
                .andExpect(jsonPath("$.smartElementList[?(@.elementType=='Light')].elementType")
                        .value(obj.get("elementType")))
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
    void turnOnAllElementsInRoomByRoomIdAndElementType() throws Exception {
        // Arrange
        obj.put("roomId", 1);
        obj.put("elementType", "Light");

        // Act
        ResultActions result = mockMvc.perform(post("/RoomController/TurnOnElement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value(1))
                .andExpect(jsonPath("$.smartElementList[?(@.elementType=='Light')].elementType")
                        .value(obj.get("elementType")))
                .andExpect(jsonPath("$.smartElementList[?(@.elementType=='Light')].isOpen")
                        .value(true));

    }

    @Test
    void turnOffAllElementsInRoomByRoomIdAndElementType() throws Exception {
        // Arrange
        obj.put("roomId", 1);
        obj.put("elementType", "Light");

        // Act
        ResultActions result = mockMvc.perform(post("/RoomController/TurnOffElement")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value(1))
                .andExpect(jsonPath("$.smartElementList[?(@.elementType=='Light')].elementType")
                        .value(obj.get("elementType")))
                .andExpect(jsonPath("$.smartElementList[?(@.elementType=='Light')].isOpen")
                        .value(false));

    }

    @Test
    void changeUserLocationByUserNameAndRoomId() throws Exception {
        // Arrange
        obj.put("oldRoomId", 1);
        obj.put("newRoomId", 2);
        obj.put("userName", "DanDuguay");

        // Act
        ResultActions result = mockMvc.perform(post("/RoomController/ChangeUserLocation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk());
    }

    @Test
    void removeUserFromRoomByRoomIdAndUserName() throws Exception {
        // Arrange
        roomService.addUserToRoomByRoomIdAndUserName(1, "DanDuguay");

        obj.put("roomId", 1);
        obj.put("userName", "DanDuguay");

        // Act
        ResultActions result = mockMvc.perform(post("/RoomController/RemoveUserFromRoom")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk());

    }

    @Test
    void addUserToRoomByRoomIdAndUserName() throws Exception {
        // Arrange
        obj.put("roomId", 1);
        obj.put("userName", "DanDuguay");

        // Act
        ResultActions result = mockMvc.perform(post("/RoomController/AddUserToRoom")
        .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk());

    }

    @Test
    void overrideRoomTemperatureByRoomId() throws Exception {
        // Arrange
        obj.put("roomId", 1);
        obj.put("roomTemp", 1);

        JSONParser parser = new JSONParser();

        // Act
        ResultActions result = mockMvc.perform(post("/RoomController/OverrideRoomTemp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath(("$.temperature")).value(1));
    }

    @Test
    void resetRoomOverrideRoomId() throws Exception {
        // Arrange
        obj.put("roomId",1);

        // Act
        ResultActions result = mockMvc.perform(post("/RoomController/ResetOverride")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk());
    }

}