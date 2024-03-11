package dev.TeamRedDragon.SmartHomeSimulator.Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


@Service
public class JsonFileService {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public JsonFileService(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    public Home readJsonFile() {
        try (InputStream inputStream = resourceLoader.getResource("classpath:data/simpleHome.json").getInputStream()) {
            return objectMapper.readValue(inputStream, Home.class);
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file", e);
        }
    }

    public static JSONObject updateHomeLayout() {
        JSONObject obj = new JSONObject();
        Home home = Home.getHome();

        obj.put("homeId", home.getHomeId());
        obj.put("homeType", "simpleHome");
        obj.put("roomCount", home.getRoomCount());

        JSONArray roomJArray = new JSONArray();

        ArrayList<Room> roomList = home.getRoomList();

        for (Room room : roomList)
        {
            JSONObject roomObj = new JSONObject();

            roomObj.put("roomId", room.getRoomId());
            roomObj.put("roomType", room.getRoomType());
            roomObj.put("elementCount", room.getElementCount());

            JSONArray smartElementJArray = new JSONArray();

            ArrayList<SmartElement> smartElementList = room.getSmartElementList();

            for (SmartElement smartElement : smartElementList)
            {
                JSONObject smartElementObj = new JSONObject();

                smartElementObj.put("classType", smartElement.getElementType());
                smartElementObj.put("elementId", smartElement.getElementId());
                smartElementObj.put("open", smartElement.getIsOpen());

                smartElementJArray.add(smartElementObj);
            }

            roomObj.put("smartElements", smartElementJArray);
            roomJArray.add(roomObj);
        }
        obj.put("roomList", roomJArray);
        System.out.println(obj.toJSONString());
        return obj;
    }
}

