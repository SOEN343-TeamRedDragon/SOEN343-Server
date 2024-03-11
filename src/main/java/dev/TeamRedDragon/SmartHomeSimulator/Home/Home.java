package dev.TeamRedDragon.SmartHomeSimulator.Home;

import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElements.SmartElements;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import static java.lang.Math.toIntExact;

public class Home {

    private String homeId;
    private String homeType;
    private static Home home;


    private String roomCount;

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
    }

    private ArrayList<Room> roomList = new ArrayList<Room>();

    public Home(){}

    public static Home getHome(){
        if (home == null){
              home = new Home();
        }
        return home;
    }

    public String getHomeId() { return homeId;}

    public String getHomeType() {
        return homeType;
    }

    public String getRoomCount() {
        return roomCount;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
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
        Home home = new Home();
        ArrayList<Room> roomList = new ArrayList<>();
        ArrayList<SmartElements> smartELements = new ArrayList<>();
        int numberOfRooms;

        JSONParser parser = new JSONParser();
        Reader reader = new FileReader("C:\\Users\\Dugua\\IdeaProjects\\SOEN343-Server\\src\\main\\resources\\data\\simpleHome.json");
        Object jsonobj = parser.parse(reader);
        JSONObject data = (JSONObject) jsonobj;

        JSONArray roomArray = (JSONArray) data.get("roomList");

        numberOfRooms = toIntExact((long) data.get("roomCount"));

        for (int i = 0; i < numberOfRooms; i++)
        {
            JSONObject room = (JSONObject) roomArray.get(i);
            int roomId = toIntExact((long) room.get("roomId"));
            String roomType = (String) room.get("roomType");
            int elementCount = toIntExact((long) room.get("elementCount"));
            JSONArray elementArray = (JSONArray) room.get("smartElements");


            for (int j = 0; j < elementCount; j++)
            {
                JSONObject smartElement = (JSONObject) elementArray.get(j);
                String classType = (String) smartElement.get("classType");
                int elementId = toIntExact((long) smartElement.get("elementId"));
                String open = (String) smartElement.get("open");

            }

        }

    }
}
