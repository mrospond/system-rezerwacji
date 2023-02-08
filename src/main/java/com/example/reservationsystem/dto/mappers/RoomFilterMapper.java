package com.example.reservationsystem.dto.mappers;

import com.example.reservationsystem.dto.RoomFilterDto;
import com.example.reservationsystem.service.filters.rooms.RoomFilter;

public interface RoomFilterMapper {
    RoomFilter mapToRoomFilter(RoomFilterDto dto);
}
