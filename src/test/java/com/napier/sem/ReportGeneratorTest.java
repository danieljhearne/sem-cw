package com.napier.sem;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link ReportGenerator} class. These tests do not
 * connect to a real database; instead they verify that the methods
 * behave sensibly when provided a {@code null} connection and do not
 * throw unexpected exceptions.
 */
public class ReportGeneratorTest {

    @Test
    public void testGetAllCountriesWithNullConnection() {
        ReportGenerator generator = new ReportGenerator((Connection) null);
        List<Country> countries = generator.getAllCountries();
        assertNotNull(countries, "Method should never return null");
        assertTrue(countries.isEmpty(), "No connection should yield an empty list");
    }

    @Test
    public void testGetAllCitiesWithNullConnection() {
        ReportGenerator generator = new ReportGenerator((Connection) null);
        List<City> cities = generator.getAllCities();
        assertNotNull(cities, "Method should never return null");
        assertTrue(cities.isEmpty(), "No connection should yield an empty list");
    }

    @Test
    public void testGetAllCapitalCitiesWithNullConnection() {
        ReportGenerator generator = new ReportGenerator((Connection) null);
        List<City> capitals = generator.getAllCapitalCities();
        assertNotNull(capitals, "Method should never return null");
        assertTrue(capitals.isEmpty(), "No connection should yield an empty list");
    }
}