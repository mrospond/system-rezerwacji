package com.example.reservationsystem.service;

import com.example.reservationsystem.domain.Employee;
import com.example.reservationsystem.security.EmployeeUser;

public interface EmployeeService {
    Employee findEmployeeByEmail(String email);
    Employee getLoggedInUserDetails();
    EmployeeUser getLoggedInUser();
}
