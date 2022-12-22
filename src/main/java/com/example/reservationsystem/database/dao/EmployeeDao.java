package com.example.reservationsystem.database.dao;

import com.example.reservationsystem.database.DBSession;
import com.example.reservationsystem.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        dbSession.executeDml("insertIntoEmployees", getProperties(entity, true));
    }

    @Override
    public void updateById(String id, Employee entity) {
        dbSession.executeDml("updateEmployeeById", id, getProperties(entity, false));
    }

    @Override
    public void deleteById(String id) {
        dbSession.executeDml("deleteEmployeeById", id);
    }

    private Object[] getProperties(Employee employee, boolean includeId) {
        List<Object> properties = new ArrayList<>();
        if (includeId) {
            properties.add(employee.getId());
        }

        properties.add(employee.getId());
        properties.add(employee.getCityId());
        properties.add(employee.getDelegationCityId());
        properties.add(employee.getFirstName());
        properties.add(employee.getLastName());
        properties.add(employee.getEmail());
        properties.add(employee.getPriority());

        return properties.toArray();

    }
}
