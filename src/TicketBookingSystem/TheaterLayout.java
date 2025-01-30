package TicketBookingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TheaterLayout {

    private static final int ROWS = 10;
    private static final int COLUMNS = 18;

    // Display Seat Layout for a movie
    public static void displaySeatLayout(String movieName) {
        try (Connection conn = TicketBookingDB.getConnection()) {
            createSeatLayoutTableIfNotExists(conn, movieName);
            String query = "SELECT seat_number FROM `" + movieName + "`";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            boolean[][] seats = new boolean[ROWS][COLUMNS];

            while (rs.next()) {
                String seatNumber = rs.getString("seat_number");
                int row = seatNumber.charAt(0) - 'A'; // Row from A to J
                int col = Integer.parseInt(seatNumber.substring(1)) - 1; // Column
                if (row >= 0 && row < ROWS && col >= 0 && col < COLUMNS) {
                    seats[row][col] = true;
                }
            }

            System.out.println("(SCREEN)");
            for (int i = 0; i < ROWS; i++) {
                char rowLabel = (char) ('A' + i);
                System.out.print(rowLabel + "  ");
                for (int j = 0; j < COLUMNS; j++) {
                    System.out.print((seats[i][j] ? "1 " : "0 "));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error displaying seat layout: " + e.getMessage());
        }
    }

    private static void createSeatLayoutTableIfNotExists(Connection conn, String movieName) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS `" + movieName + "` (" +
                "name VARCHAR(50), email VARCHAR(50), phone VARCHAR(15), seat_number VARCHAR(5), " +
                "PRIMARY KEY (seat_number))";
        PreparedStatement stmt = conn.prepareStatement(createTableQuery);
        stmt.executeUpdate();
    }
}
