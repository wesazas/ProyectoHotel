package com.hotel.gui;

import com.hotel.controllers.ReservationController;
import com.hotel.models.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReservationListView extends JFrame {
    private final DefaultTableModel tableModel;

    public ReservationListView() {
        setTitle("Listado de Reservas");
        setLayout(new BorderLayout());

        // Columnas de la tabla
        String[] columnNames = {"Apellido", "Nombre", "DNI", "Nº Cuarto", "Fecha Reserva",
                "Fecha Ingreso", "Fecha Salida", "Tipo", "Noches", "Costo"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable reservationTable = new JTable(tableModel);

        // Cargar datos de reservas
        loadReservations();

        // Agregar la tabla dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(reservationTable);
        add(scrollPane, BorderLayout.CENTER);

        // Configuración del JFrame
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadReservations() {
        // Obtener reservas desde el controlador
        ReservationController controller = new ReservationController();
        List<Reservation> reservations = controller.getReservations();

        // Añadir filas a la tabla
        for (Reservation reservation : reservations) {
            Object[] rowData = {
                    reservation.getCustomerName(),
                    reservation.getLastName(),
                    reservation.getDni(),
                    reservation.getRoomNumber(),
                    reservation.getReservationDate(),
                    reservation.getCheckInDate(),
                    reservation.getCheckOutDate(),
                    reservation.getReservationType(),
                    reservation.getNights(),
                    reservation.getCost()
            };
            tableModel.addRow(rowData);
        }
    }
}
