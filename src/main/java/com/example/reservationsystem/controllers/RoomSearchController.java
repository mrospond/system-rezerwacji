package com.example.reservationsystem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class RoomSearchController {

    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET})
    public String searchPage() {
        return "search";
    }
}
