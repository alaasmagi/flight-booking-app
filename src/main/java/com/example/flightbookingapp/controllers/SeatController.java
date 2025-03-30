package com.example.flightbookingapp.controllers;

import com.example.flightbookingapp.models.Seat;
import com.example.flightbookingapp.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seats")
public class SeatController {
    @Autowired
    private SeatRepository seatRepository;

    @GetMapping("/recommend")
    public List<Seat> recommendSeats(@RequestParam int flightId, @RequestParam(required = false) Boolean window,
                                     @RequestParam(required = false) Boolean extraLegroom, @RequestParam(required = false) Boolean nearExit) {
        List<Seat> seats = seatRepository.findAll();
        return seats.stream()
                .filter(seat -> seat.getFlight().getId() == flightId)
                .filter(seat -> !seat.isBooked())
                .filter(seat -> window == null || seat.isWindow() == window)
                .filter(seat -> extraLegroom == null || seat.isHasExtraLegroom() == extraLegroom)
                .filter(seat -> nearExit == null || seat.isNearExit() == nearExit)
                .toList();
    }

    @PostMapping("/book")
    public String bookSeat(@RequestParam int seatId) {
        Optional<Seat> seatOptional = seatRepository.findById(seatId);
        if (seatOptional.isPresent()) {
            Seat seat = seatOptional.get();
            if (!seat.isBooked()) {
                seat.setBooked(true);
                seatRepository.save(seat);
                return "Seat " + seat.getRowNumber() + seat.getSeatLetter() + " successfully booked.";
            } else {
                return "Seat is already booked.";
            }
        }
        return "Seat not found.";
    }
}
