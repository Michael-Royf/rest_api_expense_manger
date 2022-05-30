package com.michael.controller;

import com.michael.entity.User;
import com.michael.entity.UserModel;
import com.michael.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/profile")
    public ResponseEntity<User> getUserById() {
        return new ResponseEntity<>(userService.getUserProfile(), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<User> updateUser(@RequestBody UserModel userModel) {
        return new ResponseEntity<>(userService.updateUser(userModel), HttpStatus.OK);

    }

    @DeleteMapping("/deactivate")
    public ResponseEntity<HttpStatus> deleteUser() {
        userService.deleteUser();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
