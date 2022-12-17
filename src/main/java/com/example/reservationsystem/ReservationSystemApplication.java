package com.example.reservationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ReservationSystemApplication {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(ReservationSystemApplication.class, args);
        System.out.println();
    }

}
