package com.example.reservationsystem.controllers;

import com.example.reservationsystem.domain.Reservation;
import com.example.reservationsystem.dto.ReservationDto;
import com.example.reservationsystem.dto.mappers.ReservationMapper;
import com.example.reservationsystem.service.EmployeeService;
import com.example.reservationsystem.service.RoomReservationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ReservationController {
    private final RoomReservationService roomReservationService;
    private final ReservationMapper reservationMapper;
    private final EmployeeService employeeService;

    @PostMapping("/reservation/bookRoom")
    public String bookRoom(@Valid ReservationDto reservationDto) {
        Reservation reservation = reservationMapper.mapToReservation(reservationDto);
        reservation.setEmployeeId(employeeService.getLoggedInUserDetails().getId());
        roomReservationService.reserveRoom(reservation);
        return "redirect:/home";
    }

    @GetMapping("/reservation")
    public String reservation(@RequestParam long roomId, Model model) {
        model.addAttribute("roomId", roomId);
        return "reservation";
    }
}
