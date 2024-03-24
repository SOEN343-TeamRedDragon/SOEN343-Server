package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule;

import dev.TeamRedDragon.SmartHomeSimulator.Command.Command;
import dev.TeamRedDragon.SmartHomeSimulator.Command.SetTemperatureCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Heater;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import org.springframework.stereotype.Service;

@Service
public class SmartHeatingModuleService {

    private SmartHeatingModule smartHeatingModule = SmartHeatingModule.getSmartHeatingModule();

    SetTemperatureCommand setTemperatureCommand;

    public void setDesiredTemperature() {
        for (Room room : smartHeatingModule.getCoolZoneList()) {
            for (SmartElement element : room.getSmartElementList()) {
                if (element instanceof Heater) {
                    setTemperatureCommand = new SetTemperatureCommand((Heater) element);
                    element.setCommand(setTemperatureCommand);
                    element.executeCommand();
                }
            }
        }
        for (Room room : smartHeatingModule.getHeatZoneList()) {
            for (SmartElement element : room.getSmartElementList()) {
                if (element instanceof Heater) {
                    setTemperatureCommand = new SetTemperatureCommand((Heater) element);
                    element.setCommand(setTemperatureCommand);
                    element.executeCommand();
                }
            }
        }
    }
}
