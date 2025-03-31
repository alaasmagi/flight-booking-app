package com.example.flightbookingapp.services;

import com.example.flightbookingapp.contracts.ISeatService;
import com.example.flightbookingapp.models.Seat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService implements ISeatService {

    public List<Seat> findAdjacentSeats(List<Seat> availableSeats, int numberOfSeats) {
        List<Seat> adjacentSeats = new ArrayList<>();
        for (Seat seat : availableSeats) {
            adjacentSeats.add(seat);
            if (adjacentSeats.size() == numberOfSeats) {
                if (areAdjacentSeats(adjacentSeats)) {
                    return adjacentSeats;
                } else {
                    adjacentSeats.remove(0);
                }
            } else if (adjacentSeats.size() > numberOfSeats) {
                adjacentSeats.remove(0);
            }
        }
        return new ArrayList<>();
    }

    private boolean areAdjacentSeats(List<Seat> seats) {
        if (seats.size() <= 1) {
            return true;
        }

        int rowNumber = seats.get(0).getRowNumber();
        char expectedSeatLetter = seats.get(0).getSeatLetter();

        for (Seat seat : seats) {
            if (seat.getRowNumber() != rowNumber || seat.getSeatLetter() != expectedSeatLetter) {
                return false;
            }
            expectedSeatLetter++;
        }
        return true;
    }
}

