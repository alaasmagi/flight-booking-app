package com.example.flightbookingapp.controllers;

import com.example.flightbookingapp.models.Flight;
import com.example.flightbookingapp.models.Seat;
import com.example.flightbookingapp.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flights")
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public List<Flight> getFlights(
            @RequestParam(required = false) String originCity,
            @RequestParam(required = false) String destinationCity,
            @RequestParam(required = false) LocalDate date,
            @RequestParam(required = false) LocalDateTime arrivalTime,
            @RequestParam(required = false) LocalDateTime departureTime,
            @RequestParam(required = false) Double price
    ) {
        List<Flight> flights = flightRepository.findAll();
        List<Flight> availableFlights = flights.stream()
                .filter(flight -> originCity == null || flight.getOriginCity().equals(originCity))
                .filter(flight -> destinationCity == null || flight.getDestinationCity().equals(destinationCity))
                .filter(flight -> date == null || flight.getDepartureTime().toLocalDate().equals(date))
                .filter(flight -> arrivalTime == null || flight.getArrivalTime().equals(arrivalTime))
                .filter(flight -> departureTime == null || flight.getDepartureTime().equals(departureTime))
                .filter(flight -> price == null || flight.getPrice() == price)
                .collect(Collectors.toList());
        return availableFlights;
    }
}

