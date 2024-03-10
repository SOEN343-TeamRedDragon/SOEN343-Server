package dev.TeamRedDragon.SmartHomeSimulator.Home;

import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;

import java.util.ArrayList;

public class Home {

    private String homeId;
    private String homeType;
    private static Home home;


    private String roomCount;
    private ArrayList<Room> roomList = new ArrayList<Room>();

    private Home(){}

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
}
