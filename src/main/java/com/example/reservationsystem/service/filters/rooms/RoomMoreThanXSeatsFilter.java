package com.example.reservationsystem.service.filters.rooms;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.conditions.ConditionBuilder;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.service.filters.RecordFilter;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "of")
public class RoomMoreThanXSeatsFilter implements RecordFilter {
    private int seats;

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        return List.of(
                ConditionBuilder.key("seats").moreOrEqualTo(seats)
        );
    }

    @Override
    public boolean isTargetClass(Class<?> clazz) {
        return Room.class.equals(clazz);
    }
}
