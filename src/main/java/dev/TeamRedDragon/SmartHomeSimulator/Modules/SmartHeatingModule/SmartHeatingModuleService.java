package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule;

import dev.TeamRedDragon.SmartHomeSimulator.Command.Command;
import dev.TeamRedDragon.SmartHomeSimulator.Command.OffCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Command.OnCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Command.SetTemperatureCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.AirConditioner;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Heater;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.TemperatureData.TemperatureDataService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SmartHeatingModuleService {

    private SmartHeatingModule smartHeatingModule;
    private Home home = Home.getHome();

    OnCommand onCommand;
    OffCommand offCommand;

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

    public void updateRoomTempByOutdoorTemp() {
        for (Room room : home.getRoomList())
        {
            if(TemperatureDataService.IsCoolerOutside(room)){
                for (SmartElement element : room.getSmartElementList())
                {
                    if (Objects.equals(element.getElementType(), "AirConditioner"))
                    {
                        if(element.getIsOpen())
                        {
                            offCommand = new OffCommand(element);
                            element.setCommand(offCommand);
                            element.executeCommand();
                            System.out.println(room.getRoomId()+": AC OFF.");
                        }
                    }
                    if (Objects.equals(element.getElementType(), "Heater"))
                    {
                        if(!element.getIsOpen())
                        {
                            onCommand = new OnCommand(element);
                            element.setCommand(onCommand);
                            element.executeCommand();
                            System.out.println(room.getRoomId() + ": HEATER ON.");
                        }

                    }
                }
            } else {
                for (SmartElement element : room.getSmartElementList()) {
                    if (Objects.equals(element.getElementType(), "AirConditioner"))
                    {
                        if(!element.getIsOpen())
                        {
                            onCommand = new OnCommand(element);
                            element.setCommand(onCommand);
                            element.executeCommand();
                            System.out.println(room.getRoomId() + ": AC ON.");
                        }
                    }
                    if (Objects.equals(element.getElementType(),"Heater"))
                    {
                        if(element.getIsOpen())
                        {
                            offCommand = new OffCommand(element);
                            element.setCommand(offCommand);
                            element.executeCommand();
                            System.out.println(room.getRoomId() + ": HEATER OFF.");

                        }
                    }
                }
            }
        }
    }

    public void changeRoomTempAlgorithm() {
        for (Room room : home.getRoomList()) {
            for (SmartElement element : room.getSmartElementList())
            {
                if (Objects.equals(element.getElementType(), "Heater"))
                    if (element.getIsOpen())
                        room.setTemperature(room.getTemperature() + 6);

                else if (Objects.equals(element.getElementType(), "AirConditioner"))
                    if (element.getIsOpen())
                        room.setTemperature(room.getTemperature() - 6);

                else {
                        if (TemperatureDataService.IsCoolerOutside(room))
                            room.setTemperature(room.getTemperature() - 3);

                        if (!TemperatureDataService.IsCoolerOutside(room))
                            room.setTemperature(room.getTemperature() + 3);
                    }
            }
        }
    }
}
