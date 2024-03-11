package dev.TeamRedDragon.SmartHomeSimulator.Room;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;

import java.util.ArrayList;

public class Room {
    private int roomId;
    private String roomType;
    private ArrayList<SmartElement> smartElements = new ArrayList<SmartElement>();

    public Room(int roomId, String roomType) {
        this.roomId = roomId;
        this.roomType = roomType;
    }

    public ArrayList<SmartElement> getSmartElements() {
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
