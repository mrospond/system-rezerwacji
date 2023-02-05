package com.example.reservationsystem.services.filters.rooms;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.conditions.ConditionBuilder;
import com.example.reservationsystem.services.filters.RecordFilter;
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
        return false;
    }
}
