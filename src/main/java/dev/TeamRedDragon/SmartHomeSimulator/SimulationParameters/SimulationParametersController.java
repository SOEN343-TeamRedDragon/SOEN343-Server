package dev.TeamRedDragon.SmartHomeSimulator.SimulationParameters;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/SimulationParameters")
public class SimulationParametersController {

    private SimulationParametersService SimulationParameterService;

    @GetMapping("/")
    public List<SimulationParameters> getSimulationParameters(){
        return SimulationParameterService.getSimulationParameters();
    }

    @GetMapping("/AddSimulationParameters")
    public String addSimulationParameters(SimulationParameters SimulationParameters){
        SimulationParameterService.saveSimulationParameters(SimulationParameters);
        return "Simulation Parameters are sucessfully added!";
    }
}
