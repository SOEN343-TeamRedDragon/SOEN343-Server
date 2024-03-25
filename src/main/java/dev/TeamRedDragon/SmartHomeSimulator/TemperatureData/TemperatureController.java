package dev.TeamRedDragon.SmartHomeSimulator.TemperatureData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TemperatureController")
public class TemperatureController {

    @Autowired
    TemperatureDataService temperatureDataService;

    @GetMapping("/GetOutdoorTemp")
    public double getTemperatureFromClockAndTemperatureData(){
        return temperatureDataService.getTemperatureFromClockAndTemperatureData();
    }
}
