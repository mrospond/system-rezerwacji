package com.example.reservationsystem.database.dao;

import com.example.reservationsystem.database.DBSession;
import com.example.reservationsystem.database.conditions.AbstractQueryCondition;
import com.example.reservationsystem.domain.Employee;
import com.example.reservationsystem.reflection.ReflectionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@AllArgsConstructor
public class EmployeeDao implements Dao<String, Employee> {
    private final DBSession dbSession;

    public Employee getByEmail(String email) {
        return dbSession.queryOne("selectEmployeeByEmail", Employee.class, email);
    }

    @Override
    public Employee getById(String id) {
        return dbSession.queryOne("selectEmployeeById", Employee.class, id);
    }

    @Override
    public void insert(Employee entity) {
        Object[] args = ReflectionUtil.extractFieldsAsValuesArray(entity);
        dbSession.executeDml("insertIntoEmployees", args);
    }

    @Override
    public void updateById(String id, Employee entity) {
        Object[] args = ReflectionUtil.extractFieldsAsValuesArray(entity);
        dbSession.executeDml("updateEmployeeById", args);
    }

    @Override
    public void deleteById(String id) {
        dbSession.executeDml("deleteEmployeeById", id);
    }

    @Override
    public List<Employee> getAll(List<AbstractQueryCondition> conditions) {
        return dbSession.queryMultiple("selectAllFromEmployees", Employee.class, conditions);
    }
}
