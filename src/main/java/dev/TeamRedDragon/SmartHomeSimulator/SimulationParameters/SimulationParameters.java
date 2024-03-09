package dev.TeamRedDragon.SmartHomeSimulator.SimulationParameters;

import java.sql.Time;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.LocalDate;

public class SimulationParameters {

    private Double insideTemperature;
    private Double outsideTemperature;
    private LocalDate simulationDate;
    private Clock clock;
    private Double timeSpeed;

    public SimulationParameters(Double insideTemperature,
                                Double outsideTemperature,
                                LocalDate simulationDate,
                                Clock clock,
                                Double timeSpeed)
    {
        this.insideTemperature = insideTemperature;
        this.outsideTemperature = outsideTemperature;
        this.simulationDate = simulationDate;
        this.clock = clock;
        this.timeSpeed = timeSpeed;
    }

    public Double getInsideTemperature() {
        return insideTemperature;
    }

    public Double getOutsideTemperature() {
        return outsideTemperature;
    }

    public LocalDate getSimulationDate() {
        return simulationDate;
    }

    public Clock getClock() {
        return clock;
    }

    public Double getTimeSpeed() {
        return timeSpeed;
    }

    public void setInsideTemperature(Double insideTemperature) {
        this.insideTemperature = insideTemperature;
    }

    public void setOutsideTemperature(Double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public void setSimulationDate(LocalDate simulationDate) {
        this.simulationDate = simulationDate;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public void setTimeSpeed(Double timeSpeed) {
        this.timeSpeed = timeSpeed;
    }

    @Override
    public String toString() {
        return "SimulationParameters{" +
                "insideTemperature=" + insideTemperature +
                ", outsideTemperature=" + outsideTemperature +
                ", simulationDate=" + simulationDate +
                ", clock=" + clock +
                ", timeSpeed=" + timeSpeed +
                '}';
    }
}
