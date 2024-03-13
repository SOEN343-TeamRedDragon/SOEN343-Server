package dev.TeamRedDragon.SmartHomeSimulator.Command;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;

public class OnCommand extends Command {
    public OnCommand(SmartElement smartElement){
        super(smartElement);
    }

    @Override
    public boolean execute() {
        smartElement.setIsOpen(true);
        return true;
    }
}
