package com.example.reservationsystem.services.filters.reservations;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.conditions.ConditionBuilder;
import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.services.filters.RecordFilter;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor(staticName = "of")
public class ReservationTimePeriodFilter implements RecordFilter {
    private LocalDateTime from;
    private LocalDateTime to;

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        return List.of(
                ConditionBuilder.key("start_time").lessOrEqualTo(to),
                ConditionBuilder.key("end_time").moreOrEqualTo(from)
        );
    }

    @Override
    public boolean isTargetClass(Class<?> clazz) {
        return Reservation.class.equals(clazz);
    }
}
