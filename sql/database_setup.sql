-- Create Database
CREATE DATABASE IF NOT EXISTS TicketBookingDB;

USE TicketBookingDB;

-- Create Users Table
CREATE TABLE IF NOT EXISTS users (
    name VARCHAR(50),
    email VARCHAR(50) PRIMARY KEY,
    phone VARCHAR(15),
    password VARCHAR(50),
    isAdmin BOOLEAN DEFAULT 0
);

-- Create Shows Table
CREATE TABLE IF NOT EXISTS shows (
    movie_name VARCHAR(50) PRIMARY KEY,
    genre VARCHAR(20),
    time VARCHAR(20),
    admin_email VARCHAR(50),
    FOREIGN KEY (admin_email) REFERENCES users(email)
);

-- Create User Profile Table
CREATE TABLE IF NOT EXISTS user_profile (
    name VARCHAR(50),
    email VARCHAR(50) PRIMARY KEY,
    phone VARCHAR(15),
    password VARCHAR(50),
    booked_movie VARCHAR(50),
    seat_number VARCHAR(5)
);
