package TicketBookingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfile {

    public static void viewProfile(String email) {
        try (Connection conn = TicketBookingDB.getConnection()) {
            String query = "SELECT * FROM user_profile WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String bookedMovie = rs.getString("booked_movie");
                String seatNumber = rs.getString("seat_number");

                System.out.println("User Profile:");
                System.out.printf("Name: %s%nEmail: %s%nPhone: %s%nBooked Movie: %s%nBooked Seat: %s%n",
                        name, email, phone, bookedMovie, seatNumber);
            } else {
                System.out.println("No profile found. Please book a ticket first.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching profile: " + e.getMessage());
        }
    }
}
