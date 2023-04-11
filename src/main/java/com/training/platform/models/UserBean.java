package com.training.platform.models;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class UserBean {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private Integer age;
    private String address;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}