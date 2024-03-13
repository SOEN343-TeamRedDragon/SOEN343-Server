package dev.TeamRedDragon.SmartHomeSimulator.Command;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;

public class OffCommand extends Command {
    public OffCommand(SmartElement smartElement){
        super(smartElement);
    }

    @Override
    public boolean execute() {
        smartElement.setIsOpen(false);
        return true;
    }
}
