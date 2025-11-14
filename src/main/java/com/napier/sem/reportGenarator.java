package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ReportGenerator {
    private Connection connection;
}

    public ReportGenerator() {
        this.connection = App.connect();
    }

//Get all countries in order of population descecnding
    public List<Country> getAllCountries() {
        //Empty list
        List<Country> countries = new ArrayList<>();
        //Sql query to get all countried by population
        String sql = "SELECT code, name, continent, region, population, capital FROM country ORDER BY population DESC";
    
        //prevents the app from crashing due to resource exhaustion by automaticly closing resources
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

             while (rs.next()) {
                // Create a new Country object from the current row's data
                Country country = new Country(
                    rs.getString("code"),
                    rs.getString("name"),        
                    rs.getString("continent"),   
                    rs.getString("region"),      
                    rs.getLong("population"),    
                    rs.getString("capital")      
                );
                
                //Adds to country list
                countries.add(country);
             }
        } 
        
        catch (SQLException e) {
            // Handle any SQL errors that occurs
            System.err.println("Error: " + e.getMessage());
        }
        
        // Return the list of countries
        return countries;
    }


//    public List<Country> getTopPopulatedCountries(int limit) {