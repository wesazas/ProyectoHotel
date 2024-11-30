package com.hotel.controllers;

import com.hotel.models.Reservation;
import com.hotel.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationController {

    // Método para agregar una nueva reserva
    public boolean addReservation(Reservation reservation) {
        String query = "INSERT INTO reservations (customer_name, last_name, dni, room_number, reservation_date, " +
                "check_in_date, check_out_date, reservation_type, nights, cost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Verificar que las fechas no sean nulas
            Date reservationDate = reservation.getReservationDate();
            Date checkInDate = reservation.getCheckInDate();
            Date checkOutDate = reservation.getCheckOutDate();

            if (reservationDate == null || checkInDate == null || checkOutDate == null) {
                throw new IllegalArgumentException("Reservation, check-in, or check-out date cannot be null.");
            }

            stmt.setString(1, reservation.getCustomerName());
            stmt.setString(2, reservation.getLastName());
            stmt.setString(3, reservation.getDni());
            stmt.setString(4, reservation.getRoomNumber());
            stmt.setDate(5, new java.sql.Date(reservationDate.getTime()));
            stmt.setDate(6, new java.sql.Date(checkInDate.getTime()));
            stmt.setDate(7, new java.sql.Date(checkOutDate.getTime()));
            stmt.setString(8, reservation.getReservationType());
            stmt.setInt(9, reservation.getNights());
            stmt.setDouble(10, reservation.getCost());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todas las reservas
    public List<Reservation> getReservations() {
        String query = "SELECT customer_name, last_name, dni, room_number, reservation_date, check_in_date, " +
                "check_out_date, reservation_type, nights, cost FROM reservations";
        List<Reservation> reservations = new ArrayList<>();

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String customerName = rs.getString("customer_name");
                String lastName = rs.getString("last_name");
                String dni = rs.getString("dni");
                String roomNumber = rs.getString("room_number");
                Date reservationDate = new Date(rs.getDate("reservation_date").getTime());
                Date checkInDate = new Date(rs.getDate("check_in_date").getTime());
                Date checkOutDate = new Date(rs.getDate("check_out_date").getTime());
                String reservationType = rs.getString("reservation_type");
                int nights = rs.getInt("nights");
                double cost = rs.getDouble("cost");

                // Crear una instancia de Reservation y agregarla a la lista
                reservations.add(new Reservation(customerName, lastName, dni, roomNumber,
                        reservationDate, checkInDate, checkOutDate, reservationType, nights, cost));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }
}
