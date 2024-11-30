package com.hotel.models;

import java.util.Date;

public class Reservation {
    private String customerName;
    private String lastName;
    private String dni;
    private String roomNumber;
    private Date reservationDate;
    private Date checkInDate;
    private Date checkOutDate;
    private String reservationType;
    private int nights;
    private double cost;

    // Constructor
    public Reservation(String lastName, String customerName, String dni, String roomNumber,
                       Date reservationDate, Date checkInDate, Date checkOutDate,
                       String reservationType, int nights, double cost) {
        this.lastName = lastName;
        this.customerName = customerName;
        this.dni = dni;
        this.roomNumber = roomNumber;
        this.reservationDate = reservationDate;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservationType = reservationType;
        this.nights = nights;
        this.cost = cost;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getDni() {
        return this.dni;
    }

    public String getRoomNumber() {
        return this.roomNumber;
    }

    public Date getReservationDate() {
        return this.reservationDate;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }

    public Date getCheckOutDate() {
        return this.checkOutDate;
    }

    public String getReservationType() {
        return this.reservationType;
    }

    public int getNights() {
        return this.nights;
    }

    public double getCost() {
        return this.cost;
    }

    public Object getApellido() {
        return null;
    }

    public Object getNombre() {
        return null;
    }

    public Object getNumeroCuarto() {
        return null;
    }

    public Object getFechaReserva() {
        return null;
    }

    public Object getFechaIngreso() {
        return null;
    }

    public Object getFechaSalida() {
        return null;
    }

    public Object getTipoReserva() {
        return null;
    }

    public Object getNoches() {
        return null;
    }

    public Object getCosto() {
        return null;
    }

    // Getters and Setters
    // (aquí puedes agregar los setters si necesitas)
}