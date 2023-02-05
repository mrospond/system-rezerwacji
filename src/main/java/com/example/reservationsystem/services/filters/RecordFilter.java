package com.example.reservationsystem.services.filters;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;

import java.util.List;

@FunctionalInterface
public interface RecordFilter {
    List<AbstractQueryCondition> toQueryConditions();

    default boolean isTargetClass(Class<?> clazz) {
        return true;
    }
}
