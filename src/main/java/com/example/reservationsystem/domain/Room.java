package com.example.reservationsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Room {
    private Long id;
    private Long buildingId;
    private Integer priority;
    private String status;
    private Integer floor;
    private String name;
    private Integer seats;
    private Boolean videoConferenceHolder;
}
