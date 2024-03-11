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

}
