package com.example.reservationsystem.database.conditions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MultiValueQueryCondition extends AbstractQueryCondition {
    private List<Object> values;

    public MultiValueQueryCondition(String key, String symbol, List<Object> values) {
        super();
        this.key = key;
        this.symbol = symbol;
        this.values = values;
    }

    @Override
    public String mapToSql() {
        String commaSeparatedQuestionMarks = "(" + "?, ".repeat(values.size() - 1) + "?)";
        return key + " " + symbol + " " + commaSeparatedQuestionMarks;
    }
}
