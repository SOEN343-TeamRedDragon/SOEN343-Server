package dev.TeamRedDragon.SmartHomeSimulator.Room;

import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import dev.TeamRedDragon.SmartHomeSimulator.SmartElement.SmartElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/RoomController")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public List<Room> getRooms(){
        return roomService.getRooms();
    }

    @PostMapping("/GetById")
    public Room getRoomById(@RequestBody Map<String, String> data) {
        String roomId = data.get("roomId");
        return roomService.getRoomById(Integer.parseInt(roomId));
    }

    @PostMapping("/ToggleRoom")
    public Room toggleAllElementsInRoomByRoomIdAndElementType(@RequestBody Map<String, String> data) {
        String roomId = data.get("roomId");
        String elementType = data.get("elementType");
        return roomService.toggleAllElementsInRoomByRoomIdAndElementType(Integer.parseInt(roomId), elementType);
    }

    @PostMapping("/ChangeUserLocation")
    public Home changeUserLocationByUserNameAndRoomId(@RequestBody Map<String, String> data) {
        String userName = data.get("userName");
        String newRoomId = data.get("newRoomId");
        String oldRoomId = data.get("oldRoomId");
       roomService.changeUserLocationByNewIdOldIdAndUserName(Integer.parseInt(newRoomId),
               Integer.parseInt(oldRoomId), userName);
       return Home.getHome();
    }

    @PostMapping("/RemoveUserFromRoom")
    public Room removeUserFromRoomByRoomIdAndUserName(@RequestBody Map<String, String> data) {
        String userName = data.get("userName");
        String roomId = data.get("roomId");
        return roomService.removeUserFromRoomByRoomIdAndUserName(Integer.parseInt(roomId), userName);
    }

    @PostMapping("AddUserToRoom")
    public Room addUserToRoomByRoomIdAndUserName(@RequestBody Map<String, String> data) {
        String userName = data.get("userName");
        String roomId = data.get("roomId");
        return roomService.addUserToRoomByRoomIdAndUserName(Integer.parseInt(roomId), userName);
    }
}
