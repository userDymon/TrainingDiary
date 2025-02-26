package com.example.trainingdiary.repository;

import com.example.trainingdiary.entity.WorkoutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutPlanRepository extends JpaRepository<WorkoutPlan, Long> {
}
