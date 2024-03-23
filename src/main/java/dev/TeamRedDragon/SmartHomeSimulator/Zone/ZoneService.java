package dev.TeamRedDragon.SmartHomeSimulator.Zone;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZoneService {

    private final List<Zone> zones = new ArrayList<>();

    public Zone createZone(double zoneId, double amTemp, double pmTemp, double nightTemp) {
        for (Zone zone : zones) {
            if (zone.getZoneId() == zoneId) {
                throw new IllegalArgumentException("Zone with ID " + zoneId + " already exists.");
            }
        }
        Zone zone = new Zone(zoneId, amTemp, pmTemp, nightTemp);
        zones.add(zone);
        return zone;
    }

    public Zone addRoomToZoneByZoneIdAndRoomId(int zoneId, int roomId) {
        Zone targetZone = null;
        for (Zone zone : zones) {
            if (zone.getZoneId() == zoneId) {
                targetZone = zone;
                break;
            }
        }
        if (targetZone == null) {
            throw new IllegalArgumentException("Zone with ID " + zoneId + " does not exist.");
        }
        targetZone.addRoom(roomId);
        return targetZone;
    }
}
