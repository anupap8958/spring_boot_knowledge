package com.training.platform.service;

import com.training.platform.entity.User;
import com.training.platform.repository.EntityManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EntityManagerService {
    @Autowired
    private EntityManagerRepository entityManagerRepository;

    public List<User> getAllUsers() {
        List<User> users = entityManagerRepository.findAll();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return users;
    }

    public User getUserById(int id) {
        User user = entityManagerRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public void createUser(User user) {
        try {
            user.setAddress(user.getAddress().toLowerCase());
            entityManagerRepository.save(user);
        } catch (Exception e) {
            throw e;
        }
    }

    public User updateUser(int id, User user) {
        User existingUser = entityManagerRepository.findById(id).orElse(null);
        try {
            if (existingUser != null) {
                existingUser.setName(user.getName());
                existingUser.setEmail(user.getEmail());
                existingUser.setAge(user.getAge());
                existingUser.setAddress(user.getAddress().toLowerCase());
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            entityManagerRepository.save(existingUser);
            return existingUser;
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteUser(int id) {
        try {
            entityManagerRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}