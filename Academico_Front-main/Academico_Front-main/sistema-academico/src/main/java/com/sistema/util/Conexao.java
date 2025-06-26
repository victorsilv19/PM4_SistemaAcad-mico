package com.sistema.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = System.getenv().getOrDefault("DB_URL", 
        "jdbc:postgresql://localhost:5432/sistema_academico");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "postgres");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "Pedro14789");

    public static Connection getConnection() throws SQLException {
        if (PASSWORD.isEmpty()) {
            throw new SQLException("Database password not configured. Set DB_PASSWORD environment variable.");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
