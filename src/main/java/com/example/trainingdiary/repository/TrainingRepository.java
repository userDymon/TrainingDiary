package com.example.trainingdiary.repository;

import com.example.trainingdiary.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByUserId(Long userId); // Отримати тренування за ID користувача
    List<Training> findByExerciseId(Long exerciseId); // Отримати тренування за ID вправи
}