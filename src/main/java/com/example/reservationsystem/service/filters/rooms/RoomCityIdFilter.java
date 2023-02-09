package com.example.reservationsystem.service.filters.rooms;

import com.example.reservationsystem.database.DatabaseConstants;
import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.conditions.ConditionBuilder;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.service.filters.RecordFilter;

import java.util.Arrays;
import java.util.List;


public class RoomCityIdFilter implements RecordFilter {
    private List<Long> cityIds;

    public RoomCityIdFilter(Long... ids) {
        this.cityIds = Arrays.asList(ids);
    }

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        return List.of(
                ConditionBuilder.key(DatabaseConstants.BUILDING_PREFIX + "city_id").in(cityIds)
        );
    }

    @Override
    public boolean isTargetClass(Class<?> clazz) {
        return Room.class.equals(clazz);
    }
}
