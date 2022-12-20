package com.example.reservationsystem.domain;

import lombok.Data;

@Data
public class Employee {
    private String id;
    private Integer cityId;
    private Integer delegationCityId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer priority;
}
