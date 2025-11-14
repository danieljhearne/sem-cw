package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates population reports from the world database.
 */
public class ReportGenerator {

    private final Connection connection;


    public ReportGenerator() {
        this.connection = App.connect();
    }


    public ReportGenerator(Connection connection) {
        this.connection = connection;
    }

    /**
     * All countries ordered by population descending.
     */
    public List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();
        if (connection == null) {
            return countries;
        }
        final String sql =
                "SELECT code, name, continent, region, population, capital " +
                        "FROM country ORDER BY population DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                countries.add(new Country(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("continent"),
                        rs.getString("region"),
                        rs.getLong("population"),
                        rs.getString("capital")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving countries: " + e.getMessage());
        }
        return countries;
    }

    /**
     * All cities ordered by population descending.
     */
    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        if (connection == null) {
            return cities;
        }
        final String sql =
                "SELECT city.Name AS city_name, country.Name AS country_name, " +
                        "city.District AS district, city.Population AS population " +
                        "FROM city JOIN country ON city.CountryCode = country.Code " +
                        "ORDER BY city.Population DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                cities.add(new City(
                        rs.getString("city_name"),
                        rs.getString("country_name"),
                        rs.getString("district"),
                        rs.getLong("population")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving cities: " + e.getMessage());
        }
        return cities;
    }

    /**
     * All capital cities ordered by population descending.
     */
    public List<City> getAllCapitalCities() {
        List<City> capitals = new ArrayList<>();
        if (connection == null) {
            return capitals;
        }
        final String sql =
                "SELECT city.Name AS city_name, country.Name AS country_name, " +
                        "city.Population AS population " +
                        "FROM city JOIN country ON city.ID = country.Capital " +
                        "ORDER BY city.Population DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                capitals.add(new City(
                        rs.getString("city_name"),
                        rs.getString("country_name"),
                        null,
                        rs.getLong("population")));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving capital cities: " + e.getMessage());
        }
        return capitals;
    }
}
