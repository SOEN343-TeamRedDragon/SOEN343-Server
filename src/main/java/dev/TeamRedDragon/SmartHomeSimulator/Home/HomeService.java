package dev.TeamRedDragon.SmartHomeSimulator.Home;

import dev.TeamRedDragon.SmartHomeSimulator.Command.OffCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Command.OnCommand;
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
    OffCommand offCommand;
    OnCommand onCommand;


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

    public Home setAllElementsStateByBooleanAndType(Boolean isOpen, String elementType)
    {
        for (Room room : home.getRoomList())
        {
            for (SmartElement element : room.getSmartElementList())
            {
                if (Objects.equals(element.getElementType(), elementType))
                {
                    if(!isOpen)
                    {
                        offCommand = new OffCommand(element);
                        element.setCommand(offCommand);
                        element.executeCommand();
                    }
                    else
                    {
                        onCommand = new OnCommand(element);
                        element.setCommand(onCommand);
                        element.executeCommand();
                    }
                }
            }
        }
        return Home.getHome();
    }
}

