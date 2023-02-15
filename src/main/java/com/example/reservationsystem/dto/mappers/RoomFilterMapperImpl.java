package com.example.reservationsystem.dto.mappers;

import com.example.reservationsystem.database.conditions.ConditionBuilder;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.dto.RoomFilterDto;
import com.example.reservationsystem.service.filters.reservations.ReservationTimePeriodRoomIdFilter;
import com.example.reservationsystem.service.filters.rooms.RoomFilter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.reservationsystem.database.DatabaseConstants.BUILDING_PREFIX;
import static com.example.reservationsystem.database.DatabaseConstants.CITY_PREFIX;
import static com.example.reservationsystem.database.DatabaseConstants.ROOM_PREFIX;

@Component
public class RoomFilterMapperImpl implements RoomFilterMapper {
    @Override
    public RoomFilter mapToRoomFilter(RoomFilterDto dto) {
        return RoomFilter.builder()
                .building(ConditionBuilder.key(BUILDING_PREFIX + "name").matching(dto.getBuilding()))
                .city(ConditionBuilder.key(CITY_PREFIX + "name").matching(dto.getCity()))
                .name(ConditionBuilder.key(ROOM_PREFIX + "name").matching(dto.getRoomName()))
                .floor(ConditionBuilder.key(ROOM_PREFIX + "floor").in(getFloors(dto)))
                .minSeats(ConditionBuilder.key(ROOM_PREFIX + "seats").moreOrEqualTo(dto.getMinSeats()))
                .maxSeats(ConditionBuilder.key(ROOM_PREFIX + "seats").lessOrEqualTo(dto.getMaxSeats()))
                .status(ConditionBuilder.key(ROOM_PREFIX + "status").in(getStatuses(dto)))
                .videoConferenceHolder(ConditionBuilder.key(ROOM_PREFIX + "video_conference_holder")
                        .in(getVideoConferenceValues(dto)))
                .build();
    }

    @Override
    public ReservationTimePeriodRoomIdFilter mapToTimePeriodFilter(RoomFilterDto dto) {
        LocalDate day = dto.getDate();
        if (day == null) {
            day = LocalDate.now();
        }
        LocalTime hour = LocalTime.of(dto.getHour(), 0);
        LocalDateTime dateTime = LocalDateTime.of(day, hour);
        LocalDateTime dateTimePlusHour = dateTime.plusHours(1L);
        return new ReservationTimePeriodRoomIdFilter(dateTime, dateTimePlusHour);
    }

    private List<Integer> getFloors(RoomFilterDto dto) {
        List<Integer> floors = new ArrayList<>();
        if (dto.isFloor1()) {
            floors.add(1);
        }
        if (dto.isFloor2()) {
            floors.add(2);
        }
        if (dto.isFloor3()) {
            floors.add(3);
        }
        if (dto.isFloor4()) {
            floors.add(4);
        }
        return floors;
    }

    private List<String> getStatuses(RoomFilterDto dto) {
        List<String> statuses = new ArrayList<>();

        if ("available".equalsIgnoreCase(dto.getStatus())) {
            statuses.add(Room.AVAILABLE);
        }
        else if ("unavailable".equalsIgnoreCase(dto.getStatus())) {
            statuses.add(Room.OUT_OF_SERVICE);
        }
        else if ("all".equalsIgnoreCase(dto.getStatus())) {
            statuses.add(Room.AVAILABLE);
            statuses.add(Room.OUT_OF_SERVICE);
        }

        return statuses;
    }

    private List<Boolean> getVideoConferenceValues(RoomFilterDto dto) {
        List<Boolean> values = new ArrayList<>();
        if ("yes".equalsIgnoreCase(dto.getVideoConferenceHolder())) {
            values.add(true);
        } else if ("no".equalsIgnoreCase(dto.getVideoConferenceHolder())) {
            values.add(false);
        } else if ("all".equalsIgnoreCase(dto.getVideoConferenceHolder())) {
            values.add(true);
            values.add(false);
        }
        return values;
    }
}
