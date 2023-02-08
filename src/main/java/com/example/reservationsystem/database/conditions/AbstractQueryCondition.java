package com.example.reservationsystem.database.conditions;

public abstract class AbstractQueryCondition {
    protected String key;
    protected String symbol;

    public abstract String mapToSql();
    public abstract boolean isEmpty();
}
