package com.example.trainingdiary.repository;

import com.example.trainingdiary.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}