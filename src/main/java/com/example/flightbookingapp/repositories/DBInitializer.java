package com.example.flightbookingapp.repositories;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.Random;

public class DBInitializer {
    private static final String URL = "jdbc:sqlite:database.db";
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#.00");
    private static final Random random = new Random();

    public static void initializeDatabase() {
        String flightSQL = "INSERT INTO flight (arrival_time, departure_time, origin_city, origin_airport, destination_city, destination_airport, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String seatSQL = "INSERT INTO seat (has_extra_legroom, is_booked, is_near_exit, is_window, row_number, seat_class, seat_letter, extra_fee) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement flightStmt = conn.prepareStatement(flightSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement seatStmt = conn.prepareStatement(seatSQL);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DELETE FROM seat");
            stmt.executeUpdate("DELETE FROM flight");

            String[][] flights = {
                    {"2025-04-01 12:30:00", "2025-04-01 10:00:00", "Tallinn", "EETN", "London", "EGLL", "299.99"},
                    {"2025-04-01 15:00:00", "2025-04-01 12:00:00", "Tallinn", "EETN", "Stockholm", "ESSB", "199.99"},
                    {"2025-04-01 18:45:00", "2025-04-01 16:30:00", "Tallinn", "EETN", "Madrid", "LETO", "349.99"}
            };

            for (String[] flight : flights) {
                flightStmt.setString(1, flight[0]);
                flightStmt.setString(2, flight[1]);
                flightStmt.setString(3, flight[2]);
                flightStmt.setString(4, flight[3]);
                flightStmt.setString(5, flight[4]);
                flightStmt.setString(6, flight[5]);
                flightStmt.setDouble(7, Double.parseDouble(PRICE_FORMAT.format(Double.parseDouble(flight[6]))));
                flightStmt.executeUpdate();
            }

            char[] seatLetters = {'A', 'B', 'C', 'D', 'E', 'F'};
            for (int row = 1; row <= 12; row++) {
                for (char seatLetter : seatLetters) {
                    boolean isBooked = random.nextBoolean();
                    seatStmt.setBoolean(1, (row * seatLetter) % 2 == 0);
                    seatStmt.setBoolean(2, isBooked);
                    seatStmt.setBoolean(3, (row * seatLetter) % 3 == 0);
                    seatStmt.setBoolean(4, seatLetter == 'A' || seatLetter == 'F');
                    seatStmt.setInt(5, row);
                    seatStmt.setInt(6, row <= 2 ? 2 : (row <= 5 ? 1 : 0));
                    seatStmt.setString(7, String.valueOf(seatLetter));
                    seatStmt.setDouble(8, row <= 2 ? 100.00 : (row <= 5 ? 50.00 : 0.00));
                    seatStmt.executeUpdate();
                }
            }

            System.out.println("Database initialized successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initializeDatabase();
    }
}