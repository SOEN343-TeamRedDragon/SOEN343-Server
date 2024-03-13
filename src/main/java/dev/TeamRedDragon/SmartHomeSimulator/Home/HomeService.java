package dev.TeamRedDragon.SmartHomeSimulator.Home;

import dev.TeamRedDragon.SmartHomeSimulator.Command.ToggleCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.Room.RoomService;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class HomeService {
    private Home home = Home.getHome();
    ToggleCommand toggleCommand;


    @Autowired
    private RoomService roomService;

    public void toggleAllElementsByType(String elementType)
    {
        for (Room room : home.getRoomList())
        {
            for (SmartElement element : room.getSmartElementList())
            {
                if (Objects.equals(element.getElementType(), elementType))
                {
                    toggleCommand = new ToggleCommand(element);
                    element.setCommand(toggleCommand);
                    element.executeCommand();
                }
            }
        }
    }
}

