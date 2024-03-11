package dev.TeamRedDragon.SmartHomeSimulator.Command;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;

public abstract class Command {
    public SmartElement smartElement;

    Command(SmartElement smartElement){
        this.smartElement = smartElement;
    }

    public abstract boolean execute();
}
