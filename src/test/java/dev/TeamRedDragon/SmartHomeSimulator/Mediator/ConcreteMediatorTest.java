package dev.TeamRedDragon.SmartHomeSimulator.Mediator;

import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule.SmartHeatingModule;
import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeCoreFunctionality.SmartHomeCoreFunctionality;
import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule.SmartHomeSecurityModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMediatorTest {

    SmartHeatingModule smartHeatingModule = SmartHeatingModule.getSmartHeatingModule();
    SmartHomeCoreFunctionality smartHomeCoreFunctionality = SmartHomeCoreFunctionality.getSmartHomeCoreFunctionality();
    SmartHomeSecurityModule smartHomeSecurityModule = SmartHomeSecurityModule.getSmartHomeSecurityModule();

    @Test
    void testNotify() {
        //Arrange
        ConcreteMediator mediator = new ConcreteMediator();

        // Assert
        assertDoesNotThrow(() -> mediator.notify(smartHeatingModule, "message"));
        assertDoesNotThrow(() -> mediator.notify(smartHomeCoreFunctionality, "message2"));
        assertDoesNotThrow(() -> mediator.notify(smartHomeSecurityModule, "message3"));
    }
}