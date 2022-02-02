package com.hgutierrezg.training.model;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@Builder
public class Timesheet {
    private Long id;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private Boolean approved;

    private String client;

    public Long getTotalHours() {
        return Duration.between(startDateTime, endDateTime).toHours();
    }
}
