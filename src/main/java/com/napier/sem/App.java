package com.napier.sem;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Entry point for the application. This class contains a helper to obtain a
 * connection to the MySQL world database. In a production environment the
 * connection details would typically be externalised.
 */
public class App {
    // … existing connect() method …

    public static void main(String[] args) {
        // Establish a connection to the world database
        Connection con = connect();
        if (con == null) {
            System.err.println("Failed to connect to database. Exiting.");
            return;
        }
        try {
            // Instantiate the report generator using the open connection
            ReportGenerator reportGenerator = new ReportGenerator(con);

            // Generate and display the first 10 countries by population
            System.out.println("Countries by population (top 10):");
            reportGenerator.getAllCountries()
                .stream()
                .limit(10)
                .forEach(country -> System.out.println(country.toString()));

            // Generate and display the first 10 cities by population
            System.out.println("\nCities by population (top 10):");
            reportGenerator.getAllCities()
                .stream()
                .limit(10)
                .forEach(city -> System.out.println(city.toString()));

            // Generate and display the first 10 capital cities by population
            System.out.println("\nCapital cities by population (top 10):");
            reportGenerator.getAllCapitalCities()
                .stream()
                .limit(10)
                .forEach(city -> System.out.println(city.toString()));

            // Display the total population of the world
            long worldPop = reportGenerator.getPopulationOfWorld();
            System.out.printf("\nTotal world population: %,d\n", worldPop);
        } finally {
            // Always close the connection when finished to release resources
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
