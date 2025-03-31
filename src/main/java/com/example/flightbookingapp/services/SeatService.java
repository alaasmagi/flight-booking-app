package com.example.flightbookingapp.services;

import com.example.flightbookingapp.contracts.ISeatService;
import com.example.flightbookingapp.models.Seat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService implements ISeatService {

    public List<Seat> findAdjacentSeats(List<Seat> availableSeats, int numberOfSeats) {
        availableSeats.sort((a, b) -> {
            if (a.getRowNumber() == b.getRowNumber()) {
                return Character.compare(a.getSeatLetter(), b.getSeatLetter());
            }
            return Integer.compare(a.getRowNumber(), b.getRowNumber());
        });

        for (int i = 0; i <= availableSeats.size() - numberOfSeats; i++) {
            List<Seat> adjacentSeats = new ArrayList<>();
            adjacentSeats.add(availableSeats.get(i));

            for (int j = 1; j < numberOfSeats; j++) {
                if (availableSeats.get(i + j).getRowNumber() == adjacentSeats.get(0).getRowNumber() &&
                        availableSeats.get(i + j).getSeatLetter() - adjacentSeats.get(j - 1).getSeatLetter() == 1) {
                    adjacentSeats.add(availableSeats.get(i + j));
                } else {
                    break;
                }
            }

            if (adjacentSeats.size() == numberOfSeats) {
                return adjacentSeats;
            }
        }

        return new ArrayList<>();
    }

    private boolean areAdjacentSeats(List<Seat> seats) {
        if (seats.size() <= 1) {
            return true;
        }

        int rowNumber = seats.get(0).getRowNumber();
        seats.sort((a, b) -> Character.compare(a.getSeatLetter(), b.getSeatLetter())); // Ensure order

        for (int i = 1; i < seats.size(); i++) {
            if (seats.get(i).getRowNumber() != rowNumber ||
                    seats.get(i).getSeatLetter() - seats.get(i - 1).getSeatLetter() != 1) {
                return false;
            }
        }
        return true;
    }
}

