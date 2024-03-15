package dev.TeamRedDragon.SmartHomeSimulator.SimulationParameters;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class SimulationParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer parameterId;
    private Double insideTemperature;
    private Double outsideTemperature;
    private LocalDate dateTime;
    private Double timeSpeed;

    public SimulationParameters(Double insideTemperature,
                                Double outsideTemperature,
                                LocalDate dateTime,
                                Double timeSpeed)
    {
        this.insideTemperature = insideTemperature;
        this.outsideTemperature = outsideTemperature;
        this.dateTime = dateTime;
        this.timeSpeed = timeSpeed;
    }

    public SimulationParameters() {

    }

    public Double getInsideTemperature() {
        return insideTemperature;
    }

    public Double getOutsideTemperature() {
        return outsideTemperature;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }


    public Double getTimeSpeed() {
        return timeSpeed;
    }

    public Integer getParameterId() { return parameterId;}

    public void setInsideTemperature(Double insideTemperature) {
        this.insideTemperature = insideTemperature;
    }

    public void setOutsideTemperature(Double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public void setTimeSpeed(Double timeSpeed) {
        this.timeSpeed = timeSpeed;
    }

    public void setParameterId(Integer id) {
        this.parameterId = id;
    }

    @Override
    public String toString() {
        return "SimulationParameters{" +
                "insideTemperature=" + insideTemperature +
                ", outsideTemperature=" + outsideTemperature +
                ", dateTime=" + dateTime +
                ", timeSpeed=" + timeSpeed +
                '}';
    }
}
