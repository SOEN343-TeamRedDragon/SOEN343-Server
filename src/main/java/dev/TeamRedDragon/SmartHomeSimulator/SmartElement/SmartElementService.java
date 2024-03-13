package dev.TeamRedDragon.SmartHomeSimulator.SmartElement;

import dev.TeamRedDragon.SmartHomeSimulator.Command.ToggleCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.Room.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmartElementService {

    private Home home = Home.getHome();

    @Autowired
    RoomService roomService;

    public SmartElement getElementByRoomIdAndElementId(int roomId, int elementId) {
        Room room = roomService.getRoomById(roomId);

        for (SmartElement element : room.getSmartElementList()) {
            if (element.getElementId() == elementId)
                return element;
        }
        System.err.println("Error finding element by Room & Element Id.");
        return null;
    }

    public SmartElement toggleElementByRoomIdAndElementId(int roomId, int elementId) {
        SmartElement element = getElementByRoomIdAndElementId(roomId, elementId);
        ToggleCommand toggleCommand = new ToggleCommand(element);
        element.setCommand(toggleCommand);
        element.executeCommand();
        return element;
    }
}
