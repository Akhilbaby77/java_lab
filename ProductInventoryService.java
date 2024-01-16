package com.manufacturing.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductInventoryService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/manufacturing";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "MySQL@password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println("Connected to the database");

            createTable(connection); // Create the table if not exists

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addProduct(connection);
                        break;
                    case 2:
                        viewProducts(connection);
                        break;
                    case 3:
                        System.out.println("Exiting the program");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } while (choice != 3);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS products ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(255) NOT NULL,"
                + "category VARCHAR(255),"
                + "manufacturer VARCHAR(255),"
                + "production_date DATE,"
                + "price DOUBLE"
                + ")";

        try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
            statement.execute();
        }
    }

    private static void addProduct(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Manufacturer: ");
        String manufacturer = scanner.nextLine();
        System.out.print("Enter Production Date (YYYY-MM-DD): ");
        String productionDate = scanner.next();
        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        String insertQuery = "INSERT INTO products (name, category, manufacturer, production_date, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, name);
            statement.setString(2, category);
            statement.setString(3, manufacturer);
            statement.setString(4, productionDate);
            statement.setDouble(5, price);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product added successfully!");
            } else {
                System.out.println("Failed to add the product.");
            }
        }
    }

    private static void viewProducts(Connection connection) throws SQLException {
        String selectQuery = "SELECT * FROM products";

        try (PreparedStatement statement = connection.prepareStatement(selectQuery);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.println("Product List:");
            System.out.println("ID\tName\tCategory\tManufacturer\tProduction Date\tPrice");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                String manufacturer = resultSet.getString("manufacturer");
                String productionDate = resultSet.getString("production_date");
                double price = resultSet.getDouble("price");

                System.out.println(id + "\t" + name + "\t" + category + "\t" + manufacturer + "\t" + productionDate + "\t" + price);
            }
        }
    }
}
