package com.example.trainingdiary.dto;

import java.io.Serializable;
import java.util.Date;

public record UserDto(
        Long id,
        boolean deleted,
        Date createdAt,
        Date updatedAt,
        String username,
        String password
) implements Serializable {
}