package com.example.reservationsystem.dao;

import com.example.reservationsystem.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Data
@AllArgsConstructor
public class EmployeeDao {
    private JdbcTemplate jdbcTemplate;
    private EmployeeMapper employeeMapper;

    public Employee execute() {
        return jdbcTemplate.queryForObject("SELECT * FROM employees where id = 'BB80325'", employeeMapper);
    }

    public Employee getByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM employees WHERE email = ?", employeeMapper, email);
    }
}
