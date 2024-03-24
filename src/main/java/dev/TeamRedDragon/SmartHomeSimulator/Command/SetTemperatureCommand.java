package dev.TeamRedDragon.SmartHomeSimulator.Command;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Heater;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import java.time.LocalTime;

public class SetTemperatureCommand extends Command {
    private Heater heater;
    private int temperature;

    private int currentHour;

    private boolean temperaturePassed = false;


    public SetTemperatureCommand(Heater heater, int temperature) {
        super(heater);
        this.heater = heater;
        this.temperature = temperature;
        this.temperaturePassed = true;
    }

    public SetTemperatureCommand(Heater heater) {
        super(heater);
        this.heater = heater;
    }

    private void setCurrentHour() {
        LocalTime currentTime = LocalTime.now();
        this.currentHour = currentTime.getHour();
    }

    @Override
    public boolean execute() {
        if (temperaturePassed)
        {
            heater.setTemperature(temperature);
            return true;
        }

        setCurrentHour();
        if (currentHour >= 0 && currentHour <= 6) {
           heater.setTemperature(15);
           return true;
        } else if (currentHour >= 7 && currentHour <= 12) {
           heater.setTemperature(20);
           return true;
        } else if (currentHour >= 13 && currentHour <= 18) {
            heater.setTemperature(25);
            return true;
        } else if (currentHour >= 19 && currentHour <= 23) {
            heater.setTemperature(20);
            return true;
        }
        return false;
    }
}
