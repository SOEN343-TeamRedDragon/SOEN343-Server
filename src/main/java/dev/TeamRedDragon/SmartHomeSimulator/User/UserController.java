package dev.TeamRedDragon.SmartHomeSimulator.User;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
         
    }

    @PostMapping("/AddUser")
    public String addUser(@RequestBody User user){
        userService.saveUser(user);
        return "User is sucessfully added!";
    }

}
