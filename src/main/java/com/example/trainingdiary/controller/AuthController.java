package com.example.trainingdiary.controller;

import com.example.trainingdiary.entity.User;
import com.example.trainingdiary.payload.request.LoginRequest;
import com.example.trainingdiary.payload.request.SignupRequest;
import com.example.trainingdiary.payload.response.JwtResponse;
import com.example.trainingdiary.service.UserService;
import com.example.trainingdiary.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken((org.springframework.security.core.userdetails.User) authentication.getPrincipal());

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        // Перевірка, чи існує користувач з таким іменем
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        // Реєстрація нового користувача
        User user = userService.registerUser(signUpRequest);
        return ResponseEntity.ok("User registered successfully!");
    }
}