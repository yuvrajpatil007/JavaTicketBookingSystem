
# 🎟 Java Movie Ticket Booking System 🎬

A Command-Line Interface (CLI)-based **Java Movie Ticket Booking System** designed for seamless user experience and efficient management of movie shows. Built using **Java**, **JDBC**, and **MySQL**, this project enables users and admins to interact with a fully functional ticket booking platform.

---

## 🚀 Features

### 🎫 **User Functionalities**
- **Registration & Login:** Secure user authentication system.
- **View Shows:** Fetch and display available movie shows.
- **Book Tickets:** Select seats dynamically from an interactive layout.
- **Check Seat Availability:** Real-time seat status updates.
- **View Profile:** Check your booking history and account details.

### 🔑 **Admin Functionalities**
- **Manage Shows:** Add and delete movie shows.
- **View Booked Tickets:** See booking details for each movie.
- **Seat Availability:** Track real-time seat statuses.

---

## 🏗️ Project Architecture
```
JavaTicketBookingSystem/
├── src/
│   ├── TicketBookingSystem/
│   │   ├── Admin.java
│   │   ├── Authentication.java
│   │   ├── DatabaseConnection.java
│   │   ├── TheaterLayout.java
│   │   ├── TicketBookingDB.java
│   │   ├── User.java
│   │   └── UserProfile.java
└── sql/
    └── database_setup.sql
```

---

## 🗂️ Database Schema

### **1. `users` Table**
| Column     | Type     | Description         |
|------------|----------|---------------------|
| name       | VARCHAR  | User name            |
| email (PK) | VARCHAR  | User's email address |
| phone      | VARCHAR  | Contact number       |
| password   | VARCHAR  | Account password     |
| isAdmin    | BOOLEAN  | Admin or regular user|

### **2. `shows` Table**
| Column      | Type     | Description         |
|-------------|----------|---------------------|
| movie_name  | VARCHAR  | Name of the movie    |
| genre       | VARCHAR  | Movie genre          |
| time        | TIME     | Show timing          |
| admin_email | VARCHAR  | Admin managing show  |

---

## 🔧 Setup Instructions

### 1️⃣ **Clone the Repository**
```bash
git clone https://github.com/yuvrajpatil007/JavaTicketBookingSystem.git
cd JavaTicketBookingSystem
```

### 2️⃣ **Database Setup**
- Import the provided SQL script into your MySQL database:
  ```bash
  source sql/database_setup.sql;
  ```

### 3️⃣ **Configure Database Connection**
Update `DatabaseConnection.java` with your database credentials:
```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/TicketBookingDB";
private static final String DB_USER = "your-username";
private static final String DB_PASS = "your-password";
```

### 4️⃣ **Run the Project**
Compile and execute the main class to start the application:
```bash
javac TicketBookingSystem/Main.java
java TicketBookingSystem.Main
```

---

## 📸 Seat Layout Display
```
A  0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0  
B  0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0  
C  0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0  
(SCREEN)
```

---

## 📄 Usage Instructions
1. **User Login/Register:** Enter your email and password or register a new account.
2. **Admin Panel:** If you are an admin, you will be redirected to admin functionalities.
3. **Booking:** Choose a movie, view the seat layout, and book tickets.

---

## 💡 Future Improvements
- Add graphical user interface (GUI).
- Integrate payment gateway for secure transactions.
- Enable ticket cancellation feature.
- Add support for email notifications.

---

## 🛡️ License
This project is open-source and available under the [MIT License](LICENSE).

---

## 🙌 Acknowledgements
Special thanks to the contributors for their guidance and support!

---

## 👨‍💻 Author
**[Yuvraj Patil](https://github.com/yuvrajpatil007)**
