package com.example.reservationsystem.service;

import com.example.reservationsystem.database.dao.EmployeeDao;
import com.example.reservationsystem.database.dao.PermissionDao;
import com.example.reservationsystem.domain.Employee;
import com.example.reservationsystem.domain.Permissions;
import com.example.reservationsystem.security.EmployeeUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeDao employeeDao;
    private final PermissionDao permissionDao;

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeDao.getByEmail(email);
    }

    @Override
    public Employee getLoggedInUserDetails() {
        EmployeeUser user = getLoggedInUser();
        return findEmployeeByEmail(user.getEmail());
    }

    @Override
    public EmployeeUser getLoggedInUser() {
        return (EmployeeUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public Permissions getLoggedInUserPermissions() {
        Employee employee = getLoggedInUserDetails();
        long priority = employee.getPriority();
        return permissionDao.getById(priority);
    }
}
