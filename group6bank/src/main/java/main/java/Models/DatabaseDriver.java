package main.java.Models;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseDriver {
    private Connection conn;

    public DatabaseDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:330
        }
    }
}
