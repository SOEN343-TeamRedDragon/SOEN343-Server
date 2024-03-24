package dev.TeamRedDragon.SmartHomeSimulator.SimulationParameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/SimulationParameters")
public class SimulationParametersController {
    @Autowired
    private SimulationParametersService simulationParameterService;

    @GetMapping("/")
    public List<SimulationParameters> getSimulationParameters(){
        return simulationParameterService.getSimulationParameters();
    }

    @PostMapping("/AddSimulationParameters")
    public String addSimulationParameters(@RequestBody SimulationParameters SimulationParameters){
        simulationParameterService.saveSimulationParameters(SimulationParameters);
        return "Simulation Parameters are sucessfully added!";
    }

    @PostMapping("/GetById")
    public SimulationParameters getSimulationParametersById(@RequestBody Map<String, String> data) {
        String parameterId = data.get("parameterId");
        return simulationParameterService.getSimulationParametersById(Integer.valueOf(parameterId));
    }

    @DeleteMapping("/DeleteById")
    public ResponseEntity<Object> DeleteSimulationParametersById(@RequestBody Map<String, String> data) {
        String parameterId = data.get("parameterId");
        if (simulationParameterService.getSimulationParametersById(Integer.valueOf(parameterId)) != null)
        {
            simulationParameterService.deleteSimulationParameters(Integer.parseInt(parameterId));
            return ResponseEntity.status(HttpStatus.OK).body("Simulation parameter deleted. ");
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Simulation parameter not found.");
    }

    @PatchMapping("/Update")
    public ResponseEntity<Object> UpdateSimulationParameter(@RequestBody SimulationParameters simulationParameters) {
        if (simulationParameterService.getSimulationParametersById(simulationParameters.getParameterId()) != null)
        {
            simulationParameterService.updateSimulationParameters(simulationParameters);
            return ResponseEntity.status(HttpStatus.OK).body("Simulation parameters updated. ");
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Simulation parameters not found.");
    }
}
