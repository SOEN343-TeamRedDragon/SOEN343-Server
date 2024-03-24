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

    public Zone addRoomToZoneByZoneIdAndRoomId(double zoneId, int roomId) {
        for (Zone zone : zones) {
            if (zone.getZoneId() == zoneId) {
                zone.addRoom(roomId);
                return zone;
            }
        }
        return null;
    }

    public Zone removeRoomFromZoneByZoneIdAndRoomId(double zoneId, int roomId) {
        for (Zone zone : zones) {
            if (zone.getZoneId() == zoneId) {
                zone.removeRoom(roomId);
                return zone;
            }
        }
        return null;
    }

    public List<Zone> getZonesDetailedInfo() {
        List<Zone> detailedZoneInfo = new ArrayList<>();
        for (Zone zone : zones) {
            detailedZoneInfo.add(zone);
        }
        return detailedZoneInfo;
    }

    public Zone updateZoneTemperature(int zoneId, String timeOfDay, double newTemperature) {
        Zone zoneToUpdate = null;
        for (Zone zone : zones) {
            if (zone.getZoneId() == zoneId) {
                zoneToUpdate = zone;
                break;
            }
        }
        if (zoneToUpdate == null) {
            return null;
        }

        switch (timeOfDay) {
            case "AM":
                zoneToUpdate.setAmTemp(newTemperature);
                break;
            case "PM":
                zoneToUpdate.setPmTemp(newTemperature);
                break;
            case "NIGHT":
                zoneToUpdate.setNightTemp(newTemperature);
                break;
            default:
                return null;
        }
        return zoneToUpdate;
    }
}
