package com.fittrack.FitTrack.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Workout {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Workout type is required")
    private String type;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int duration; // in minutes

    @Min(value = 1, message = "Calories burned must be positive")
    private int caloriesBurned;

    @NotNull(message = "Timestamp is required")
    private LocalDateTime timestamp;

    @ManyToOne
    private User user;
}
