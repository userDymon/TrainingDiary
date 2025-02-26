package com.example.trainingdiary.entity;


import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String muscleGroup;

}