package com.example.trainingdiary.service;

import com.example.trainingdiary.entity.User;
import com.example.trainingdiary.payload.request.SignupRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers(); // Отримати всіх користувачів
    Optional<User> getUserById(Long id); // Отримати користувача за ID
    User createUser(User user); // Створити нового користувача
    User updateUser(Long id, User userDetails); // Оновити дані користувача
    void deleteUser(Long id); // Видалити користувача
    boolean existsByUsername(String username); // Перевірка, чи існує користувач з таким іменем
    User registerUser(SignupRequest signUpRequest); // Реєстрація нового користувача
}