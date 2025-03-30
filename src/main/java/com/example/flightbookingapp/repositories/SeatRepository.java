package com.example.flightbookingapp.repositories;

import com.example.flightbookingapp.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
