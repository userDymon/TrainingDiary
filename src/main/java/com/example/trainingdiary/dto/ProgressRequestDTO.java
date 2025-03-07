package com.example.trainingdiary.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProgressRequestDTO {
    private Long exerciseId;
    private double value;
    private LocalDate date;

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
