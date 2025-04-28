package com.fittrack.FitTrack.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fittrack.FitTrack.enums.ChallengeStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotBlank(message = "Goal type is required")
    private String goalType; // calories, distance, etc.

    @Min(value = 1, message = "Goal target must be a positive value")
    private int goalTarget;

    @Enumerated(EnumType.STRING)
    private ChallengeStatus status;

    @OneToMany(mappedBy = "challenge")
    @JsonBackReference
    private List<ChallengeParticipation> participations;
}
