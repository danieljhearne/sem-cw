package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates reports from the world database.
 */
public class ReportGenerator {

    private final Connection connection;

    /**
     * Default constructor uses App.connect().
     */
    public ReportGenerator() {
        this.connection = App.connect();
    }

    /**
     * Allows an external connection (useful for testing).
     */
    public ReportGenerator(Connection connection) {
        this.connection = connection;
    }

    /**
     * All countries ordered by population descending.
     */
    public List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();
        if (connection == null) return countries;

        String sql = "SELECT code, name, continent, region, population, capital "
                   + "FROM country ORDER BY population DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Country country = new Country(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("continent"),
                    rs.getString("region"),
                    rs.getLong("population"),
                    rs.getString("capital")
                );
                countries.add(country);
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
        if (connection == null) return cities;

        String sql = "SELECT city.Name AS city_name, country.Name AS country_name, "
                   + "city.District, city.Population "
                   + "FROM city JOIN country ON city.CountryCode = country.Code "
                   + "ORDER BY city.Population DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                City city = new City(
                    rs.getString("city_name"),
                    rs.getString("country_name"),
                    rs.getString("District"),
                    rs.getLong("Population")
                );
                cities.add(city);
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
        if (connection == null) return capitals;

        String sql = "SELECT city.Name AS city_name, country.Name AS country_name, city.Population "
                   + "FROM city JOIN country ON city.ID = country.Capital "
                   + "ORDER BY city.Population DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                City capital = new City(
                    rs.getString("city_name"),
                    rs.getString("country_name"),
                    null,
                    rs.getLong("Population")
                );
                capitals.add(capital);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving capital cities: " + e.getMessage());
        }
        return capitals;
    }

    /**
     * Total population of the world.
     */
    public long getPopulationOfWorld() {
        if (connection == null) return 0L;
        String sql = "SELECT SUM(population) AS total_population FROM country";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() ? rs.getLong("total_population") : 0L;
        } catch (SQLException e) {
            System.err.println("Error retrieving world population: " + e.getMessage());
            return 0L;
        }
    }
