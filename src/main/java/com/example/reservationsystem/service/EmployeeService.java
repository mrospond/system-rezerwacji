package com.example.reservationsystem.service;

import com.example.reservationsystem.domain.Employee;

public interface EmployeeService {
    Employee findEmployeeByEmail(String email);
}
