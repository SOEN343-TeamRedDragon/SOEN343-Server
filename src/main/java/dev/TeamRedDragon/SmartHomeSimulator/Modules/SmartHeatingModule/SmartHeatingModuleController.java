package dev.TeamRedDragon.SmartHomeSimulator.Modules.SmartHeatingModule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Heating")
public class SmartHeatingModuleController {

    @Autowired
    private SmartHeatingModuleService smartHeatingModuleService;


}
