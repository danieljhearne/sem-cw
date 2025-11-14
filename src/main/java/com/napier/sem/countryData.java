package com.napier.sem;


//Country record from world.sql database
public class Country {
    private String code;
    private String name;
    private String contident;
    private String region;
    private long population;
    private String capital;

    //Constructor
    public Country(String code, String name, String contident, String region, long population, String capital)
    {
        this.code = code;
        this.name = name;
        this.contident = contident;
        this.region = region;
        this.population = population;
        this.capital = capital;
    }

    //Getters
    public String getCode() {return code;}
    public String getName() {return name;}
    public String getContident() {return contident;}
    public String getRegion() {return region;}
    public long getPopulation() {return population;}
    public String getCapital() {return capital;}

    //String overide. Making it readable
    public String toString() {
        return String.format("%s (%s) - %s - Population: %,d", 
                           name, code, continent, population);
    }
}