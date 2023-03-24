package com.example.jmpdto.repository;

import com.example.jmpdto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
