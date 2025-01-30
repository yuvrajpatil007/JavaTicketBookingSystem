package TicketBookingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {

    public static void showAdminPanel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Admin Panel!");
        System.out.println("1. Add a Movie");
        System.out.println("2. Delete a Movie");
        System.out.println("3. View Booked Seats");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                addMovie();
                break;
            case 2:
                deleteMovie();
                break;
            case 3:
                viewBookedSeats();
                break;
            case 4:
                System.out.println("Exiting Admin Panel...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        showAdminPanel();
    }

    private static void addMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Movie Name: ");
        String movieName = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Show Time: ");
        String time = scanner.nextLine();

        try (Connection conn = TicketBookingDB.getConnection()) {
            String query = "INSERT INTO shows (movie_name, genre, time) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, movieName);
            stmt.setString(2, genre);
            stmt.setString(3, time);
            stmt.executeUpdate();
            System.out.println("Movie added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding movie: " + e.getMessage());
        }
    }

    private static void deleteMovie() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Movie Name to Delete: ");
        String movieName = scanner.nextLine();

        try (Connection conn = TicketBookingDB.getConnection()) {
            String deleteShowQuery = "DELETE FROM shows WHERE movie_name = ?";
            PreparedStatement deleteStmt = conn.prepareStatement(deleteShowQuery);
            deleteStmt.setString(1, movieName);
            deleteStmt.executeUpdate();

            String dropMovieTableQuery = "DROP TABLE IF EXISTS `" + movieName + "`";
            PreparedStatement dropStmt = conn.prepareStatement(dropMovieTableQuery);
            dropStmt.executeUpdate();

            System.out.println("Movie and its bookings deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting movie: " + e.getMessage());
        }
    }

    private static void viewBookedSeats() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Movie Name to View Booked Seats: ");
        String movieName = scanner.nextLine();

        try (Connection conn = TicketBookingDB.getConnection()) {
            String query = "SELECT * FROM `" + movieName + "`";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Booked Seats for " + movieName + ":");
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String seatNumber = rs.getString("seat_number");
                System.out.printf("Name: %s | Email: %s | Phone: %s | Seat: %s%n", name, email, phone, seatNumber);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching booked seats: " + e.getMessage());
        }
    }
}

