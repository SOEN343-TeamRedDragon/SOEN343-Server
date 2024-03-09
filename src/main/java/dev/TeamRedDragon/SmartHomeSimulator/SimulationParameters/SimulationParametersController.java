package dev.TeamRedDragon.SmartHomeSimulator.SimulationParameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SimulationParameters")
public class SimulationParametersController {
    @Autowired
    private SimulationParametersService SimulationParameterService;

    @GetMapping("/")
    public List<SimulationParameters> getSimulationParameters(){
        return SimulationParameterService.getSimulationParameters();
    }

    @PostMapping("/AddSimulationParameters")
    public String addSimulationParameters(@RequestBody SimulationParameters SimulationParameters){
        SimulationParameterService.saveSimulationParameters(SimulationParameters);
        return "Simulation Parameters are sucessfully added!";

    }
}
