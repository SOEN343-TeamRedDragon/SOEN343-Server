package dev.TeamRedDragon.SmartHomeSimulator.Room;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.User.User;

import java.util.ArrayList;

public class Room {
    private int roomId;
    private String roomType;
    private ArrayList<SmartElement> smartElementList = new ArrayList<SmartElement>();

    private int elementCount;
    private boolean autoMode = false;

    private ArrayList<User> userList = new ArrayList<User>();

    private int userCount;

    public Room(int roomId, String roomType, ArrayList<SmartElement> smartElementList, ArrayList<User> userList) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.smartElementList = smartElementList;
        this.elementCount = smartElementList.size();
        this.userList = userList;
        this.userCount = userList.size();
    }

    public ArrayList<SmartElement> getSmartElementList() {
        return smartElementList;
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

    public int getElementCount() {
        return elementCount;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> usersInRoom) {
        this.userList = usersInRoom;
    }

    @Override
    public String toString() {
        return "Room {" +
                "smartElements = " + smartElementList +
                ", roomId = " + roomId +
                ", roomType = '" + roomType + '\'' +
                '}';
    }

    public void removeUserFromRoom(User user){
        ArrayList<User> userList = this.getUserList();
        System.out.println(userList);
        userList.remove(user);
        this.setUserList(userList);
        System.out.println(this.userList);
    }

    public void addUserToRoom(User user) {
        ArrayList<User> userList = this.getUserList();
        userList.add(user);
        this.setUserList(userList);
    }

    public boolean isAutoMode() {
        return autoMode;
    }

    public void setAutoMode(boolean autoMode) {
        this.autoMode = autoMode;
    }
}
