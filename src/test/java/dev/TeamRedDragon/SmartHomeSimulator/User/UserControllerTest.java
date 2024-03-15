package dev.TeamRedDragon.SmartHomeSimulator.User;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getUsers() throws Exception {

        //Act
        ResultActions result = mockMvc.perform(get("/User"));

        //Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[?($.id)]").exists())
                .andExpect(jsonPath("$[?($.userName)]").exists());

    }

    @Test
    void getUserByUserName() {
    }

    @Test
    void addUser() {
    }

    @Test
    void authenticateUser() {
    }

    @Test
    void updateUser() {
    }
}