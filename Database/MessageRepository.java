package database;

public class MessageRepository {
    public void saveMessage(String sender, String content) {
        // Code lưu tin nhắn vào bảng lịch sử chat
        System.out.println("Đã lưu tin nhắn của: " + sender);
    }
}
