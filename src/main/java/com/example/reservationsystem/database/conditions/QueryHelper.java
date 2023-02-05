package com.example.reservationsystem.database.conditions;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryHelper {
    private static final String AND = "AND";
    private static final String WHERE = "WHERE";

    public String addWhereClauses(String query, List<AbstractQueryCondition> conditions) {
        String queryWithoutSemicolon = query.replace(";", "");
        String joinedConditions = conditions.stream()
                .map(AbstractQueryCondition::mapToSql)
                .collect(Collectors.joining(" " + AND + " "));
        return queryWithoutSemicolon + " " + WHERE + " " + joinedConditions + ";";
    }

    public Object[] mapConditionsToValuesArray(List<AbstractQueryCondition> conditions) {
        return conditions.stream()
                .map(condition -> {
                    if (condition instanceof SimpleQueryCondition) {
                        return List.of(((SimpleQueryCondition) condition).getValue());
                    }
                    else if (condition instanceof MultiValueQueryCondition) {
                        return ((MultiValueQueryCondition) condition).getValues();
                    }
                    else {
                        throw new IllegalArgumentException("Unable to map class: " + condition.getClass());
                    }
                })
                .flatMap(List::stream)
                .toArray();
    }
}
