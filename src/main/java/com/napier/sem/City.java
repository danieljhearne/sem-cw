package com.napier.sem;

/**
 * Immutable data object representing a city. Instances of this
 * class encapsulate the name of the city, the country it belongs
 * to, the district (if any) and the population. All fields are
 * provided via the constructor and there are only getters so that
 * values cannot be changed after creation.
 */
public class City {

    private final String name;
    private final String country;
    private final String district;
    private final long population;

    public City(String name, String country, String district, long population) {
        this.name = name;
        this.country = country;
        this.district = district;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getDistrict() {
        return district;
    }

    public long getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        String districtName = (district == null || district.isEmpty()) ? "no district" : district;
        return String.format("%s, %s (%s) – Population: %,d", name, country, districtName, population);
    }
}
