package com.example.reservationsystem.service;

import com.example.reservationsystem.database.dao.EmployeeDao;
import com.example.reservationsystem.domain.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao;

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeDao.getByEmail(email);
    }
}
