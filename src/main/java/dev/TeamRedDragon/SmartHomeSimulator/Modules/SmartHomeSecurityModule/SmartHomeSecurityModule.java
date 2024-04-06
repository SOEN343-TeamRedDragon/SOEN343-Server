package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule;

import dev.TeamRedDragon.SmartHomeSimulator.Mediator.ConcreteMediator;
import dev.TeamRedDragon.SmartHomeSimulator.Mediator.ModuleComponent;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observer;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Door;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Window;
import org.springframework.beans.factory.annotation.Autowired;

public class SmartHomeSecurityModule implements Observer, ModuleComponent {
    private volatile static SmartHomeSecurityModule smartHomeSecurityModule;

    ConcreteMediator mediator = new ConcreteMediator();
    private SmartHomeSecurityModule(){}
    public static SmartHomeSecurityModule getSmartHomeSecurityModule() {

        if (smartHomeSecurityModule == null) {
            smartHomeSecurityModule = new SmartHomeSecurityModule();
        }
        return smartHomeSecurityModule;
    }

    @Override
    public void update() {
        //TODO: do stuff
    }

    @Override
    public void sendMessage(String message) {
        mediator.notify(this, message);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.print("SmartHomeSecurity Receiving a message: "+ message);
    }
}
