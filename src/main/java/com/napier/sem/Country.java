package com.napier.sem;

/**
 * Immutable data object representing a country. The class
 * encapsulates the ISO country code, name, continent, region,
 * population and capital. All fields are set via the constructor
 * and exposed through getter methods to ensure immutability.
 */
public class Country {

    private final String code;
    private final String name;
    private final String continent;
    private final String region;
    private final long population;
    private final String capital;


    public Country(String code, String name, String continent,
                   String region, long population, String capital) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
        this.capital = capital;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }

    public long getPopulation() {
        return population;
    }

    public String getCapital() {
        return capital;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) – %s – Population: %,d",
                name, code, continent, population);
    }
}