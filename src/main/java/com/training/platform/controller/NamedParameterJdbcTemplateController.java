package com.training.platform.controller;

import com.training.platform.models.UserBean;
import com.training.platform.service.NamedParameterJdbcTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/namedParameterJdbcTemplate")
public class NamedParameterJdbcTemplateController {
    @Autowired
    private NamedParameterJdbcTemplateService jdbcTemplateService;

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(jdbcTemplateService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(jdbcTemplateService.getUserById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserBean user) {
        jdbcTemplateService.createUser(user);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserBean user) {
        jdbcTemplateService.updateUser(id, user);
        return ResponseEntity.ok("Updated " + id + " user");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        jdbcTemplateService.deleteUser(id);
        return ResponseEntity.ok("Deleted " + id + " user");
    }
}
