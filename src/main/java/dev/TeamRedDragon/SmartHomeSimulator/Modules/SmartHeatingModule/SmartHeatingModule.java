package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule;

import dev.TeamRedDragon.SmartHomeSimulator.Command.OffCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Command.OnCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observer;
import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClock;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.AirConditioner;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Heater;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Window;

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

    private ArrayList<Window> blockedWindows = new ArrayList<>();

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

    public void windowIsBlocked() {
        blockedWindows.clear();
        
        for (Room room : home.getRoomList()) {
            for (SmartElement element : room.getSmartElementList()) {
                if (element instanceof Window) {
                    Window window = (Window) element;
                    if (window.isWindowBlocked()) {
                        blockedWindows.add(window);
                    }
                }
            }
        }
    }

    public void windowIsUnBlocked() {
        for (int i = blockedWindows.size() - 1; i >= 0; i--) {
            Window window = blockedWindows.get(i);
            if (!window.isWindowBlocked()) {
                blockedWindows.remove(i);
            }
        }
    }
}
