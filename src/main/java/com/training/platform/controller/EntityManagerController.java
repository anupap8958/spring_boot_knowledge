package com.training.platform.controller;

import com.training.platform.entity.User;
import com.training.platform.service.EntityManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/entity")
public class EntityManagerController {
    @Autowired
    private EntityManagerService entityManagerService;

    @GetMapping(value ="")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(entityManagerService.getAllUsers());
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(entityManagerService.getUserById(Integer.parseInt(id)));
    }

    @PostMapping(value ="")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        entityManagerService.createUser(user);
        return ResponseEntity.created(null).build();
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user) {
        return ResponseEntity.ok(entityManagerService.updateUser(Integer.parseInt(id), user));
    }

    @DeleteMapping(value ="/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        entityManagerService.deleteUser(Integer.parseInt(id));
        return ResponseEntity.ok("Delete id: " + id + " Successfully");
    }
}
