package dev.TeamRedDragon.SmartHomeSimulator.Home;

import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.toIntExact;

public class Home {

    private int homeId;
    private String homeType;
    private static Home home;


    private int roomCount;

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }

    private ArrayList<Room> roomList = new ArrayList<Room>();

    private Home(){}

    public static Home getHome(){
        if (home == null){
              home = new Home();
        }
        return home;
    }

    public int getHomeId() { return homeId;}

    public String getHomeType() {
        return homeType;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void setHomeId(int homeId) {
        this.homeId = homeId;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    @Override
    public String toString() {
        return "Home {" +
                "homeId = '" + homeId + '\'' +
                ", homeType = '" + homeType + '\'' +
                ", roomCount = '" + roomCount + '\'' +
                '}';
    }

    public void jsonParser() throws IOException, ParseException {
        Home home = Home.getHome();
        ArrayList<Room> roomList = new ArrayList<>();
        int numberOfRooms;

        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("C:\\Users\\Dugua\\IdeaProjects\\SOEN343-Server\\src\\main\\resources\\data\\simpleHome.json");
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
}
