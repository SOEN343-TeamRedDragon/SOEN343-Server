package dev.TeamRedDragon.SmartHomeSimulator.Room;

import dev.TeamRedDragon.SmartHomeSimulator.MotionDetectors.MotionDetector;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Light;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.User.User;

import java.util.ArrayList;

public class Room {
    private int roomId;
    private String roomType;
    private ArrayList<SmartElement> smartElementList = new ArrayList<SmartElement>();

    private int elementCount;

    private double temperature;

    private int zoneId;

    private ArrayList<User> userList = new ArrayList<User>();

    private int userCount;
    private boolean autoModeEnabled = false;

    private boolean tempOverridden = false;

    private MotionDetector motionDetector;;

    private ArrayList<MotionDetector> sensorList = new ArrayList<MotionDetector>();


    public Room(int roomId, String roomType,double temperature, int zoneId, ArrayList<SmartElement> smartElementList, ArrayList<User> userList) {
        this.roomId = roomId;
        this.zoneId = zoneId;
        this.roomType = roomType;
        this.temperature = temperature;
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
  
    public boolean isAutoModeEnabled() {
        return autoModeEnabled;
    }

    public void setAutoModeEnabled(boolean autoModeEnabled) {
        this.autoModeEnabled = autoModeEnabled;
    }

    private void adjustLightsForAutoMode() {
        if (!autoModeEnabled) {
            return; 
        }
        boolean shouldBeOn = !this.userList.isEmpty();
        for (SmartElement element : smartElementList) {
            if (element instanceof Light) {
                element.setIsOpen(shouldBeOn);
            }
        }
    }

    public void addUserToRoom(User user) {
        this.userList.add(user);
        adjustLightsForAutoMode();
    }


    public void removeUserFromRoom(User user) {
        this.userList.remove(user);
        adjustLightsForAutoMode();
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        if (!tempOverridden)
            this.temperature = temperature;
    }

    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    public boolean isTempOverridden() {
        return tempOverridden;
    }

    public void setTempOverridden(boolean tempOverridden) {
        this.tempOverridden = tempOverridden;
    }

    public void addMotionDetector(MotionDetector sensor) {
        sensorList.add(sensor);
    }

    public void removeMotionDetector(MotionDetector sensor) {
        sensorList.remove(sensor);
    }
}
