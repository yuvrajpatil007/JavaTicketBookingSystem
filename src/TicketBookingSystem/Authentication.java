package TicketBookingSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Authentication {
    public static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try (Connection conn = TicketBookingDB.getConnection()) {
            String query = "SELECT isAdmin FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                boolean isAdmin = rs.getBoolean("isAdmin");
                if (isAdmin) {
                    Admin.showAdminPanel();
                } else {
                    User.showUserPanel(email);
                }
            } else {
                System.out.println("Invalid email or password. Try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    public static void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try (Connection conn = TicketBookingDB.getConnection()) {
            String query = "INSERT INTO users (name, email, phone, password, isAdmin) VALUES (?, ?, ?, ?, 0)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, password);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Registration successful! You can now log in.");
            } else {
                System.out.println("Registration failed. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
    }
}
