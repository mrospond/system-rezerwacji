package com.example.reservationsystem;

import com.example.reservationsystem.database.dao.EmployeeDao;
import com.example.reservationsystem.reflection.ReflectionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ReservationSystemApplication {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(ReservationSystemApplication.class, args);
        var emp = ctx.getBean(EmployeeDao.class).getByEmail("brenda.barry80325@example.com");
        var gowno = ReflectionUtil.extractFieldsAsValuesArray(emp);
        System.out.println();
    }
}
