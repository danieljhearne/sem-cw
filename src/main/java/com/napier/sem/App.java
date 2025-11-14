package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        // Load DB driver
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        Connection con = null;
        int retries = 100;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                Thread.sleep(1000);
                con = DriverManager.getConnection(
                        "jdbc:mysql://db:3306/world?useSSL=false&allowPublicKeyRetrieval=true",
                        "root",
                        "example");
                System.out.println("Successfully connected");
                Thread.sleep(1000);
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

        if (con != null)
        {
            // ----- CALL YOUR REPORT METHODS HERE -----
            generateTopCountriesByPopulationReport(con, 10);

            // Close connection
            try
            {
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Example report:
     * Top N countries in the world by population
     */
    public static void generateTopCountriesByPopulationReport(Connection con, int limit)
    {

        String sql =
                "SELECT Code, Name, Continent, Population " +
                        "FROM country " +
                        "ORDER BY Population DESC " +
                        "LIMIT ?";

        try
        {

            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, limit);


            ResultSet rset = pstmt.executeQuery();


            System.out.println("--------------------------------------------------------");

            rset.close();
            pstmt.close();
        }
        catch (SQLException e)
        {
            System.out.println("Failed to generate report: " + e.getMessage());
        }
    }
}