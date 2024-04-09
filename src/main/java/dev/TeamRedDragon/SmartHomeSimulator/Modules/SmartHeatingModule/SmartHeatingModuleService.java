package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule;

import dev.TeamRedDragon.SmartHomeSimulator.Command.OffCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Command.OnCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observable;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observer;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.Room.RoomService;
import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClockService;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.TemperatureData.TemperatureDataService;
import dev.TeamRedDragon.SmartHomeSimulator.Zone.Zone;
import dev.TeamRedDragon.SmartHomeSimulator.Zone.ZoneService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SmartHeatingModuleService implements Observer, Observable {

    private final RoomService roomService = new RoomService();

    private SmartHeatingModule smartHeatingModule = SmartHeatingModule.getSmartHeatingModule();

    private Home home = Home.getHome();

    private SimulationClockService simulationClockService = new SimulationClockService();

    private ZoneService zoneService = new ZoneService();

    OnCommand onCommand;
    OffCommand offCommand;

    public void updateRoomTempByOutdoorTemp() {
        double desiredTemp = 0;
        for (Zone zone : zoneService.getZones())
            for (Room room : home.getRoomList())
            {
                if (zone.getZoneId() == room.getZoneId()) {
                    desiredTemp = switch (simulationClockService.getHour()) {
                        case (0), (1), (2), (3), (4), (5), (6), (7), (8) -> zone.getNightTemp();
                        case (9), (10), (11), (12), (13), (14), (15), (16) -> zone.getAmTemp();
                        case (17), (18), (19), (20), (21), (22), (23) -> zone.getPmTemp();
                        default -> 19;
                    };

                    if (home.checkIfNoOneHome() && !smartHeatingModule.getAwayModeOn() && simulationClockService.getMonth() == (12 | 1 | 2 | 3))
                        desiredTemp = 17;
                    if(room.getTemperature() < desiredTemp - 3){
                        for (SmartElement element : room.getSmartElementList())
                        {
                            if (Objects.equals(element.getElementType(), "AirConditioner"))
                            {
                                if(element.getIsOpen())
                                {
                                    offCommand = new OffCommand(element);
                                    element.setCommand(offCommand);
                                    element.executeCommand();
                                }
                            }
                            if (Objects.equals(element.getElementType(), "Heater"))
                            {
                                if(!element.getIsOpen())
                                {
                                    onCommand = new OnCommand(element);
                                    element.setCommand(onCommand);
                                    element.executeCommand();
                                }

                            }
                        }
                    } else if (room.getTemperature() > desiredTemp + 3) {
                        for (SmartElement element : room.getSmartElementList()) {
                            if (Objects.equals(element.getElementType(), "AirConditioner"))
                            {
                                if(!element.getIsOpen())
                                {
                                    onCommand = new OnCommand(element);
                                    element.setCommand(onCommand);
                                    element.executeCommand();
                                }
                            }
                            if (Objects.equals(element.getElementType(),"Heater"))
                            {
                                if(element.getIsOpen())
                                {
                                    offCommand = new OffCommand(element);
                                    element.setCommand(offCommand);
                                    element.executeCommand();
                                }
                            }
                        }
                    }
                }
            }
    }

    public void changeRoomTempAlgorithm() {
        boolean flag;
        double outdoorTemp = TemperatureDataService.getTemperatureFromClockAndTemperatureData();
        for (Room room : home.getRoomList()) {
            flag = true;
            for (SmartElement element : room.getSmartElementList())
            {
                if (Objects.equals(element.getElementType(), "Heater"))
                {
                    if (element.getIsOpen()) {
                        room.setTemperature(room.getTemperature() + 6);
                        flag = false;
                    }

                } else if (Objects.equals(element.getElementType(), "AirConditioner"))
                {
                    if (element.getIsOpen()) {
                        room.setTemperature(room.getTemperature() - 6);
                        flag = false;
                    }
                }
            }
            if (flag) {
                if (TemperatureDataService.IsCoolerOutside(room)) {
                    if (outdoorTemp > 20 && !smartHeatingModule.getAwayModeOn() && home.checkIfNoOneHome())
                        roomService.turnOnAllElementsInRoomByRoomIdAndElementType(room.getRoomId(),"Window");

                    room.setTemperature(room.getTemperature() - 3);
                }

                else
                    room.setTemperature(room.getTemperature() + 3);
            }
        }
    }

    @Override
    public void update(String event) {
        switch(event) {
            case("SecurityAway"):
                updateOnFromSecurity();
                break;
            case("SecurityActive"):
                updateOffFromSecurity();
                break;
            case("Clock"):
                updateRoomTempByOutdoorTemp();
                changeRoomTempAlgorithm();
                break;
        }
    }


    public void updateOnFromSecurity() {
        smartHeatingModule.setAwayModeOn(true);
    }

    public void updateOffFromSecurity() {
        smartHeatingModule.setAwayModeOn(false);
    }


    @Override
    public void subscribe(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        for (Observer ob : observers)
        {
            ob.update(event);
        }
    }
}
