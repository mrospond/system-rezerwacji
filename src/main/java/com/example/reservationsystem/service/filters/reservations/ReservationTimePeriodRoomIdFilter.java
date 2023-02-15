package com.example.reservationsystem.service.filters.reservations;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.conditions.ConditionBuilder;
import com.example.reservationsystem.service.filters.RecordFilter;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class ReservationTimePeriodRoomIdFilter implements RecordFilter {
    private LocalDateTime from;
    private LocalDateTime to;

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        return List.of(
                ConditionBuilder.key("0").equalToQuery(
                                """
                                SELECT COUNT(*) FROM reservations
                                WHERE room_id = res.room_id
                                AND start_time <= ? AND end_time >= ?
                                """
                , to, from)
        );
    }
}
