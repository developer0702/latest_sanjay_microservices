package com.userService.controller;

import com.userService.dto.ResponseData;
import com.userService.entity.User;
import com.userService.event.UserCreatedEvent;
import com.userService.service.UserEventProducer;
import com.userService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserEventProducer userEventProducer;

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User userServiceUser = userService.createUser(user);
        return new ResponseEntity<>(userServiceUser, HttpStatus.CREATED);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User userById = userService.getUserById(userId);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(User user, @PathVariable Long userId) {
        User updateUserById = userService.updateUserById(user, userId);
        return new ResponseEntity<>(updateUserById, HttpStatus.OK);
    }

    // get user with department
    @GetMapping("/{userId}")
    public ResponseData getUserWithDepartment(@PathVariable("userId") Long userId) {
        return userService.getUserWithDepartment(userId);
    }


    // kafka  event producer
    @PostMapping
    public String createUser(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String email) {

        UserCreatedEvent event =
                new UserCreatedEvent(id, name, email);

        userEventProducer.sendUserCreatedEvent(event);

        return "User created and Kafka event published";
    }
}
