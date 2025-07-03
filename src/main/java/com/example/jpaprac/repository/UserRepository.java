package com.example.jpaprac.repository;

import com.example.jpaprac.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
