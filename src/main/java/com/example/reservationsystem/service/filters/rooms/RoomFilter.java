package com.example.reservationsystem.service.filters.rooms;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.service.filters.RecordFilter;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RoomFilter implements RecordFilter {
    private AbstractQueryCondition building;
    private AbstractQueryCondition status;
    private AbstractQueryCondition floor;
    private AbstractQueryCondition name;
    private AbstractQueryCondition minSeats;
    private AbstractQueryCondition maxSeats;
    private AbstractQueryCondition videoConferenceHolder;

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        return List.of(
                building,
                status,
                floor,
                name,
                minSeats,
                maxSeats,
                videoConferenceHolder
        );
    }

    @Override
    public boolean isTargetClass(Class<?> clazz) {
        return Room.class.equals(clazz);
    }
}
