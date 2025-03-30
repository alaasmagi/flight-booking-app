package com.example.flightbookingapp.repositories;

import com.example.flightbookingapp.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
