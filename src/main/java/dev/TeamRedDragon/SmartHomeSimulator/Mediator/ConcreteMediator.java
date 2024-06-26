package dev.TeamRedDragon.SmartHomeSimulator.Mediator;

import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule.SmartHeatingModule;
import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeCoreFunctionality.SmartHomeCoreFunctionality;
import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule.SmartHomeSecurityModule;

import java.util.ArrayList;

public class ConcreteMediator implements Mediator{

    SmartHeatingModule smartHeatingModule;
    SmartHomeCoreFunctionality smartHomeCoreFunctionality;
    SmartHomeSecurityModule smartHomeSecurityModule;
    ArrayList<ModuleComponent> moduleList = new ArrayList<>();

    @Override
    public void notify(ModuleComponent sender, String message) {
        addModule(smartHeatingModule = SmartHeatingModule.getSmartHeatingModule());
        addModule(smartHomeSecurityModule = SmartHomeSecurityModule.getSmartHomeSecurityModule());
        addModule(smartHomeCoreFunctionality = SmartHomeCoreFunctionality.getSmartHomeCoreFunctionality());

            for( ModuleComponent module: moduleList){
                if(!module.getClass().isInstance(sender)) {
                    module.receiveMessage(message);
                }
            }
    }
    public void addModule(ModuleComponent module){
        this.moduleList.add(module);
    }

}
