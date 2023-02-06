package com.example.reservationsystem.services.filters.rooms;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.services.filters.RecordFilter;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RoomFilter implements RecordFilter {
    private AbstractQueryCondition buildingId;
    private AbstractQueryCondition priority;
    private AbstractQueryCondition status;
    private AbstractQueryCondition floor;
    private AbstractQueryCondition name;
    private AbstractQueryCondition seats;
    private AbstractQueryCondition videoConferenceHolder;

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        return List.of(
                buildingId,
                priority,
                status,
                floor,
                name,
                seats,
                videoConferenceHolder
        );
    }

    @Override
    public boolean isTargetClass(Class<?> clazz) {
        return Room.class.equals(clazz);
    }
}
