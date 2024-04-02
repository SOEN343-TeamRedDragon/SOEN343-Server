package dev.TeamRedDragon.SmartHomeSimulator.Mediator;

import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule.SmartHeatingModule;
import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeCoreFunctionality.SmartHomeCoreFunctionality;
import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule.SmartHomeSecurityModule;

import java.util.ArrayList;
import java.util.Arrays;

public class ConcreteMediator implements Mediator{

    SmartHeatingModule smartHeatingModule = SmartHeatingModule.getSmartHeatingModule();
    SmartHomeCoreFunctionality smartHomeCoreFunctionality = SmartHomeCoreFunctionality.getSmartHomeCoreFunctionality();
    SmartHomeSecurityModule smartHomeSecurityModule = SmartHomeSecurityModule.getSmartHomeSecurityModule();
    ArrayList<ModuleComponent> moduleList = new ArrayList<>();

    @Override
    public void notify(ModuleComponent sender, String message) {
        addModule(smartHeatingModule);
        addModule(smartHomeSecurityModule);
        addModule(smartHomeCoreFunctionality);

            for( ModuleComponent module: moduleList){
                if(!module.getClass().isInstance(sender)) {
                    module.receiveMessage(message);
                }
            }
    }
    public void addModule(ModuleComponent module){
        moduleList.add(module);
    }

}
