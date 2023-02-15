package com.example.reservationsystem.database.conditions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class SubqueryCondition<T> extends AbstractQueryCondition {
    private String preparedQuery;
    private List<T> args;

    @SafeVarargs
    public SubqueryCondition(String key, String symbol, String preparedQuery, T... args) {
        super();
        this.key = key;
        this.symbol = symbol;
        this.preparedQuery = preparedQuery;
        this.args = filterNulls(args);
    }

    private List<T> filterNulls(T[] values) {
        return Arrays.stream(values)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String mapToSql() {
        if (isEmpty()) {
            return "";
        }
        return key + " " + symbol + " (" + preparedQuery + ")";
    }

    @Override
    public boolean isEmpty() {
        return preparedQuery == null || preparedQuery.isBlank() || args.isEmpty();
    }
}
