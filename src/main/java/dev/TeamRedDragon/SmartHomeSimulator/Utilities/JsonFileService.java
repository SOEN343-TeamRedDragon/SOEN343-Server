package dev.TeamRedDragon.SmartHomeSimulator.Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.toIntExact;


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

    public static void updateHomeObjectFromJSON() throws IOException, ParseException {
        Home home = Home.getHome();
        ArrayList<Room> roomList = new ArrayList<>();
        int numberOfRooms;

        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("src/main/resources/data/simpleHome.json");
        Object jsonobj = parser.parse(reader);
        JSONObject data = (JSONObject) jsonobj;

        JSONArray roomArray = (JSONArray) data.get("roomList");

        numberOfRooms = toIntExact((long) data.get("roomCount"));
        home.setRoomCount(numberOfRooms);

        for (int i = 0; i < numberOfRooms; i++)
        {
            JSONObject room = (JSONObject) roomArray.get(i);
            ArrayList<SmartElement> smartElementList = new ArrayList<>();
            // Get the parameters needed by the room constructor
            int roomId = toIntExact((long) room.get("roomId"));
            String roomType = (String) room.get("roomType");
            int elementCount = toIntExact((long) room.get("elementCount"));
            JSONArray elementArray = (JSONArray) room.get("smartElements");


            for (int j = 0; j < elementCount; j++)
            {
                JSONObject smartElement = (JSONObject) elementArray.get(j);

                // Get the parameters needed by the smartElement constructor
                String classType = (String) smartElement.get("classType");
                int elementId = toIntExact((long) smartElement.get("elementId"));
                String open = (String) smartElement.get("open");
                boolean isOpen = false;
                if (Objects.equals(open, "True"))
                    isOpen = true;


                // Build the smartElement and add it to the arrayList
                switch (classType)
                {
                    case("Door"):
                        smartElementList.add(new Door(elementId, classType, isOpen));
                        break;
                    case("Window"):
                        smartElementList.add(new Window(elementId, classType, isOpen));
                        break;
                    case("Light"):
                        smartElementList.add(new Light(elementId, classType, isOpen));
                        break;
                    case("Heater"):
                        smartElementList.add(new Heater(elementId, classType, isOpen));
                        break;
                }
            }
            // Then you can build a room and feed it the smartElement arrayList just created
            roomList.add(new Room(roomId, roomType, smartElementList));

        }
        // Then you can add the roomList to the home object
        home.setRoomList(roomList);
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

