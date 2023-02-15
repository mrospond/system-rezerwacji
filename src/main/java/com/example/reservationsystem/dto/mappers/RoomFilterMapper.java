package com.example.reservationsystem.dto.mappers;

import com.example.reservationsystem.dto.RoomFilterDto;
import com.example.reservationsystem.service.filters.reservations.ReservationTimePeriodRoomIdFilter;
import com.example.reservationsystem.service.filters.rooms.RoomFilter;

public interface RoomFilterMapper {
    RoomFilter mapToRoomFilter(RoomFilterDto dto);
    ReservationTimePeriodRoomIdFilter mapToTimePeriodFilter(RoomFilterDto dto);
}
