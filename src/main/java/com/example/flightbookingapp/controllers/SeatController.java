package com.example.flightbookingapp.controllers;

import com.example.flightbookingapp.models.ESeatClass;
import com.example.flightbookingapp.models.Seat;
import com.example.flightbookingapp.repositories.SeatRepository;
import com.example.flightbookingapp.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/seats")
public class SeatController {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private SeatService seatService;

    @GetMapping("/recommend")
    public List<Seat> recommendSeats(@RequestParam int numberOfSeats,
                                     @RequestParam (required = false) ESeatClass seatClass,
                                     @RequestParam(required = false) Boolean window,
                                     @RequestParam(required = false) Boolean extraLegroom,
                                     @RequestParam(required = false) Boolean nearExit) {
        List<Seat> seats = seatRepository.findAll();
        List<Seat> availableSeats = seats.stream()
                .filter(seat -> !seat.isBooked())
                .filter(seat -> seatClass == null || seat.getSeatClass() == seatClass)
                .filter(seat -> window == null || seat.isWindow() == window)
                .filter(seat -> extraLegroom == null || seat.isHasExtraLegroom() == extraLegroom)
                .filter(seat -> nearExit == null || seat.isNearExit() == nearExit)
                .collect(Collectors.toList());

        if (numberOfSeats > 1) {
            return seatService.findAdjacentSeats(availableSeats, numberOfSeats);
        } else {
            return availableSeats;
        }
    }

    @PostMapping("/book")
    public String bookSeats(@RequestBody List<Integer> seatIds) {
        List<Seat> bookedSeats = new ArrayList<>();
        for (int seatId : seatIds) {
            Optional<Seat> seatOptional = seatRepository.findById(seatId);
            if (seatOptional.isPresent()) {
                Seat seat = seatOptional.get();
                if (seat.isBooked()) {
                    seat.setBooked(true);
                    seatRepository.save(seat);
                    bookedSeats.add(seat);
                } else {
                    return "Seat " + seat.getRowNumber() + seat.getSeatLetter() + " is already booked.";
                }
            } else {
                return "Seat with id " + seatId + " not found.";
            }
        }
        return "Seats " + bookedSeats.stream()
                .map(seat -> seat.getRowNumber() + seat.getSeatLetter() + " ")
                .collect(Collectors.joining()) + "successfully booked.";
    }
}
