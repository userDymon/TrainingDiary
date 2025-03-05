package com.example.trainingdiary.dto;

import lombok.Data;

import java.util.List;

@Data
public class WorkoutPlanRequestDTO {
    private String name;
    private String description;
    private List<Long> exerciseIds; // Список ID упражнений

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getExerciseIds() {
        return exerciseIds;
    }

    public void setExerciseIds(List<Long> exerciseIds) {
        this.exerciseIds = exerciseIds;
    }
}