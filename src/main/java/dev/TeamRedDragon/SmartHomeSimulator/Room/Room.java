package dev.TeamRedDragon.SmartHomeSimulator.Room;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElements.SmartElements;

import java.util.ArrayList;

public class Room {
    private int roomId;
    private String roomType;
    private ArrayList<SmartElements> smartElements = new ArrayList<SmartElements>();

    public Room(int roomId, String roomType, ArrayList<SmartElements> smartElements) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.smartElements = smartElements;
    }

    public ArrayList<SmartElements> getSmartElements() {
        return smartElements;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Room {" +
                "smartElements = " + smartElements +
                ", roomId = " + roomId +
                ", roomType = '" + roomType + '\'' +
                '}';
    }
}
