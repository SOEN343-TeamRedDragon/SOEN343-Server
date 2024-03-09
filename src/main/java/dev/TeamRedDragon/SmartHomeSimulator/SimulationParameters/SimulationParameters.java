package dev.TeamRedDragon.SmartHomeSimulator.SimulationParameters;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Time;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.LocalDate;

@Entity
public class SimulationParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double insideTemperature;
    private Double outsideTemperature;
    private LocalDate simulationDate;
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


    public Double getTimeSpeed() {
        return timeSpeed;
    }

    public Integer getId() { return id;}

    public void setInsideTemperature(Double insideTemperature) {
        this.insideTemperature = insideTemperature;
    }

    public void setOutsideTemperature(Double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public void setSimulationDate(LocalDate simulationDate) {
        this.simulationDate = simulationDate;
    }

    public void setTimeSpeed(Double timeSpeed) {
        this.timeSpeed = timeSpeed;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SimulationParameters{" +
                "insideTemperature=" + insideTemperature +
                ", outsideTemperature=" + outsideTemperature +
                ", simulationDate=" + simulationDate +
                ", timeSpeed=" + timeSpeed +
                '}';
    }
}
