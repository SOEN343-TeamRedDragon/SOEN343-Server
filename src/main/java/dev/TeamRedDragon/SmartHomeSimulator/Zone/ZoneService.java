package dev.TeamRedDragon.SmartHomeSimulator.Zone;

import dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule.SmartHeatingModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.Room.Room;
import dev.TeamRedDragon.SmartHomeSimulator.Room.RoomService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZoneService {
    
    @Autowired RoomService roomService;

    private SmartHeatingModule smartHeatingModule = SmartHeatingModule.getSmartHeatingModule();

    public Zone createZone(double zoneId, double amTemp, double pmTemp, double nightTemp) {
        Zone zone = new Zone(zoneId, amTemp, pmTemp, nightTemp);
        smartHeatingModule.addZone(zone);
        return zone;
    }

    public Zone addRoomToZoneByZoneIdAndRoomId(int zoneId, int roomId) {
        // Initialize a Zone object to hold the target zone
        Zone targetZone = null;

        // Iterate over all zones in the smart heating module
        for (Zone zone : smartHeatingModule.getZones()) {
            // If the current zone contains the room, remove the room from this zone
            if (zone.getRoomList().contains(roomId)) {
                zone.removeRoom(roomId);
            }
            // If the current zone's ID matches the target zone ID, store a reference to it
            if (zone.getZoneId() == zoneId) {
                targetZone = zone;
            }
        }

        // If the target zone was found, add the room to it and update the room's zone ID
        if (targetZone != null) {
            targetZone.addRoom(roomId);
            roomService.setZoneIdByRoomId(zoneId, roomId);
        }

        // Return the target zone (or null if it wasn't found)
        return targetZone;
}

    public Zone removeRoomFromZoneByZoneIdAndRoomId(double zoneId, int roomId) {
        for (Zone zone : smartHeatingModule.getZones()) {
            if (zone.getZoneId() == zoneId) {
                zone.removeRoom(roomId);
                roomService.setZoneIdByRoomId(1, roomId);
                return zone;
            }
        }
        return null;
    }

    public List<Zone> getZones() {
        if (smartHeatingModule.getZones().isEmpty()) {
            Home home = Home.getHome();
            Zone zone = new Zone(1, 19, 18, 17);
            for (Room room : home.getRoomList()) {
                zone.addRoom(room.getRoomId());
            }
            smartHeatingModule.addZone(zone);
        }
        return smartHeatingModule.getZones();
    }

    public Zone updateZoneTemperature(int zoneId, String timeOfDay, double newTemperature) {
        Zone zoneToUpdate = null;
        for (Zone zone : smartHeatingModule.getZones()) {
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
