package com.example.reservationsystem.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Reservation {
    private Long id;
    private Long roomId;
    private String employeeId;
    private Boolean reoccurring;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;
    private String lastUpdateBy;
    private LocalDateTime lastUpdateTime;
}
