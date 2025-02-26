package com.example.trainingdiary.repository;

import com.example.trainingdiary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Пошук користувача за іменем
    boolean existsByUsername(String username); // Перевірка, чи існує користувач з таким іменем
}