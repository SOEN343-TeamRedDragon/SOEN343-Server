package dev.TeamRedDragon.SmartHomeSimulator.Home;

import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import org.json.simple.JSONObject;

public class HomeService {
    static Home home = Home.getHome();

    public static Room getRoomById(int roomId) {

        for (Room room : home.getRoomList()) {
            if (room.getRoomId() == roomId)
                return room;
        }
        System.err.println("Error finding room by Room Id.");
        return null;
    }

    public static SmartElement getElementByRoomIdAndElementId(int roomId, int elementId) {
        Room room = getRoomById(roomId);

        for (SmartElement element : room.getSmartElementList()) {
            if (element.getElementId() == elementId)
                return element;
        }
        System.err.println("Error finding element by Room & Element Id.");
        return null;
    }

    public static SmartElement toggleElementByRoomIdAndElementId(int roomId, int elementId) {
        SmartElement element = getElementByRoomIdAndElementId(roomId, elementId);
        element.toggle();
        return element;
    }


}

