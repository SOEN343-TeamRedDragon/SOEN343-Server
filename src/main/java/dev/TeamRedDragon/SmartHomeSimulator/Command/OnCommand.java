package dev.TeamRedDragon.SmartHomeSimulator.Command;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule.SmartHeatingModule;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Window;

public class OnCommand extends Command {

    Home home = Home.getHome();

    public OnCommand(SmartElement smartElement){
        super(smartElement);
    }

    @Override
    public boolean execute() {
        for (Room room : home.getRoomList()) {
            for (SmartElement element : room.getSmartElementList()) {
                if (element instanceof Window) {
                    Window window = (Window) element;
                    if (!window.isWindowBlocked()) {
                        element.setIsOpen(true);
                    }
                } else {
                    element.setIsOpen(true);
                }
            }
        }
        return true;
    }
}
