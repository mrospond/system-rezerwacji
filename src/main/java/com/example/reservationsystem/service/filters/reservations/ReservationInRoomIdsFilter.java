package com.example.reservationsystem.service.filters.reservations;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.conditions.ConditionBuilder;
import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.service.filters.RecordFilter;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "of")
public class ReservationInRoomIdsFilter implements RecordFilter {
    private List<Long> roomIds;

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        return List.of(
                ConditionBuilder.key("room_id").in(roomIds)
        );
    }

    @Override
    public boolean isTargetClass(Class<?> clazz) {
        return Reservation.class.equals(clazz);
    }
}
