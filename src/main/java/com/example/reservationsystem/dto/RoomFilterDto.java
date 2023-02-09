package com.example.reservationsystem.dto;

import com.example.reservationsystem.dto.validation.MinSeatNotBiggerThanMaxSeat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
@MinSeatNotBiggerThanMaxSeat
public class RoomFilterDto {
    @Pattern(regexp = NAME_REGEXP, message = "Room name has to match regular expression: " + NAME_REGEXP)
    private String roomName;
    @Pattern(regexp = BUILDING_REGEXP, message = "Building name has to match regular expression: " + BUILDING_REGEXP)
    private String building;
    @Pattern(regexp = NAME_REGEXP, message = "City has to match regular expression: " + NAME_REGEXP)
    private String city;
    private boolean floor1;
    private boolean floor2;
    private boolean floor3;
    private boolean floor4;
    private List<Boolean> floors;
    @Min(value = 2, message = "Number of seats cannot be less than 2")
    @Max(value = 32, message = "Number of seats cannot exceed 32")
    private int minSeats;
    @Min(value = 2, message = "Number of seats cannot be less than 2")
    @Max(value = 32, message = "Number of seats cannot exceed 32")
    private int maxSeats;
    @NotBlank(message = "Status cannot be blank")
    @Pattern(regexp = STATUS_REGEXP, message = "Status has to match regular expression: " + STATUS_REGEXP)
    private String status;
    @NotBlank(message = "Video conference holder cannot be blank")
    @Pattern(regexp = VCH_REGEXP, message = "Video conference holder has to match regular expression: " + VCH_REGEXP)
    private String videoConferenceHolder;

    private static final String STATUS_REGEXP = "^(all|available|unavailable)$";
    private static final String VCH_REGEXP = "^(all|yes|no)$";
    private static final String NAME_REGEXP = "^[a-zA-Z0-9\\s]*$";
    private static final String BUILDING_REGEXP = "^[a-zA-Z0-9\\s]*$";
}
