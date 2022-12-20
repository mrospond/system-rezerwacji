package com.example.reservationsystem.controllers;

import com.example.reservationsystem.database.dao.EmployeeDao;
import com.example.reservationsystem.security.EmployeeUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Data
@AllArgsConstructor
public class HomeController {
    private EmployeeDao employeeDao;

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        EmployeeUser user = (EmployeeUser) authentication.getPrincipal();
        model.addAttribute("name", user.getFirstName());
        return "index";
    }
}