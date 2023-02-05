package com.example.reservationsystem.services.filters;

import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.database.conditions.ConditionBuilder;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(staticName = "of")
public class LongIdFilter implements RecordFilter {
    private Long id;
    private Class<?> targetClass;

    @Override
    public List<AbstractQueryCondition> toQueryConditions() {
        return List.of(
                ConditionBuilder.key("id").equalTo(id)
        );
    }

    @Override
    public boolean isTargetClass(Class<?> clazz) {
        return clazz.equals(targetClass);
    }
}
