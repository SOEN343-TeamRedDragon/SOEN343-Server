package dev.TeamRedDragon.SmartHomeSimulator.Modules;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Heater;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;

import java.time.LocalTime;
import java.util.ArrayList;

import dev.TeamRedDragon.SmartHomeSimulator.Command.Command;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.SmartElementObserver;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.Command.SetTemperatureCommand;

public class SmartHeatingModule implements SmartElementObserver {

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

    public int getCurrentHour() {
        LocalTime currentTime = LocalTime.now();
        int currentHour = currentTime.getHour();
        return currentHour;
    }

    public void setDesiredTemperature() {
        int currentHour = getCurrentHour();
        for (Room room : coolZoneList) {
            for (SmartElement element : room.getSmartElementList()) {
                if (element instanceof Heater) {
                    Heater heater = (Heater) element;
                    Command setTemperatureCommand = getTemperatureCommandForHour(heater, currentHour);
                    setTemperatureCommand.execute();
                }
            }
        }
        for (Room room : heatZoneList) {
            for (SmartElement element : room.getSmartElementList()) {
                if (element instanceof Heater) {
                    Heater heater = (Heater) element;
                    Command setTemperatureCommand = getTemperatureCommandForHour(heater, currentHour);
                    setTemperatureCommand.execute();
                }
            }
        }
    }
    
    private Command getTemperatureCommandForHour(Heater heater, int currentHour) {
        if (currentHour >= 0 && currentHour <= 6) {
            return new SetTemperatureCommand(heater, 15);
        } else if (currentHour >= 7 && currentHour <= 12) {
            return new SetTemperatureCommand(heater, 20);
        } else if (currentHour >= 13 && currentHour <= 18) {
            return new SetTemperatureCommand(heater, 25);
        } else if (currentHour >= 19 && currentHour <= 23) {
            return new SetTemperatureCommand(heater, 20);
        }
        return new SetTemperatureCommand(heater, 20);
    }





    // Observer pattern for update method for heating
    @Override
    public void update(SmartElement element) {
        if (element instanceof Heater) {
            Heater heater = (Heater) element;
            System.out.println("Heating module notified: " + heater);
        }
    }
}
