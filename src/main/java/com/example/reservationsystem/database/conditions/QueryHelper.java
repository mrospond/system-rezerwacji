package com.example.reservationsystem.database.conditions;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QueryHelper {
    private static final String AND = "AND";
    private static final String WHERE = "WHERE";

    public String addWhereClauses(String query, List<AbstractQueryCondition> conditions) {
        String queryWithoutSemicolon = removeSemicolon(query);
        String joinedConditions = joinConditions(conditions);

        if (joinedConditions.isBlank()) {
            return query;
        }
        return queryWithoutSemicolon + " " + WHERE + " " + joinedConditions + ";";
    }

    public Object[] mapConditionsToValuesArray(List<AbstractQueryCondition> conditions) {
        return conditions.stream()
                .map(condition -> {
                    if (condition instanceof SimpleQueryCondition<?> simpleQueryCondition) {
                        return List.of(simpleQueryCondition.getValue());
                    }
                    else if (condition instanceof MultiValueQueryCondition<?> multiValueQueryCondition) {
                        return multiValueQueryCondition.getValues();
                    }
                    else if (condition instanceof SubqueryCondition<?> subqueryCondition) {
                        return subqueryCondition.getArgs();
                    }
                    else {
                        throw new IllegalArgumentException("Unable to map class: " + condition.getClass());
                    }
                })
                .flatMap(List::stream)
                .toArray();
    }

    public void filterEmptyConditions(List<AbstractQueryCondition> conditions) {
        conditions.removeIf(AbstractQueryCondition::isEmpty);
    }

    private String removeSemicolon(String query) {
        return query.replace(";", "");
    }

    private String joinConditions(List<AbstractQueryCondition> conditions) {
        return conditions.stream()
                .map(AbstractQueryCondition::mapToSql)
                .collect(Collectors.joining(" " + AND + " "));
    }
}
