package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatFrame extends JFrame {
    JTextPane chatPane = new JTextPane();
    String htmlContent = "";

    JTextArea inputArea;

    public ChatFrame() {
        setTitle("NetChat Room");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0x0d0f1a));

        chatPane.setContentType("text/html");
        chatPane.setText("<html><body style='font-family:Segoe UI;'>"
                + "<b>Minh</b>: Xin chào!</body></html>");

        add(new JScrollPane(chatPane), BorderLayout.CENTER);

        inputArea = new JTextArea(2, 30);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        inputArea.setBackground(new Color(0x232842));
        inputArea.setForeground(Color.WHITE);
        inputArea.setCaretColor(new Color(0x7c3aed));

        inputArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !e.isShiftDown()) {
                    e.consume(); // ngăn dòng mới
                    sendMessage();
                }
            }
        });

        add(inputArea, BorderLayout.SOUTH);
    }

    void appendMessage(String sender, String text, boolean isOwn) {
        String bg = isOwn ? "#7c3aed" : "#1a1e35";
        String align = isOwn ? "right" : "left";
        String radius = isOwn ? "18px 18px 4px 18px" : "18px 18px 18px 4px";

        htmlContent += "<div style='text-align:" + align + ";margin:4px 8px;'>" +
                "<span style='" +
                "  display:inline-block;" +
                "  background:" + bg + ";" +
                "  color:#e2e8f0;" +
                "  border-radius:" + radius + ";" +
                "  padding:8px 14px;" +
                "  max-width:65%;font-size:13px;'>" +
                "<b>" + sender + "</b><br/>" + text +
                "</span></div>";

        chatPane.setText("<html><body style='margin:10px;'>" + htmlContent + "</body></html>");
        chatPane.setCaretPosition(chatPane.getDocument().getLength());
    }

    private void sendMessage() {
        String text = inputArea.getText().trim();
        if (!text.isEmpty()) {
            appendMessage("Bạn", text, true);
            inputArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatFrame frame = new ChatFrame();
            frame.setVisible(true);
            frame.appendMessage("Minh", "Chào bạn, mình vừa kết nối thành công!", false);
        });
    }
}