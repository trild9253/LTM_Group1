package Database;

import Logging.SystemLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    
    public boolean authenticate(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setString(1, username);
            pstmt.setString(2, password); 
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                SystemLogger.info("User đăng nhập thành công: " + username);
                return true;
            }
        } catch (SQLException e) {
            SystemLogger.error("Lỗi xác thực user: " + e.getMessage());
        }
        return false;
    }

    public boolean registerUser(String username, String password) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                SystemLogger.info("Tạo tài khoản thành công: " + username);
                return true;
            }
        } catch (SQLException e) {
            SystemLogger.error("Lỗi đăng ký user: " + e.getMessage());
        }
        return false;
    }
}
