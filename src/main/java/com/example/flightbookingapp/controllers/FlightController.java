package com.example.flightbookingapp.controllers;

import com.example.flightbookingapp.models.Flight;
import com.example.flightbookingapp.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public List<Flight> getFlights(@RequestParam(required = false) String destination) {
        if (destination != null) {
            return flightRepository.findAll().stream()
                    .filter(f -> f.getDestination().equalsIgnoreCase(destination))
                    .toList();
        }
        return flightRepository.findAll();
    }
}
