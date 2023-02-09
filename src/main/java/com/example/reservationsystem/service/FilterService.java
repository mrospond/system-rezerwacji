package com.example.reservationsystem.service;

import com.example.reservationsystem.dto.RoomFilterDto;
import com.example.reservationsystem.service.filters.RecordFilter;

import java.util.List;

public interface FilterService {
    List<RecordFilter> createUserSpecificFilters();
    void addRoomFilter(List<RecordFilter> filters, RoomFilterDto filterDto);
    void enhanceFilterDtoForView(RoomFilterDto dto);
}
