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
    private static final String MATCHES_IGNORE_CASE = "~*";
    private static final String IN = "IN";
    private static final String NOT_IN = "NOT IN";

    public static ConditionBuilder key(String key) {
        return new ConditionBuilder(key);
    }

    public <T> SimpleQueryCondition<T> lessThan(T value) {
        return new SimpleQueryCondition<>(key, LESS_THAN, value);
    }

    public <T> SimpleQueryCondition<T> lessOrEqualTo(T value) {
        return new SimpleQueryCondition<>(key, LESS_OR_EQUAL, value);
    }

    public <T> SimpleQueryCondition<T> moreThan(T value) {
        return new SimpleQueryCondition<>(key, MORE_THAN, value);
    }

    public <T> SimpleQueryCondition<T> moreOrEqualTo(T value) {
        return new SimpleQueryCondition<>(key, MORE_OR_EQUAL, value);
    }

    public <T> SimpleQueryCondition<T> equalTo(T value) {
        return new SimpleQueryCondition<>(key, EQUALS, value);
    }

    public <T> SimpleQueryCondition<T> matching(T value) {
        return new SimpleQueryCondition<>(key, MATCHES_IGNORE_CASE, value);
    }

    public <T> MultiValueQueryCondition<T> in(List<T> values) {
        return new MultiValueQueryCondition<>(key, IN, values);
    }

    public <T> MultiValueQueryCondition<T> notIn(List<T> values) {
        return new MultiValueQueryCondition<>(key, NOT_IN, values);
    }

    public <T> SubqueryCondition<T> equalToQuery(String query, T... args) {
        return new SubqueryCondition<>(key, EQUALS, query, args);
    }
}
