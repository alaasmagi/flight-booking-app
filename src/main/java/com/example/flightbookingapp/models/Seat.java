package com.example.flightbookingapp.models;

import jakarta.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rowNumber;
    private char seatLetter;
    private boolean isBooked;
    private boolean isWindow;
    private boolean hasExtraLegroom;
    private boolean isNearExit;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    public Seat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public char getSeatLetter() {
        return seatLetter;
    }

    public void setSeatLetter(char seatLetter) {
        this.seatLetter = seatLetter;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public boolean isWindow() {
        return isWindow;
    }

    public void setWindow(boolean window) {
        isWindow = window;
    }

    public boolean isHasExtraLegroom() {
        return hasExtraLegroom;
    }

    public void setHasExtraLegroom(boolean hasExtraLegroom) {
        this.hasExtraLegroom = hasExtraLegroom;
    }

    public boolean isNearExit() {
        return isNearExit;
    }

    public void setNearExit(boolean nearExit) {
        isNearExit = nearExit;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
