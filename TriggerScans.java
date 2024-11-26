import java.security.*;
import java.sql.*;

public class TriggerScans {
    public static void main(String[] args) {
        // Mock RSA private key
        String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIICWgIBAAKBgG9RfBzEJzXKm+gCJZfZj5DyR7hoyTzK+5LJi4k2LqZsu3w7xvW1\n" +
                "C5M2IiU59wR0vOwZ/Sw3xb+Xe4mP2qKzX4hZM/Nu3jwLWgQ6xTQY0e4wB9npi4jR\n" +
                "fE6d2sZKdGksVr7DhX2zK4GyRJ5WYKZ2iL0yv5CfT5I6xjvlxW9dE4bLAgMBAAEC\n" +
                "gYBm3mJ6zQrZaL3gjW4Tz4O8d+JZf5c4nHwYvBEoTz6qE4wX9yZgRvD0JgXfRqjh\n" +
                "n1qZmF7+Qs5uMHs1yWUEJ6S1i0Kk5yvWt1zKp+PyUQ9Jb0QIvR/V5M7RzlaYb3v7\n" +
                "mRvFQb8xT7X6zZ5jNpEg7jPMyz+ZlO0dS0YmKjdmxXuYxM8t2QJBAIbzYI7b1a8D\n" +
                "8PW7J0F0tA9Q1xL8z7TlY0t2LJZC/lntT7lX8cQnM2UyIw7rUc3Uw0+8T9lH2+Od\n" +
                "uXUyGwMCQQC0c3HlYmJ1WeIkf5nE+9Zp1QnK2b7t1bVzBd4cfxsH8LQX8Qy0b2aB\n" +
                "7y9qfG0+xiNvG3wIjgc9xObpc3nEzF2fAkBvPmO1w2s7xJ3t6v0lLdI8f5f6i1rq\n" +
                "R2y5vZMn9d7zr7a9lB6bNqW4xW2ZgQFjZv8S9P+O0QDZ3sU9XGvU5vW9AkEAqG2E\n" +
                "3jRyX/1S50Km1/XRR

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "password");

            // Execute a query
            stmt = conn.createStatement();
            String sql = "SELECT id, name, age FROM mytable";
            String badSQL = "SELECT id FROM mytable WHERE name='" + name + "' AND age='" + age + "'";
            rs = stmt.executeQuery(sql);

            // Process the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
