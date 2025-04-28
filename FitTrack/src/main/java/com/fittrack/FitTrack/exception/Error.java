package com.fittrack.FitTrack.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Error {
    private LocalDateTime timestamp;
    private String message;
    private String description;
}
