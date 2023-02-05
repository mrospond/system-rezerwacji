package com.example.reservationsystem.services.filters.rooms;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.conditions.ConditionBuilder;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.services.filters.RecordFilter;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "of")
public class RoomBuildingIdFilter implements RecordFilter {
    private Long buildingId;

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        return List.of(
                ConditionBuilder.key("building_id").equalTo(buildingId)
        );
    }

    @Override
    public boolean isTargetClass(Class<?> clazz) {
        return Room.class.equals(clazz);
    }
}
