package utils;

public class SystemLogger {
    public static void logInfo(String message) {
        System.out.println("[INFO] " + message);
    }
    public static void logError(String error) {
        System.err.println("[ERROR] " + error);
    }
}
