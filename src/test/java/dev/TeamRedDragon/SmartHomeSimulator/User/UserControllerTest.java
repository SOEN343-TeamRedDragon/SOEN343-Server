package dev.TeamRedDragon.SmartHomeSimulator.User;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;

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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    JSONObject obj = new JSONObject();
    String testUserName = "Test";
    @BeforeEach
    public void setUp(){
        obj = new JSONObject();
    }

    @Test
    @Order(1)
    void addUser() throws Exception {
        // Arrange
        obj.put("userName",testUserName);
        obj.put("name","Test");
        obj.put("password","Test");
        obj.put("location","Outside");
        obj.put("role","Stranger");

        // Act
        ResultActions result = mockMvc.perform((post("/User/AddUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj))));

        // Assert
        result.andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(2)
    void getUsers() throws Exception {

        // Act
        ResultActions result = mockMvc.perform(get("/User"));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[?($.id)]").exists())
                .andExpect(jsonPath("$[?($.userName)]").exists());
    }

    @Test
    @Order(3)
    void getUsersById() throws Exception {
        // Arrange
        int id = userService.getUserByUserName(testUserName).getId();
        obj.put("userId",id );

        // Act
        ResultActions result = mockMvc.perform(post("/User/GetUserById")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(testUserName))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    @Order(4)
    void getUserByUserName() throws Exception {
        // Arrange
        obj.put("userName",testUserName);


        // Act
        ResultActions result = mockMvc.perform(post("/User/GetUserByUserName")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(obj.get("userName")))
                .andExpect((jsonPath("$.id")).exists());

    }

    @Test
    @Order(5)
    void authenticateUser() throws Exception {
        // Arrange
        obj.put("userName",testUserName);
        obj.put("password","Test");

        // Act
        ResultActions result = mockMvc.perform(post("/User/AuthenticateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(obj.get("userName")));
    }

    @Test
    @Order(6)
    void updateUser() throws Exception {
        // Arrange
        int id = userService.getUserByUserName(testUserName).getId();

        obj.put("userName","Test");
        obj.put("name","TestUpdated");
        obj.put("password","TestUpdated");
        obj.put("location","Garage");
        obj.put("role", "Stranger");
        obj.put("id", id);

        // Act
        ResultActions result = mockMvc.perform(patch("/User/UpdateUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk());
    }

    @Test
    @Order(7)
    void deleteUserById() throws  Exception {
        // Arrange
        int id = userService.getUserByUserName(testUserName).getId();
        obj.put("id", id);

        // Act
        ResultActions result = mockMvc.perform(delete("/User/DeleteUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(obj)));

        // Assert
        result.andExpect(status().isOk());
    }
}