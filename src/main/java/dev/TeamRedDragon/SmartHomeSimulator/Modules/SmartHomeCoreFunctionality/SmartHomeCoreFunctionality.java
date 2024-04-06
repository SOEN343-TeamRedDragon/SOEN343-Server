package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeCoreFunctionality;

import dev.TeamRedDragon.SmartHomeSimulator.Mediator.ConcreteMediator;
import dev.TeamRedDragon.SmartHomeSimulator.Mediator.ModuleComponent;
import dev.TeamRedDragon.SmartHomeSimulator.Observer.Observer;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Light;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;

public class SmartHomeCoreFunctionality implements Observer, ModuleComponent {

    ConcreteMediator mediator = new ConcreteMediator();
    private volatile static SmartHomeCoreFunctionality smartHomeCoreFunctionality;

    private SmartHomeCoreFunctionality() {}

    public static SmartHomeCoreFunctionality getSmartHomeCoreFunctionality() {
        if (smartHomeCoreFunctionality == null) {
            smartHomeCoreFunctionality = new SmartHomeCoreFunctionality();
        }
        return smartHomeCoreFunctionality;
    }

   // Observer pattern for update method for lights - need to add windows

    @Override
    public void update() {
        //TODO: do thing
    }

    @Override
    public void sendMessage(String message) {
        mediator.notify(this, message);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.print("SmartHomeCore Receiving a message: "+ message);
    }
}
