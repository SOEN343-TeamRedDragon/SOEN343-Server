package dev.TeamRedDragon.SmartHomeSimulator.Home;

import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import java.util.ArrayList;


public class Home {

    private int homeId;
    private String homeType;
    private static Home home;
    private int roomCount;
    private Boolean noUsers;

    private ArrayList<Room> roomList = new ArrayList<Room>();

    private Home(){}

    public static Home getHome(){
        if (home == null){
              home = new Home();
              home.init();
        }
        return home;
    }

    public Room getRoomById(int roomId) {
        for (Room room : roomList) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null; 
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

    public void setRoomList(ArrayList<Room> roomList) {
        this.roomList = roomList;
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

    public boolean checkIfNoOneHome() {
        boolean flag = true;
        for (Room room : roomList) {
            if (!room.getUserList().isEmpty())
                flag = false;
        }
        return flag;
    }

    @Override
    public String toString() {
        return "Home {" +
                "homeId = '" + homeId + '\'' +
                ", homeType = '" + homeType + '\'' +
                ", roomCount = '" + roomCount + '\'' +
                '}';
    }
    private void init() {
        this.noUsers = checkIfNoOneHome();
    }
}
