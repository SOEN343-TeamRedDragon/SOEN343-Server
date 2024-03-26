package dev.TeamRedDragon.SmartHomeSimulator.Command;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule.SmartHeatingModule;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Window;

public class OffCommand extends Command {

    public OffCommand(SmartElement smartElement){
        super(smartElement);
    }

    @Override
    public boolean execute() {
        if (smartElement instanceof Window) {
            Window window = (Window) smartElement;
            if (window.getIsBlocked()) {
                return false;
            }
        }
        smartElement.setIsOpen(false);
        return true;
    }
}
