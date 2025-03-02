package com.example.trainingdiary.dto;

import java.time.LocalDate;

public class TrainingRequestDTO {
    private Long exerciseId;
    private LocalDate date;


    public Long getExerciseId() {
        return exerciseId;
    }

    public LocalDate getDate() {
        return date;
    }

    // Сетери

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}