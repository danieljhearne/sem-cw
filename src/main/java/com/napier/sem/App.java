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

        // Generate and display all countries ordered by population
        System.out.println("Countries by population:");
        reportGenerator.getAllCountries()
            .forEach(country -> System.out.println(country.toString()));

        // Generate and display all cities ordered by population
        System.out.println("\nCities by population:");
        reportGenerator.getAllCities()
            .forEach(city -> System.out.println(city.toString()));

        // Generate and display all capital cities ordered by population
        System.out.println("\nCapital cities by population:");
        reportGenerator.getAllCapitalCities()
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
