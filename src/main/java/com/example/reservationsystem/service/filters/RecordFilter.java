package com.example.reservationsystem.service.filters;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;

import java.util.List;

public interface RecordFilter {
    List<AbstractQueryCondition> toQueryConditions();

    default boolean isTargetClass(Class<?> clazz) {
        return true;
    }
}
