package TicketBookingSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TicketBookingDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/TicketBookingDB";
    private static final String USER = "root";
    private static final String PASSWORD = "Yuvraj@1234";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            throw e;
        }
    }
}
