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

    public void changeUserLocationByNewRoomIdAndUserName(int roomId, String userName) {
        User user = userService.getUserByUserName(userName);
        for (Room room : home.getRoomList())
        {
            if (room.getUserList().contains(user))
            {
                room.removeUserFromRoom(user);
                user.setLocation("");
            }

            if (room.getRoomId() == roomId)
            {
                user.setLocation(room.getRoomType());
                room.addUserToRoom(user);
            }
        }
    }

    public Room addUserToRoomByRoomIdAndUserName(int roomId, String userName) {
        User user = userService.getUserByUserName(userName);
        for (Room room : home.getRoomList()) {
            if (room.getRoomId() == roomId) {
                user.setLocation(room.getRoomType());
                room.addUserToRoom(user);
                if (room.isAutoMode()) {
                    toggleLights(room, true);
                }
                return room;
            }
        }
        return null;
    }
    
    public Room removeUserFromRoomByRoomIdAndUserName(int roomId, String userName) {
        User user = userService.getUserByUserName(userName);
        for (Room room : home.getRoomList()) {
            if (room.getRoomId() == roomId && room.getUserList().contains(user)) {
                room.removeUserFromRoom(user);
                user.setLocation("");
                if (room.isAutoMode() && room.getUserList().isEmpty()) {
                    toggleLights(room, false);
                }
                return room;
            }
        }
        return null;
    }
    
    private void toggleLights(Room room, boolean turnOn) {
        for (SmartElement element : room.getSmartElementList()) {
            if (element instanceof Light) { 
                element.setIsOpen(turnOn);
            }
        }
    }

    public void setAutoModeForRoom(int roomId, boolean activate) {
        Room room = getRoomById(roomId);
        if (room != null) {
            room.setAutoMode(activate);
        } else {
            System.err.println("Room not found.");
        }
    }
}
