package com.example.reservationsystem.service.filters.reservations;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.conditions.ConditionBuilder;
import com.example.reservationsystem.service.filters.RecordFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
public class ReservationTimePeriodEmployeeIdFilter implements RecordFilter {
    private String employeeId;
    private LocalDateTime from;
    private LocalDateTime to;

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        return List.of(
                ConditionBuilder.key("employee_id").equalTo(employeeId),
                ConditionBuilder.key("start_time").lessOrEqualTo(to),
                ConditionBuilder.key("end_time").moreOrEqualTo(from)
        );
    }
}
