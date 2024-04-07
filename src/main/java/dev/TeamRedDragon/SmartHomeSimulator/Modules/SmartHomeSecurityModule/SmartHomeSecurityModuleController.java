package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule;

import dev.TeamRedDragon.SmartHomeSimulator.State.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Security")
public class SmartHomeSecurityModuleController {

    @Autowired
    private SmartHomeSecurityModuleService smartHomeSecurityModuleService;

    @GetMapping("/")
    public State getSmartHomeSecurityState() {
        return smartHomeSecurityModuleService.getState();
    }

    @PostMapping("/AwayOn")
    public ResponseEntity<Object> turnOnAwayMode() {
        smartHomeSecurityModuleService.turnOnAwayMode();
        return ResponseEntity.ok().body("Away Mode On.");
    }

    @PostMapping("/AwayOff")
    public ResponseEntity<Object> turnOffAwayMode() {
        smartHomeSecurityModuleService.turnOffAwayMode();
        return ResponseEntity.ok().body("Away Mode Off.");
    }
}
