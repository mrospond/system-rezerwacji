package com.example.reservationsystem.database.conditions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class MultiValueQueryCondition<T> extends AbstractQueryCondition {
    private List<T> values;

    public MultiValueQueryCondition(String key, String symbol, List<T> values) {
        super();
        this.key = key;
        this.symbol = symbol;
        this.values = filterNulls(values);
    }

    public List<T> filterNulls(List<T> values) {
        return values.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String mapToSql() {
        if (isEmpty()) {
            return "";
        }
        String commaSeparatedQuestionMarks = "(" + "?, ".repeat(values.size() - 1) + "?)";
        return key + " " + symbol + " " + commaSeparatedQuestionMarks;
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }
}
