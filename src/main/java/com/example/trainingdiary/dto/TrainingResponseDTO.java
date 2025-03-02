package com.example.trainingdiary.dto;

import java.time.LocalDate;

public class TrainingResponseDTO {
    private Long id;
    private Long userId;
    private Long exerciseId;
    private LocalDate date;

    // Гетери
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public LocalDate getDate() {
        return date;
    }

    // Сетери
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}