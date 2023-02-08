package com.example.reservationsystem.service.filters;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(staticName = "create")
public class DynamicFilter implements RecordFilter {
    private final List<AbstractQueryCondition> queryConditions = new ArrayList<>();
    private Class<?> targetClass;

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        return queryConditions;
    }

    @Override
    public boolean isTargetClass(Class<?> clazz) {
        if (targetClass != null) {
            return targetClass.equals(clazz);
        }
        return true;
    }

    public DynamicFilter addCondition(AbstractQueryCondition condition) {
        queryConditions.add(condition);
        return this;
    }

    public DynamicFilter setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
        return this;
    }
}
