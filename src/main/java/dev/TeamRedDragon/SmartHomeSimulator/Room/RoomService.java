package dev.TeamRedDragon.SmartHomeSimulator.Room;

import dev.TeamRedDragon.SmartHomeSimulator.Command.ToggleCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoomService {
    private Home home = Home.getHome();




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
                ToggleCommand toggleCommand = new ToggleCommand(element);
                element.setCommand(toggleCommand);
                element.executeCommand();
            }
        }
        return room;
    }

    public void ChangeUserLocationByUserNameAndRoomId(String userName, int roomId) {

    }
}
