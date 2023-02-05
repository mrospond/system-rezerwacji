package com.example.reservationsystem.services.filters.reservations;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.conditions.ConditionBuilder;
import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.services.filters.RecordFilter;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor(staticName = "of")
public class ReservationInRoomIdsFilter implements RecordFilter {
    private List<Long> roomIds;

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        List<Object> mappedIds = Arrays.asList(roomIds.toArray());
        return List.of(
                ConditionBuilder.key("room_id").in(mappedIds)
        );
    }

    @Override
    public boolean isTargetClass(Class<?> clazz) {
        return Reservation.class.equals(clazz);
    }
}
