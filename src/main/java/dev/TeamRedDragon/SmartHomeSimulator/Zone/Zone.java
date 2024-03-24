package dev.TeamRedDragon.SmartHomeSimulator.Zone;
import java.util.ArrayList;

import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;

public class Zone {
    private double zoneId;
    private double amTemp;
    private double pmTemp;
    private double nightTemp;
    private ArrayList<Integer> roomList;

    public Zone(double zoneId, double amTemp, double pmTemp, double nightTemp) {
        this.zoneId = zoneId;
        this.amTemp = amTemp;
        this.pmTemp = pmTemp;  
        this.nightTemp = nightTemp;
        roomList = new ArrayList<>();
    }

    public double getZoneId() {
        return zoneId;
    }

    public ArrayList<Integer> getRoomList() {
        return roomList;
    }

    public void setRoomList(ArrayList<Integer> roomList) {
        this.roomList = roomList;
    }

    public void addRoom(int roomId) {
        roomList.add(roomId);
    }

    public void removeRoom(int roomId) {
        roomList.remove(roomId);
    }

    public String toString() {
        return "Zone ID: " + zoneId + " AM Temp: " + amTemp + " PM Temp: " + pmTemp + " Night Temp: " + nightTemp;
    }
}
