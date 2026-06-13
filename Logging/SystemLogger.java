package Logging;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SystemLogger {
    private static final String LOG_FILE = "server_system_log.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String level, String message) {
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            String time = LocalDateTime.now().format(formatter);
            out.println(time + " [" + level + "] " + message);
        } catch (IOException e) {
            System.err.println("Không thể ghi log: " + e.getMessage());
        }
    }

    public static void info(String message) {
        log("INFO", message);
    }

    public static void error(String message) {
        log("ERROR", message);
    }
    
    public static void warning(String message) {
        log("WARNING", message);
    }
}
