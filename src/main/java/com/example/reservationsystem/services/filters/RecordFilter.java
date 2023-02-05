package com.example.reservationsystem.services.filters;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;

import java.util.List;

public interface RecordFilter {
    List<AbstractQueryCondition> toQueryConditions();
    boolean isTargetClass(Class<?> clazz);
}
