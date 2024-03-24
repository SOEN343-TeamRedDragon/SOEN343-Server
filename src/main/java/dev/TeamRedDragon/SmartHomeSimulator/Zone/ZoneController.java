package dev.TeamRedDragon.SmartHomeSimulator.Zone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List; 
import java.util.Map;

@RestController
@RequestMapping("/ZoneController")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;
 
    @PostMapping("/CreateZone")
    public Zone createZone(@RequestBody Map<String, String> data) {
        double zoneId = Double.parseDouble(data.get("zone"));
        double amTemp = Double.parseDouble(data.get("AM"));
        double pmTemp = Double.parseDouble(data.get("PM"));
        double nightTemp = Double.parseDouble(data.get("NIGHT"));
        return zoneService.createZone(zoneId, amTemp, pmTemp, nightTemp);
    }

    @PostMapping("/AddRoomToZone") 
    public Zone addRoomToZoneByZoneIdAndRoomId(@RequestBody Map<String, Integer> data) {
        int zoneId = data.get("zoneId"); 
        int roomId = data.get("roomId");
        return zoneService.addRoomToZoneByZoneIdAndRoomId(zoneId, roomId);
    }

    @PostMapping("/RemoveRoomFromZone")
    public Zone removeRoomFromZoneByZoneIdAndRoomId(@RequestBody Map<String, Integer> data) {
        int zoneId = data.get("zoneId");
        int roomId = data.get("roomId");
        return zoneService.removeRoomFromZoneByZoneIdAndRoomId(zoneId, roomId);
    }

    @GetMapping("/GetZones")
    public List<Zone> getZones() {
        return zoneService.getZonesDetailedInfo();
    }

    @PutMapping("/UpdateZoneTemperature")
    public Zone updateZoneTemperature(@RequestBody Map<String, Object> data) {
        int zoneId = (int) data.get("zoneId"); 
        String timeOfDay = (String) data.get("timeOfDay");
        double newTemperature = ((Number) data.get("newTemperature")).doubleValue();
        return zoneService.updateZoneTemperature(zoneId, timeOfDay, newTemperature);
}
}
