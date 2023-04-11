package com.training.platform.repository;

import com.training.platform.models.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class JdbcTemplateRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserBean> getAllUsers() {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM USERS");

        List<UserBean> response = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(UserBean.class));

        return response;
    }

    public UserBean getUserById(String id) {
        StringBuilder sql = new StringBuilder();
        List<Object> param = new ArrayList<>();

        sql.append("SELECT * FROM `users` WHERE id = ? \n");
        param.add(Long.parseLong(id));

        List<UserBean> response = jdbcTemplate.query(sql.toString(), param.toArray(), new BeanPropertyRowMapper<>(UserBean.class));

        return response.isEmpty() ? null : response.get(0);
    }

    public int createUser(UserBean user) {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();

        sql.append("INSERT INTO `users`(`id`, `name`, `email`, `age`, `address`, `created_at`) \r\n");
        sql.append("VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP) \n");
        params.add(user.getId());
        params.add(user.getName());
        params.add(user.getEmail());
        params.add(user.getAge());
        params.add(user.getAddress());

        Integer result = jdbcTemplate.update(sql.toString(), params.toArray());

        return result == null ? 0 : result;
    }

    public int updateUser(String id, UserBean user) {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();
        UserBean userOld;
        try {
            userOld = getUserById(id);
            if (userOld == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw e;
        }

        sql.append("UPDATE `users` SET `name`= ?,`email`= ?,`age`= ?,`address`= ?, `updated_at`= CURRENT_TIMESTAMP \r\n");
        params.add(user.getName());
        params.add(user.getEmail());
        params.add(user.getAge() == null ? userOld.getAge() : user.getAge());
        params.add(user.getAddress() == null ? userOld.getAddress() : user.getAddress());
        sql.append("WHERE id = ? \r\n");
        params.add(id);

        Integer result = jdbcTemplate.update(sql.toString(), params.toArray());

        return result == null ? 0 : result;
    }

    public int deleteUser(String id) {
        StringBuilder sql = new StringBuilder();
        List<Object> param = new ArrayList<>();

        sql.append("DELETE FROM `users` WHERE id = ? \r\n");
        param.add(id);

        Integer result = jdbcTemplate.update(sql.toString(), param.toArray());

        return result == null ? 0 : result;
    }
}