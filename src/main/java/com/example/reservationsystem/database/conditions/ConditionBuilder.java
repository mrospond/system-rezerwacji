package com.example.reservationsystem.database.conditions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConditionBuilder {
    private String key;

    private static final String LESS_THAN = "<";
    private static final String LESS_OR_EQUAL = "<=";
    private static final String MORE_THAN = ">";
    private static final String MORE_OR_EQUAL = ">=";
    private static final String EQUALS = "=";
    private static final String IN = "IN";
    private static final String NOT_IN = "NOT IN";

    public static ConditionBuilder key(String key) {
        return new ConditionBuilder(key);
    }

    public SimpleQueryCondition lessThan(Object value) {
        return new SimpleQueryCondition(key, LESS_THAN, value);
    }

    public SimpleQueryCondition lessOrEqualTo(Object value) {
        return new SimpleQueryCondition(key, LESS_OR_EQUAL, value);
    }

    public SimpleQueryCondition moreThan(Object value) {
        return new SimpleQueryCondition(key, MORE_THAN, value);
    }

    public SimpleQueryCondition moreOrEqualTo(Object value) {
        return new SimpleQueryCondition(key, MORE_OR_EQUAL, value);
    }

    public SimpleQueryCondition equalTo(Object value) {
        return new SimpleQueryCondition(key, EQUALS, value);
    }

    public MultiValueQueryCondition in(List<Object> values) {
        return new MultiValueQueryCondition(key, IN, values);
    }

    public MultiValueQueryCondition notIn(List<Object> values) {
        return new MultiValueQueryCondition(key, NOT_IN, values);
    }
}
