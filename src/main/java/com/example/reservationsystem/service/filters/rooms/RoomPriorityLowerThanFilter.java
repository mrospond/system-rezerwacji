package com.example.reservationsystem.service.filters.rooms;

import com.example.reservationsystem.database.DatabaseConstants;
import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.conditions.ConditionBuilder;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.service.filters.RecordFilter;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "ofPriority")
public class RoomPriorityLowerThanFilter implements RecordFilter {
    private int priority;

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        return List.of(
                ConditionBuilder.key(DatabaseConstants.ROOM_PREFIX + "priority").moreOrEqualTo(priority)
        );
    }

    @Override
    public boolean isTargetClass(Class<?> clazz) {
        return Room.class.equals(clazz);
    }
}
