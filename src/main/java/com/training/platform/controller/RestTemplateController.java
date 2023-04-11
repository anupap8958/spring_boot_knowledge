package com.training.platform.controller;

import com.training.platform.models.UserRestTemplate;
import com.training.platform.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restTemplate")
public class RestTemplateController {
    @Autowired
    private RestTemplateService restTemplateService;

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(restTemplateService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(restTemplateService.getUserById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserRestTemplate user) {
        return restTemplateService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserRestTemplate user) {
        return ResponseEntity.ok(restTemplateService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        return ResponseEntity.ok(restTemplateService.deleteUser(id));
    }
}
