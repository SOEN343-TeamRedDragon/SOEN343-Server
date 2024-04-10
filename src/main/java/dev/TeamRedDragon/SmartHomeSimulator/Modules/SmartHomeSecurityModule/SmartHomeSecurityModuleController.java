package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHomeSecurityModule;

import dev.TeamRedDragon.SmartHomeSimulator.State.AwayState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Security")
public class SmartHomeSecurityModuleController {

    @Autowired
    private SmartHomeSecurityModuleService smartHomeSecurityModuleService;

    @GetMapping("/GetState")
    public ResponseEntity<Object> getSmartHomeSecurityState() {
        if(smartHomeSecurityModuleService.getState() instanceof AwayState)
            return ResponseEntity.ok().body("Away");
        return ResponseEntity.ok().body("Active");
    }

    @PostMapping("/TurnAwayOn")
    public ResponseEntity<Object> turnOnAwayMode() {
        smartHomeSecurityModuleService.turnOnAwayMode();
        return ResponseEntity.ok().body("Away Mode On.");
    }

    @PostMapping("/TurnAwayOff")
    public ResponseEntity<Object> turnOffAwayMode() {
        smartHomeSecurityModuleService.turnOffAwayMode();
        return ResponseEntity.ok().body("Away Mode Off.");
    }
}
