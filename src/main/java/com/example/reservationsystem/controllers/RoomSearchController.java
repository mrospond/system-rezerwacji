package com.example.reservationsystem.controllers;

import com.example.reservationsystem.domain.Employee;
import com.example.reservationsystem.domain.Room;
import com.example.reservationsystem.dto.RoomFilterDto;
import com.example.reservationsystem.service.EmployeeService;
import com.example.reservationsystem.service.FilterService;
import com.example.reservationsystem.service.RoomReservationService;
import com.example.reservationsystem.service.filters.RecordFilter;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@AllArgsConstructor
public class RoomSearchController {
    private final EmployeeService employeeService;
    private final RoomReservationService roomReservationService;
    private final FilterService filterService;

    @GetMapping("/home")
    public String home(Model model) {
        List<RecordFilter> recordFilters = filterService.createUserSpecificFilters();
        List<Room> rooms = roomReservationService.findAvailableRooms(recordFilters);

        Employee user = employeeService.getLoggedInUserDetails();
        updateModel(model, rooms, user);
        return "home";
    }

    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET})
    public String search(@Valid RoomFilterDto roomFilterDto, Model model) {
        List<RecordFilter> recordFilters = filterService.createUserSpecificFilters();
        filterService.addRoomFilter(recordFilters, roomFilterDto);

        List<Room> rooms = roomReservationService.findAvailableRooms(recordFilters);

        filterService.enhanceFilterDto(roomFilterDto);

        Employee user = employeeService.getLoggedInUserDetails();
        updateModel(model, rooms, user, roomFilterDto);
        return "home";
    }

    private void updateModel(Model model, List<Room> rooms, Employee user) {
        model.addAttribute("rooms", rooms);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("city", user.getCity());
        model.addAttribute("delegationCity", user.getDelegationCity());
    }

    private void updateModel(Model model, List<Room> rooms, Employee user, RoomFilterDto roomFilterDto) {
        updateModel(model, rooms, user);
        model.addAttribute("roomFilter", roomFilterDto);
    }
}
