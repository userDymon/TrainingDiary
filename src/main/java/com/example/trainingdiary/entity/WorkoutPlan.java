package com.example.trainingdiary.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class WorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "workout_plan_exercises",
            joinColumns = @JoinColumn(name = "workout_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise> exercises;

    // Getters and Setters
}