package dev.TeamRedDragon.SmartHomeSimulator.Zone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ZoneController")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping("/CreateZone")
    public Zone createZone(@RequestBody Map<String, Double> data) {
        double zoneId = data.get("zoneId");
        double amTemp = data.get("amTemp");
        double pmTemp = data.get("pmTemp");
        double nightTemp = data.get("nightTemp");
        return zoneService.createZone(zoneId, amTemp, pmTemp, nightTemp);
    }

    @PostMapping("/AddRoomToZone") 
    public Zone addRoomToZoneByZoneIdAndRoomId(@RequestBody Map<String, Integer> data) {
        int zoneId = data.get("zoneId");
        int roomId = data.get("roomId");
        return zoneService.addRoomToZoneByZoneIdAndRoomId(zoneId, roomId);
    }
}
