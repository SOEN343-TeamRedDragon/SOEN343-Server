package dev.TeamRedDragon.SmartHomeSimulator.SimulationParameters;

import dev.TeamRedDragon.SmartHomeSimulator.User.User;

import java.util.List;

public class SimulationParameterService {

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
                .getId()).
                orElse(null);

        if (existingSimulationParameters == null){
            return null;
        }

        existingSimulationParameters.setInsideTemperature(SimulationParameters.getInsideTemperature());
        existingSimulationParameters.setOutsideTemperature(SimulationParameters.getOutsideTemperature());
        existingSimulationParameters.setSimulationDate(SimulationParameters.getSimulationDate());
        existingSimulationParameters.setClock(SimulationParameters.getClock());
        existingSimulationParameters.setTimeSpeed(SimulationParameters.getTimeSpeed());
        return SimulationParametersRepository.save(existingSimulationParameters);

    }

}
