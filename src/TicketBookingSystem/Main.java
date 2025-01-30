package TicketBookingSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Movie Ticket Booking System!");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        switch (choice) {
            case 1:
                Authentication.login();
                break;
            case 2:
                Authentication.register();
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
        }
        scanner.close();
    }
}
