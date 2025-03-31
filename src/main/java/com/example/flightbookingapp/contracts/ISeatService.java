package com.example.flightbookingapp.contracts;

import com.example.flightbookingapp.models.Seat;

import java.util.List;

public interface ISeatService {
     List<Seat> findAdjacentSeats(List<Seat> availableSeats, int numberOfSeats);
}
