package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule;

import dev.TeamRedDragon.SmartHomeSimulator.Command.OffCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Command.OnCommand;
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
import dev.TeamRedDragon.SmartHomeSimulator.Zone.Zone;
import jakarta.annotation.PostConstruct;

public class SmartHeatingModule {

    OffCommand offCommand;
    OnCommand onCommand;

    private static SmartHeatingModule smartHeatingModule;

    private static SimulationClock simulationClock;

    private Home home = Home.getHome();

    private ArrayList<Zone> zones = new ArrayList<>();
    private SmartHeatingModule(){}

    public static SmartHeatingModule getSmartHeatingModule() {
        if (smartHeatingModule == null) {
                smartHeatingModule = new SmartHeatingModule();
        }
        return smartHeatingModule;
    }

    public void addZone(Zone zone) { this.zones.add(zone);}
    public void removeZone(Zone zone) {this.zones.remove(zone);}

    public ArrayList<Zone> getZones() {return this.zones; }


}
