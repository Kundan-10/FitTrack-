package com.fittrack.FitTrack.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ChallengeParticipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(value = 0, message = "Progress must be zero or more")
    private int progress;

    private int totalCaloriesBurned;

    private boolean completed;

    private LocalDateTime joinedAt;

    private LocalDateTime completedAt;

    private String progressUnit;

    @ManyToOne
    private User user;

    @ManyToOne
    private Challenge challenge;

}
