package com.example.reservationsystem.database;

import com.example.reservationsystem.config.SQLTemplates;
import com.example.reservationsystem.database.exceptions.EntityNotFoundException;
import com.example.reservationsystem.database.exceptions.NonExistingSQLTemplateException;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class DBSession {
    private final SQLTemplates templates;
    private final JdbcTemplate jdbcTemplate;

    public <T> T queryOne(String queryName, Class<T> type, Object... args) throws EntityNotFoundException {
        try {
            final String query = getQueryTemplate(queryName).getQuery();
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(type), args);
        } catch (EmptyResultDataAccessException e) {
            String message = "Entity of type " + type + " not found when executing sql: " + queryName;
            throw new EntityNotFoundException(message);
        }
    }

    public <T> List<T> queryMultiple(String queryName, Class<T> type, Object... args) {
        final String query = getQueryTemplate(queryName).getQuery();
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(type), args);
    }

    public int executeDml(String dmlName, Object... args) {
        final String dml = getDmlTemplate(dmlName).getDml();
        return jdbcTemplate.update(dml, args);
    }

    private DmlTemplate getDmlTemplate(String dmlName) {
        return templates.getDmls().stream()
                .filter(dml -> dmlName.equals(dml.getName()))
                .findFirst()
                .orElseThrow(() -> new NonExistingSQLTemplateException("Dml template: " + dmlName + "does not exist"));
    }

    private QueryTemplate getQueryTemplate(String queryName) {
        return templates.getQueries().stream()
                .filter(dml -> queryName.equals(dml.getName()))
                .findFirst()
                .orElseThrow(() -> new NonExistingSQLTemplateException("Query template: " + queryName + "does not exist"));
    }
}
