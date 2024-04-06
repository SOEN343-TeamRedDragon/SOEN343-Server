package dev.TeamRedDragon.SmartHomeSimulator.Room;

import dev.TeamRedDragon.SmartHomeSimulator.Command.ToggleCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Light;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.User.User;
import dev.TeamRedDragon.SmartHomeSimulator.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RoomService {
    private Home home = Home.getHome();
    ToggleCommand toggleCommand;

    @Autowired
    private UserService userService;

    public List<Room> getRooms(){
        return home.getRoomList();
    }


    public  Room getRoomById(int roomId) {

        for (Room room : home.getRoomList()) {
            if (room.getRoomId() == roomId)
                return room;
        }
        System.err.println("Error finding room by Room Id.");
        return null;
    }


    public Room toggleAllElementsInRoomByRoomIdAndElementType(int roomId, String elementType) {
        Room room = getRoomById(roomId);

        for(SmartElement element : room.getSmartElementList()) {
            if (Objects.equals(element.getElementType(), elementType))
            {
                toggleCommand = new ToggleCommand(element);
                element.setCommand(toggleCommand);
                element.executeCommand();
            }
        }
        return room;
    }

    public void changeUserLocationByNewIdOldIdAndUserName(int newRoomId, int oldRoomId, String userName) {
        removeUserFromRoomByRoomIdAndUserName(oldRoomId, userName);
        addUserToRoomByRoomIdAndUserName(newRoomId, userName);
    }

    public Room addUserToRoomByRoomIdAndUserName(int roomId, String userName) {
        User user = userService.getUserByUserName(userName);
        for (Room room : home.getRoomList()) {
            if (room.getRoomId() == roomId) {
                user.setLocation(room.getRoomType());
                room.addUserToRoom(user);
                return room;
            }
        }
        return null;
    }
    
    public Room removeUserFromRoomByRoomIdAndUserName(int roomId, String userName) {
        User user = userService.getUserByUserName(userName);
        for (Room room : home.getRoomList()) {
            for (User u : room.getUserList())
                if (room.getRoomId() == roomId && Objects.equals(u.getId(), user.getId())) {
                    room.removeUserFromRoom(user);
                    user.setLocation("");
                    return room;
                }
        }
        return null;
    }

    public Room setZoneIdByRoomId(int zoneId, int roomId) {
        for (Room room : home.getRoomList()) {
            if (room.getRoomId() == roomId) {
                room.setZoneId(zoneId);
                return room;
            }
        }
        return null;
    }

    public Room overrideRoomTemperatureByRoomId(int roomId,double temp) {
        for (Room room : home.getRoomList())
        {
            if (room.getRoomId() == roomId)
            {
                room.setTempOverridden(false);
                room.setTemperature(temp);
                room.setTempOverridden(true);
                return room;
            }
        }
        return null;
    }

    public Room resetOverride(int roomId) {
        for (Room room : home.getRoomList())
            if (room.getRoomId() == roomId) {
                room.setTempOverridden(false);
                return room;
            }
        return null;
    }
}
