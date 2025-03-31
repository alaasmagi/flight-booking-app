package com.example.flightbookingapp.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String originAirport;
    private String originCity;
    private String destinationAirport;
    private String destinationCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;

    public Flight() {
    }

    public String getOriginAirport() { return originAirport; }

    public void setOriginAirport(String originAirport) { this.originAirport = originAirport; }

    public String getOriginCity() { return originCity; }

    public void setOriginCity(String originCity) { this.originCity = originCity; }

    public String getDestinationAirport() { return destinationAirport; }

    public void setDestinationAirport(String destinationAirport) { this.destinationAirport = destinationAirport; }

    public String getDestinationCity() { return destinationCity; }

    public void setDestinationCity(String destinationCity) { this.destinationCity = destinationCity; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
