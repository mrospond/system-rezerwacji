package com.example.reservationsystem.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {
    private String id;
    private Long cityId;
    private String city;
    private Long delegationCityId;
    private String delegationCity;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;
    private Integer priority;
}
