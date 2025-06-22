package com.example.QuoraAppClone.controllers;

import com.example.QuoraAppClone.dtos.UserDTO;
import com.example.QuoraAppClone.models.User;
import com.example.QuoraAppClone.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    private UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build()); // If the user is present (Optional contains a value), map(ResponseEntity::ok) converts it to ResponseEntity.ok(user) — a 200 OK response with the user in the body.
        //.orElseGet(() -> ResponseEntity.notFound().build());
        //If the user is not found (Optional is empty), it returns ResponseEntity.notFound().build() — a 404 Not Found response.
    }

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
         userService.deleteUser(id);
         return ResponseEntity.notFound().build();
    }

    @PostMapping("/{userId}/followTag/{tagId}")
    public ResponseEntity<Void> followTag(@PathVariable Long userId ,@PathVariable Long tagId){
        userService.followTag(userId,tagId);
        return ResponseEntity.noContent().build();
    }
}
