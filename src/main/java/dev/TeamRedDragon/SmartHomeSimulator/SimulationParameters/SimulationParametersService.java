package dev.TeamRedDragon.SmartHomeSimulator.SimulationParameters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SimulationParametersService {
    @Autowired
    private SimulationParametersRepository SimulationParametersRepository;

    public SimulationParameters saveSimulationParameters(SimulationParameters SimulationParameters){
        return SimulationParametersRepository.save(SimulationParameters);
    }

    public List<SimulationParameters> getSimulationParameters(){
        return SimulationParametersRepository.findAll();
    }

    public SimulationParameters getSimulationParametersById(Integer id){
        return SimulationParametersRepository.findById(id).orElse(null);
    }

    public String deleteSimulationParameters(Integer id){
        SimulationParametersRepository.deleteById(id);
        return "Simulation Parameters are removed!";
    }
    public SimulationParameters updateSimulationParameters(SimulationParameters SimulationParameters){
        SimulationParameters existingSimulationParameters = SimulationParametersRepository
                .findById(SimulationParameters
                .getParameterId()).
                orElse(null);

        if (existingSimulationParameters == null){
            return null;
        }

        existingSimulationParameters.setInsideTemperature(SimulationParameters.getInsideTemperature());
        existingSimulationParameters.setOutsideTemperature(SimulationParameters.getOutsideTemperature());
        existingSimulationParameters.setDateTime(SimulationParameters.getDateTime());
        existingSimulationParameters.setTimeSpeed(SimulationParameters.getTimeSpeed());
        return SimulationParametersRepository.save(existingSimulationParameters);

    }
}
