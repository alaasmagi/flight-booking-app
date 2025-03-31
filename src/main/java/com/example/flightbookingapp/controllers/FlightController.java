package com.example.flightbookingapp.controllers;

import com.example.flightbookingapp.models.Flight;
import com.example.flightbookingapp.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public List<Flight> getFlights(
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) LocalDateTime arrivalTime,
            @RequestParam(required = false) LocalDateTime departureTime,
            @RequestParam(required = false) Double price
    ) {
        List<Flight> flights = flightRepository.findAll();

        if(origin != null) {
            flights = flights.stream()
                    .filter(flight -> flight.getOrigin().equals(origin))
                    .collect(Collectors.toList());
        }

        if (destination != null) {
            flights = flights.stream()
                    .filter(f -> f.getDestination().equalsIgnoreCase(destination))
                    .collect(Collectors.toList());
        }

        if (date != null) {
            flights = flights.stream()
                    .filter(f -> f.getDepartureTime().toLocalDate().equals(date))
                    .collect(Collectors.toList());
        }

        if (arrivalTime != null) {
            flights = flights.stream()
                    .filter(f -> f.getDepartureTime().equals(arrivalTime))
                    .collect(Collectors.toList());
        }

        if (departureTime != null) {
            flights = flights.stream()
                    .filter(f -> f.getDepartureTime().equals(departureTime))
                    .collect(Collectors.toList());
        }

        if (price != null) {
            flights = flights.stream()
                    .filter(f -> f.getPrice() <= price)
                    .collect(Collectors.toList());
        }

        return flights;
    }
}

