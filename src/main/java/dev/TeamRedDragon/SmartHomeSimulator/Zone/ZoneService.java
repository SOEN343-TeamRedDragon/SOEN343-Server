package dev.TeamRedDragon.SmartHomeSimulator.Zone;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZoneService {

    private List<Zone> zones = new ArrayList<>();

    public Zone createZone(double zoneId, double amTemp, double pmTemp, double nightTemp) {
        Zone zone = new Zone(zoneId, amTemp, pmTemp, nightTemp);
        zones.add(zone);
        return zone;
    }

    public Zone addRoomToZoneByZoneIdAndRoomId(int zoneId, int roomId) {
        for (Zone zone : zones) {
            if (zone.getZoneId() == zoneId) {
                zone.addRoom(roomId);
                return zone;
            }
        }
        return null;
    }
}
