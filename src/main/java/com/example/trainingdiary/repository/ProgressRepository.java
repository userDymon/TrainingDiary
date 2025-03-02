package com.example.trainingdiary.repository;

import com.example.trainingdiary.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {
    List<Progress> findByUserId(Long userId); // Отримати прогрес за ID користувача
    List<Progress> findByExerciseId(Long exerciseId); // Отримати прогрес за ID вправи
}