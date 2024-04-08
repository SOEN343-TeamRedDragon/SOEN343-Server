package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule;

import dev.TeamRedDragon.SmartHomeSimulator.Command.OffCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Command.OnCommand;
import dev.TeamRedDragon.SmartHomeSimulator.Mediator.ConcreteMediator;
import dev.TeamRedDragon.SmartHomeSimulator.Mediator.ModuleComponent;
import dev.TeamRedDragon.SmartHomeSimulator.SimulationClock.SimulationClock;
import java.util.ArrayList;
import dev.TeamRedDragon.SmartHomeSimulator.Zone.Zone;


public class SmartHeatingModule implements ModuleComponent {

    ConcreteMediator mediator = new ConcreteMediator();

    private volatile static SmartHeatingModule smartHeatingModule;

    private static SimulationClock simulationClock;
    private static Boolean awayModeOn = false;

    private ArrayList<Zone> zones = new ArrayList<>();
    private SmartHeatingModule(){}

    public static SmartHeatingModule getSmartHeatingModule() {
        if (smartHeatingModule == null) {
                smartHeatingModule = new SmartHeatingModule();
        }
        return smartHeatingModule;
    }

    public static Boolean getAwayModeOn() {
        return awayModeOn;
    }

    public static void setAwayModeOn(Boolean awayModeOn) {
        SmartHeatingModule.awayModeOn = awayModeOn;
    }

    public void addZone(Zone zone) { this.zones.add(zone);}
    public void removeZone(Zone zone) {this.zones.remove(zone);}

    public ArrayList<Zone> getZones() {return this.zones; }

    @Override
    public void sendMessage(String message) {
        mediator.notify(this, message);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.print("SmartHeatingModule Receiving a message: "+ message);
    }
}
