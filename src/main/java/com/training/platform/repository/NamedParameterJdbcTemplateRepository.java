package com.training.platform.repository;

import com.training.platform.models.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NamedParameterJdbcTemplateRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<UserBean> getAllUsers() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM users");

        List<UserBean> response = namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(UserBean.class));

        return response;
    }

    public UserBean getUserById(String id) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<>();

        sql.append("SELECT * FROM users WHERE id = :id");
        paramMap.put("id", id);
        List<UserBean> response = namedParameterJdbcTemplate.query(sql.toString(), paramMap, new BeanPropertyRowMapper<>(UserBean.class));

        return response.isEmpty() ? null : response.get(0);
    }

    public int createUser(UserBean user) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<>();

        sql.append("INSERT INTO users (id, name, email, age, address, created_at) \r\n");
        sql.append("VALUES (:id, :name, :email, :age, :address, CURRENT_TIMESTAMP) \r\n");

        paramMap.put("id", user.getId());
        paramMap.put("name", user.getName());
        paramMap.put("email", user.getEmail());
        paramMap.put("age", user.getAge());
        paramMap.put("address", user.getAddress());

        Integer result = namedParameterJdbcTemplate.update(sql.toString(), paramMap);

        return result == null ? 0 : result;
    }

    public int updateUser(String id, UserBean user) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<>();
        UserBean userOld;
        try {
            userOld = getUserById(id);
            if (userOld == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw e;
        }

        sql.append("UPDATE `users` SET `name`=:name, `email`=:email, `age`=:age, `address`=:address, `updated_at`= CURRENT_TIMESTAMP \r\n");
        sql.append("WHERE id = :id \r\n");

        paramMap.put("name", user.getName());
        paramMap.put("email", user.getEmail());
        paramMap.put("age", user.getAge() == null ? userOld.getAge() : user.getAge());
        paramMap.put("address", user.getAddress() == null ? userOld.getAddress() : user.getAddress());
        paramMap.put("id", id);

        Integer result = namedParameterJdbcTemplate.update(sql.toString(), paramMap);

        return result == null ? 0 : result;
    }

    public int deleteUser(String id) {
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<>();

        sql.append("DELETE FROM `users` WHERE id = :id \r\n");
        paramMap.put("id", id);

        Integer result = namedParameterJdbcTemplate.update(sql.toString(), paramMap);

        return result == null ? 0 : result;
    }
}
