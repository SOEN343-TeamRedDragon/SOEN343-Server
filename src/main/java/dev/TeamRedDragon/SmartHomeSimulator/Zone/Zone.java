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
        int index = roomList.indexOf(roomId);
        roomList.remove(index);
    }

    public double getAmTemp() {
        return amTemp;
    }

    public double getPmTemp() {
        return pmTemp;
    }

    public double getNightTemp() {
        return nightTemp;
    }

    public void setAmTemp(double amTemp) {
        this.amTemp = amTemp;
    }

    public void setPmTemp(double pmTemp) {
        this.pmTemp = pmTemp;
    }

    public void setNightTemp(double nightTemp) {
        this.nightTemp = nightTemp;
    }

    public String toString() {
        return "Zone ID: " + zoneId +
               " AM Temp: " + amTemp +
               " PM Temp: " + pmTemp +
               " Night Temp: " + nightTemp +
               " Rooms: " + roomList;
    }
}
