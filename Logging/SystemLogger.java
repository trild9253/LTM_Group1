package utils;

public class SystemLogger {
    public static void logInfo(String message) {
        // Code ghi lại các sự kiện bình thường của hệ thống
        System.out.println("[INFO] " + message);
    }

    public static void logError(String error) {
        // Code ghi lại lỗi hệ thống
        System.err.println("[ERROR] " + error);
    }
}
