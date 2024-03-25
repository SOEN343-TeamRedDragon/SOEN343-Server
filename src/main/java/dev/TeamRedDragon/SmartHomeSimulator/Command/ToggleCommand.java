package dev.TeamRedDragon.SmartHomeSimulator.Command;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule.SmartHeatingModule;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Door;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Window;

public class ToggleCommand extends Command {

    SmartHeatingModule SHH;

    public ToggleCommand(SmartElement smartElement){
        super(smartElement);
    }

    @Override
    public boolean execute() {
        if (smartElement instanceof Window) {
            Window window = (Window) smartElement;
            SHH.blockedWindows.contains(window);
            return false;
        }
        else {
            smartElement.setIsOpen(false);
            return true;
        }
    }
}
