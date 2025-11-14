package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Entry point for the population reporting application.
 */
public class App {

    /**
     * Establish a connection to the MySQL `world` database.
     * This uses the same settings as the Docker-compose setup.
     */
    public static Connection connect() {
        try {
            // Load database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Could not load SQL driver");
            return null;
        }

        Connection connection = null;
        int retries = 10;

        for (int i = 0; i < retries; i++) {
            System.out.println("Connecting to database...");
            try {
                Thread.sleep(1000);
                // IMPORTANT: this URL/creds must match your docker-compose/db
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:33060/world?useSSL=false&allowPublicKeyRetrieval=true",
                        "root",
                        "example"
                );
                System.out.println("Successfully connected");
                break;
            } catch (SQLException e) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

        return connection;
    }

    public static void main(String[] args) {
        Connection connection = connect();
        if (connection == null) {
            System.err.println("Failed to connect to database. Exiting.");
            return;
        }

        try {
            // Use the shared connection for all reports
            ReportGenerator reportGenerator = new ReportGenerator(connection);

            System.out.println("Countries by population (world):");
            reportGenerator.getAllCountries()
                    .forEach(System.out::println);

            System.out.println("\nCities by population (world):");
            reportGenerator.getAllCities()
                    .forEach(System.out::println);

            System.out.println("\nCapital cities by population (world):");
            reportGenerator.getAllCapitalCities()
                    .forEach(System.out::println);

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
