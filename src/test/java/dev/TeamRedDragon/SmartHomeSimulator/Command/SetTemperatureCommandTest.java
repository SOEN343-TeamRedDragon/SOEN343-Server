package dev.TeamRedDragon.SmartHomeSimulator.Command;

import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.Heater;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SetTemperatureCommandTest {

    SetTemperatureCommand setTemperatureCommand;
    Heater heater = new Heater(1, "Heater",true);

    private void executable1() {
        setTemperatureCommand = new SetTemperatureCommand(heater);
        heater.setCommand(setTemperatureCommand);
        heater.executeCommand();
    }

    private void executable2(){
        setTemperatureCommand = new SetTemperatureCommand(heater, 20);
        heater.setCommand(setTemperatureCommand);
        heater.executeCommand();
    }

    @Test
    void execute1() {
        assertDoesNotThrow(this::executable1);
    }

    @Test
    void execute2() {
        assertDoesNotThrow(this::executable2);
    }
}