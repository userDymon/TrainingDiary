package com.example.trainingdiary.dto;

import java.time.LocalDate;

public class TrainingRequestDTO {
    private Long userId;
    private Long exerciseId;
    private LocalDate date;

    // Гетери
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