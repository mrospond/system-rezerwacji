package com.example.reservationsystem.database.conditions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleQueryCondition extends AbstractQueryCondition {
    private Object value;

    public SimpleQueryCondition(String key, String symbol, Object value) {
        super();
        this.key = key;
        this.symbol = symbol;
        this.value = value;
    }

    @Override
    public String mapToSql() {
        return key + " " + symbol + " ?";
    }
}
