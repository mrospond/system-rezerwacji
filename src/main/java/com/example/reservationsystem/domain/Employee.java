package com.example.reservationsystem.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
    private String id;
    private Long cityId;
    private Long delegationCityId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer priority;
}
