package com.hotel.gui;

import com.hotel.controllers.ReservationController;
import com.hotel.models.Reservation;
import com.toedter.calendar.JDateChooser;
import com.hotel.gui.ReservationListView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ReservationForm extends JFrame {
    private JTextField txtApellido, txtNombre, txtDNI, txtNumeroCuarto, txtPrecioDia, txtNoches, txtCosto;
    private JDateChooser dateReserva, dateIngreso, dateSalida;
    private JComboBox<String> cmbTipoReserva;
    private JButton btnGrabar;

    public ReservationForm() {
        setTitle("Formulario de Reserva de Hotel");

        // Usamos GridBagLayout para mayor flexibilidad
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Márgenes entre componentes

        // Inicializar los campos
        txtApellido = new JTextField(20);
        txtNombre = new JTextField(20);
        txtDNI = new JTextField(20);
        txtNumeroCuarto = new JTextField(20);
        txtPrecioDia = new JTextField(20);
        txtNoches = new JTextField(20);
        txtCosto = new JTextField(20);
        txtCosto.setEditable(false);  // El costo no debe ser editable directamente
        dateReserva = new JDateChooser();
        dateIngreso = new JDateChooser();
        dateSalida = new JDateChooser();
        cmbTipoReserva = new JComboBox<>(new String[] {"RESERVA DE HABITACION", "RESERVA DE EVENTO"});
        btnGrabar = new JButton("Grabar");
        // Dentro del constructor de ReservationForm
        JButton btnVerReservas = new JButton("Ver Reservas");
        btnVerReservas.addActionListener(e -> new ReservationListView());
        add(btnVerReservas); // Agregar el botón al formulario

        // Organizar los componentes utilizando GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 1;
        add(txtApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Nombres:"), gbc);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1;
        add(txtDNI, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Número de Cuarto:"), gbc);
        gbc.gridx = 1;
        add(txtNumeroCuarto, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Precio x Día:"), gbc);
        gbc.gridx = 1;
        add(txtPrecioDia, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        add(new JLabel("Fecha Reserva:"), gbc);
        gbc.gridx = 1;
        add(dateReserva, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        add(new JLabel("Fecha Ingreso:"), gbc);
        gbc.gridx = 1;
        add(dateIngreso, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        add(new JLabel("Fecha Salida:"), gbc);
        gbc.gridx = 1;
        add(dateSalida, gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        add(new JLabel("Tipo de Reserva:"), gbc);
        gbc.gridx = 1;
        add(cmbTipoReserva, gbc);

        gbc.gridx = 0; gbc.gridy = 9;
        add(new JLabel("Noches:"), gbc);
        gbc.gridx = 1;
        add(txtNoches, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        add(new JLabel("Costo:"), gbc);
        gbc.gridx = 1;
        add(txtCosto, gbc);

        // Configuración del botón "Grabar"
        gbc.gridx = 0; gbc.gridy = 11;
        gbc.gridwidth = 2; // Hacer que el botón ocupe dos columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        add(btnGrabar, gbc);

        // Configuración del botón "Ver Reservas"
        gbc.gridx = 0; gbc.gridy = 12;
        gbc.gridwidth = 2; // Hacer que el botón ocupe dos columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centrar el botón
        add(btnVerReservas, gbc);

        // Acción para grabar la reserva
        btnGrabar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                grabarReserva();
            }
        });

        // Configuración del JFrame
        setSize(450, 650);  // Ajusté el tamaño del formulario
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);  // Asegúrate de que el JFrame se haga visible después de agregar los componentes
        // Agregar importación para la nueva interfaz



    }

    // Método para grabar la reserva
    private void grabarReserva() {
        try {
            String apellido = txtApellido.getText();
            String nombre = txtNombre.getText();
            String dni = txtDNI.getText();
            String numeroCuarto = txtNumeroCuarto.getText();
            double precioDia = Double.parseDouble(txtPrecioDia.getText());
            Date fechaReserva = dateReserva.getDate();
            Date fechaIngreso = dateIngreso.getDate();
            Date fechaSalida = dateSalida.getDate();

            // Validar que las fechas no sean null
            if (fechaReserva == null || fechaIngreso == null || fechaSalida == null) {
                throw new IllegalArgumentException("Todas las fechas deben estar seleccionadas.");
            }

            String tipoReserva = (String) cmbTipoReserva.getSelectedItem();
            int noches = Integer.parseInt(txtNoches.getText());
            double costo = precioDia * noches;

            // Mostrar el costo calculado en el campo "Costo"
            txtCosto.setText(String.valueOf(costo));

            // Crear el objeto de la reserva
            Reservation reservation = new Reservation(apellido, nombre, dni, numeroCuarto, fechaReserva, fechaIngreso, fechaSalida, tipoReserva, noches, costo);

            // Grabar la reserva usando el controlador
            ReservationController controller = new ReservationController();
            boolean success = controller.addReservation(reservation);

            if (success) {
                JOptionPane.showMessageDialog(this, "Reserva grabada con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al grabar la reserva.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new ReservationForm();
    }
}
