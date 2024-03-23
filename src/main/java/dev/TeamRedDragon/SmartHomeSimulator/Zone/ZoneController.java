package dev.TeamRedDragon.SmartHomeSimulator.Zone;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ZoneController")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping("/CreateZone")
    public Zone createZone(@RequestBody Map<String, Double> data) {
        double zoneId =  data.get("zoneId");
        double amTemp = data.get("amTemp");
        double pmTemp = data.get("pmTemp");
        double nightTemp = data.get("nightTemp");
        return zoneService.createZone(zoneId, amTemp, pmTemp, nightTemp);
    }

    @PostMapping("/AddRoomToZone")
    public Zone addRoomTZoneByZoneIdAndRoomId(@RequestBody Map<String, String> data) {
        String zoneId = data.get("zoneId");
        String roomId = data.get("roomId");
        return zoneService.addRoomToZoneByZoneIdAndRoomId(Integer.parseInt(zoneId), Integer.parseInt(roomId));
    }
}