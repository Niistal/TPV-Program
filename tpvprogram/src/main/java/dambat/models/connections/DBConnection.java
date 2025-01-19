package dambat.models.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 * This class provides a method to establish a connection to a PostgreSQL database.
 */
public class DBConnection {
    
    // JDBC URL, username, and password of the PostgreSQL server
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Admin123";

    /**
     * Establishes a connection to the PostgreSQL database using the provided URL, username, and password.
     *
     * @return a {@link java.sql.Connection} object to the database.
     * @throws SQLException if a database access error occurs or the URL is null.
     */
    @SuppressWarnings("exports")
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
