package dev.TeamRedDragon.SmartHomeSimulator.Command;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule.SmartHeatingModule;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Window;

public class OffCommand extends Command {

    Home home = Home.getHome();

    public OffCommand(SmartElement smartElement){
        super(smartElement);
    }

    @Override
    public boolean execute() {
        for (Room room : home.getRoomList()) {
            for (SmartElement element : room.getSmartElementList()) {
                if (element instanceof Window) {
                    Window window = (Window) element;
                    if (!window.isWindowBlocked()) {
                        element.setIsOpen(false);
                    }
                } else {
                    element.setIsOpen(false);
                }
            }
        }
        return true;
    }
}
