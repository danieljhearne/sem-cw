package com.napier.sem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Country} and {@link City} model classes.
 */
public class ModelTest {

    @Test
    public void testCountryToString() {
        Country country = new Country("GBR", "United Kingdom", "Europe", "Northern Europe", 67886011L, "London");
        String repr = country.toString();
        assertTrue(repr.contains("United Kingdom"));
        assertTrue(repr.contains("GBR"));
        assertTrue(repr.contains("Europe"));
        assertTrue(repr.contains("67,886,011"), "Population should be formatted with commas");
    }

    @Test
    public void testCityToStringWithDistrict() {
        City city = new City("London", "United Kingdom", "England", 9000000L);
        String repr = city.toString();
        assertTrue(repr.contains("London"));
        assertTrue(repr.contains("United Kingdom"));
        assertTrue(repr.contains("England"));
        assertTrue(repr.contains("9,000,000"), "Population should be formatted with commas");
    }

    @Test
    public void testCityToStringWithoutDistrict() {
        City city = new City("Washington", "United States", null, 702455L);
        String repr = city.toString();
        assertTrue(repr.contains("no district"));
    }
}