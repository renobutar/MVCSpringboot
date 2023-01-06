package com.example.demo.tools;

import java.sql.*;

public class DbConnection {
    private static Connection con;

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_jdbc","root","@BTRrone1512");
        } catch (Exception e) {
            System.out.println("Error + "+ e.getMessage());
        }
        return con;
    }
}