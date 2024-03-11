package dev.TeamRedDragon.SmartHomeSimulator.Command;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Door;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;

public class ToggleCommand extends Command {

    public ToggleCommand(SmartElement smartElement){
        super(smartElement);
    }

    @Override
    public boolean execute() {
        smartElement.toggle();
        return true;
        }
    }
