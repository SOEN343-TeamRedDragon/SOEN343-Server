package dev.TeamRedDragon.SmartHomeSimulator.Command;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Heater;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;

public class SetTemperatureCommand extends Command {
    private Heater heater;
    private int temperature;

    public SetTemperatureCommand(Heater heater, int temperature) {
        super(heater);
        this.heater = heater;
        this.temperature = temperature;
    }

    @Override
    public boolean execute() {
        heater.setTemperature(temperature);
        return true;
    }
}
