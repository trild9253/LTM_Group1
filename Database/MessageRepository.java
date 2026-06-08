package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageRepository {
    
    public boolean saveMessage(String sender, String content) {
        String sql = "INSERT INTO ChatHistory (sender, content) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, sender);
            stmt.setString(2, content);
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi MessageRepository.saveMessage: " + e.getMessage());
            return false;
        }
    }
}
