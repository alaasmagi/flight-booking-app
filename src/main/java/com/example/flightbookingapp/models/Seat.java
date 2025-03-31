package com.example.flightbookingapp.models;

import jakarta.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rowNumber;
    private ESeatClass seatClass;
    private char seatLetter;
    private boolean isBooked;
    private boolean isWindow;
    private boolean hasExtraLegroom;
    private boolean isNearExit;
    private double extraFee;

    public Seat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Boolean isHasExtraLegroom() {
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

    public ESeatClass getSeatClass() { return seatClass;}

    public void setSeatClass(ESeatClass seatClass) { this.seatClass = seatClass;}

    public double getExtraFee() {return extraFee; }

    public void setExtraFee(double extraFee) { this.extraFee = extraFee; }
}
