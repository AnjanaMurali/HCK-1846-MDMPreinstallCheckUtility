package com.precheck;
import java.sql.*;
public class DatabaseCheck {
    public static String checkDatabaseConnection(String dbUrl, String dbUser, String dbPassword) throws ClassNotFoundException {
    	dbUrl = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=master";
        dbUser = "sa";
        dbPassword = "Mssql123";
        try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			if (connection != null) {
                return "Database connection successful.";
            } else {
                return "Failed to make a database connection.";
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            return "Database connection failed: " + e.getMessage();
        }
		
			//e.printStackTrace();
		}
    }

  