package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule;

import dev.TeamRedDragon.SmartHomeSimulator.Command.OffCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observer;
import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClock;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.AirConditioner;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Heater;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

import dev.TeamRedDragon.SmartHomeSimulator.Command.Command;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.Command.SetTemperatureCommand;
import dev.TeamRedDragon.SmartHomeSimulator.TemperatureData.TemperatureDataService;

public class SmartHeatingModule implements Observer {

    Command command;

    private static SmartHeatingModule smartHeatingModule;

    private static SimulationClock simulationClock = SimulationClock.getSimulationClock();

    private Home home = Home.getHome();

    private ArrayList<Room> coolZoneList = new ArrayList<Room>();
    private ArrayList<Room> heatZoneList = new ArrayList<Room>();

    public ArrayList<Room> getCoolZoneList() {
        return coolZoneList;
    }

    public ArrayList<Room> getHeatZoneList() {
        return heatZoneList;
    }

    public void addRoomToCoolZoneList(Room room) {
        coolZoneList.add(room);
    }

    public void addRoomToHeatZoneList(Room room) {
        heatZoneList.add(room);
    }

    public void removeRoomFromCoolZoneList(Room room) {
        coolZoneList.remove(room);
    }

    public void removeRoomFromHeatZoneList(Room room) {
        heatZoneList.remove(room);
    }

    private SmartHeatingModule(){}

    public static SmartHeatingModule getSmartHeatingModule() {
        if (smartHeatingModule == null) {
            smartHeatingModule = new SmartHeatingModule();
            simulationClock.subscribe(smartHeatingModule);
        }

        return smartHeatingModule;
    }

    public void updateRoomTempByOutdoorTemp() {
        for (Room room : home.getRoomList())
        {
            if(TemperatureDataService.IsCoolerOutside(room)){
                for (SmartElement element : room.getSmartElementList())
                {
                    if (Objects.equals(element.getElementType(), "AirConditioner"))
                    {
                        command = new OffCommand(element);
                        element.setCommand(command);
                        element.executeCommand();
                    }
                }
            }
        }
    }

    @Override
    public void update() {
        updateRoomTempByOutdoorTemp();
    }
}
