package TicketBookingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {

    public static void showUserPanel(String email) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the User Panel!");
        System.out.println("1. View Available Shows");
        System.out.println("2. Book a Ticket");
        System.out.println("3. View Profile");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                viewAvailableShows();
                break;
            case 2:
                bookTicket(email);
                break;
            case 3:
                UserProfile.viewProfile(email);
                break;
            case 4:
                System.out.println("Thank you for using the system!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        showUserPanel(email);
    }

    private static void viewAvailableShows() {
        try (Connection conn = TicketBookingDB.getConnection()) {
            String query = "SELECT * FROM shows";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Available Shows:");
            while (rs.next()) {
                String movieName = rs.getString("movie_name");
                String genre = rs.getString("genre");
                String time = rs.getString("time");
                System.out.printf("Movie: %s | Genre: %s | Time: %s%n", movieName, genre, time);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching shows: " + e.getMessage());
        }
    }

    private static void bookTicket(String email) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Movie Name: ");
        String movieName = scanner.nextLine();

        System.out.println("Seat Layout for " + movieName + ":");
        TheaterLayout.displaySeatLayout(movieName);

        System.out.print("Enter Seat Number (e.g., A5): ");
        String seatNumber = scanner.nextLine();

        try (Connection conn = TicketBookingDB.getConnection()) {
            String checkSeatQuery = "SELECT * FROM `" + movieName + "` WHERE seat_number = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSeatQuery);
            checkStmt.setString(1, seatNumber);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Seat already booked! Please choose another seat.");
                return;
            }

            System.out.print("Enter Your Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Your Phone: ");
            String phone = scanner.nextLine();

            String bookSeatQuery = "INSERT INTO `" + movieName + "` (name, email, phone, seat_number) VALUES (?, ?, ?, ?)";
            PreparedStatement bookStmt = conn.prepareStatement(bookSeatQuery);
            bookStmt.setString(1, name);
            bookStmt.setString(2, email);
            bookStmt.setString(3, phone);
            bookStmt.setString(4, seatNumber);

            int rows = bookStmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Ticket booked successfully!");
                updateUserProfile(email, movieName, seatNumber);
            } else {
                System.out.println("Ticket booking failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error during ticket booking: " + e.getMessage());
        }
    }

    private static void updateUserProfile(String email, String movieName, String seatNumber) {
        try (Connection conn = TicketBookingDB.getConnection()) {
            String query = "UPDATE user_profile SET booked_movie = ?, seat_number = ? WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, movieName);
            stmt.setString(2, seatNumber);
            stmt.setString(3, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating user profile: " + e.getMessage());
        }
    }
}

