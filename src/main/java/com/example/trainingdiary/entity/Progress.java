package com.example.trainingdiary.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    private double value; // Наприклад, вага або кількість повторень
    private LocalDate date;

}