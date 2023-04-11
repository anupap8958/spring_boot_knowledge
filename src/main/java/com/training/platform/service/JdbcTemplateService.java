package com.training.platform.service;

import com.training.platform.models.UserBean;
import com.training.platform.repository.JdbcTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
public class JdbcTemplateService {
    @Autowired
    private JdbcTemplateRepository jdbcTemplateRepository;

    public List<UserBean> getAllUsers() {
        try {
            List<UserBean> response = jdbcTemplateRepository.getAllUsers();
            if (response.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            }
            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    public UserBean getUserById(String id) {
        try {
            UserBean user = jdbcTemplateRepository.getUserById(id);
            if (user == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            return user;
        } catch (Exception e) {
            throw e;
        }
    }

    public void createUser(UserBean user) {
        try {
            int result = jdbcTemplateRepository.createUser(user);

            if (result == 0) {
                log.error("Create failed");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                log.info("createUser: Created user");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateUser(String id, UserBean user) {
        try {
            int result = 0;

            result = jdbcTemplateRepository.updateUser(id, user);

            if (result == 0) {
                log.error("Update failed");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                log.info("updateUser: Updated user");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void deleteUser(String id) {
        try {
            int result = 0;

            result = jdbcTemplateRepository.deleteUser(id);

            if (result == 0) {
                log.error("Delete failed");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                log.info("updateUser: Deleted user");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
