package com.example.reservationsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Room {
    private Long id;
    private Long buildingId;
    private Long cityId;
    private String building;
    private String city;
    private Integer priority;
    private String status;
    private Integer floor;
    private String name;
    private Integer seats;
    private Boolean videoConferenceHolder;

    public static final String AVAILABLE = "AVAILABLE";
    public static final String OUT_OF_SERVICE = "OUT_OF_SERVICE";
}
