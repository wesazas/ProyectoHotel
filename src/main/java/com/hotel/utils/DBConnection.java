package com.hotel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:ucanaccess://C:/Users/xwesa/IdeaProjects/ProyectoHotel/BDD_PROYECTOPOO.accdb";  // Cambia la ruta
    private static final String USER = "";  // No es necesario para Access
    private static final String PASSWORD = "";  // No es necesario para Access

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
