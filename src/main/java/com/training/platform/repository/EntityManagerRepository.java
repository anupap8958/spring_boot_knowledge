package com.training.platform.repository;

import com.training.platform.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityManagerRepository extends JpaRepository<User, Integer> {
}
