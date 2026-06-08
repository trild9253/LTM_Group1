package utils;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class SystemLogger {

    private static void logToDatabase(String logLevel, String message) {
        String sql = "INSERT INTO SystemLogs (log_level, message) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, logLevel);
            stmt.setString(2, message);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Không thể ghi log vào CSDL: " + e.getMessage());
        }
    }

    public static void logInfo(String message) {
        String logStr = "[INFO] " + LocalDateTime.now() + " - " + message;
        System.out.println(logStr); 
        logToDatabase("INFO", message); 
    }

    public static void logError(String error) {
        String logStr = "[ERROR] " + LocalDateTime.now() + " - " + error;
        System.err.println(logStr); 
        logToDatabase("ERROR", error); 
    }
}
