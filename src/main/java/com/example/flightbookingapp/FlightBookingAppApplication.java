package com.example.flightbookingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.flightbookingapp.repositories.DBInitializer.initializeDatabase;


@SpringBootApplication
public class FlightBookingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightBookingAppApplication.class, args);
        initializeDatabase();
    }

}
