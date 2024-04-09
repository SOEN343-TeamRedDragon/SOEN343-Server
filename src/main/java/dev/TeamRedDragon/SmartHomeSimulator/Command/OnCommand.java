package dev.TeamRedDragon.SmartHomeSimulator.Command;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Window;

public class OnCommand extends Command {

    public OnCommand(SmartElement smartElement){
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

        smartElement.setIsOpen(true);
        return true;
    }
}
