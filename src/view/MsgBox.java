package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MsgBox {

    // Màu chủ đạo của App bạn (Màu Cam)
    private static final Color MAU_CAM = new Color(232, 145, 82);
    private static final Color MAU_CHU = new Color(50, 50, 50);

    public static void show(Component parent, String message, String title) {
        JDialog dialog = new JDialog();
        dialog.setUndecorated(true); // Bỏ khung viền mặc định của Windows
        dialog.setModal(true); // Chặn không cho bấm vào cửa sổ khác khi chưa đóng thông báo
        dialog.setSize(400, 200);
        dialog.setLayout(new BorderLayout());

        // --- 1. Tạo viền bo tròn và bóng đổ (Giả lập) ---
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        // --- 2. Phần Tiêu đề (Header) ---
        JPanel pHeader = new JPanel(new BorderLayout());
        pHeader.setBackground(MAU_CAM);
        pHeader.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel lblTitle = new JLabel(title.toUpperCase());
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTitle.setForeground(Color.WHITE);

        // Nút X để đóng
        JLabel lblClose = new JLabel("X");
        lblClose.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblClose.setForeground(Color.WHITE);
        lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dialog.dispose();
            }
        });

        pHeader.add(lblTitle, BorderLayout.WEST);
        pHeader.add(lblClose, BorderLayout.EAST);

        // --- 3. Phần Nội dung (Body) ---
        JPanel pBody = new JPanel();
        pBody.setBackground(Color.WHITE);
        pBody.setLayout(new GridBagLayout()); // Căn giữa nội dung

        JTextArea txtMessage = new JTextArea(message);
        txtMessage.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtMessage.setForeground(MAU_CHU);
        txtMessage.setWrapStyleWord(true);
        txtMessage.setLineWrap(true);
        txtMessage.setEditable(false);
        txtMessage.setFocusable(false);
        txtMessage.setBackground(Color.WHITE);
        txtMessage.setSize(300, 100);
        // Giới hạn chiều rộng text để tự xuống dòng
        txtMessage.setPreferredSize(new Dimension(320, 80));

        pBody.add(txtMessage);

        // --- 4. Phần Nút bấm (Footer) ---
        JPanel pFooter = new JPanel();
        pFooter.setBackground(Color.WHITE);
        pFooter.setBorder(new EmptyBorder(0, 0, 15, 0));

        JButton btnOK = new JButton("Đồng ý");
        btnOK.setBackground(MAU_CAM);
        btnOK.setForeground(Color.WHITE);
        btnOK.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnOK.setFocusPainted(false);
        btnOK.setBorderPainted(false);
        btnOK.setPreferredSize(new Dimension(100, 35));
        btnOK.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hiệu ứng hover
        btnOK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btnOK.setBackground(new Color(210, 130, 70)); // Màu cam đậm hơn
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btnOK.setBackground(MAU_CAM);
            }
        });

        btnOK.addActionListener(e -> dialog.dispose());

        pFooter.add(btnOK);

        // --- Ráp nối ---
        contentPanel.add(pHeader, BorderLayout.NORTH);
        contentPanel.add(pBody, BorderLayout.CENTER);
        contentPanel.add(pFooter, BorderLayout.SOUTH);

        dialog.add(contentPanel);

        // Căn giữa so với cửa sổ cha
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
    // Biến lưu kết quả chọn của người dùng
    private static boolean result;

    public static boolean confirm(Component parent, String message) {
        result = false; // Mặc định là False (Hủy)

        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.setModal(true);
        dialog.setSize(400, 200);
        dialog.setLayout(new BorderLayout());

        // 1. Header
        JPanel pHeader = new JPanel(new BorderLayout());
        pHeader.setBackground(MAU_CAM);
        pHeader.setBorder(new EmptyBorder(10, 15, 10, 15));

        JLabel lblTitle = new JLabel("XÁC NHẬN");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTitle.setForeground(Color.WHITE);
        pHeader.add(lblTitle);

        // 2. Body
        JPanel pBody = new JPanel(new GridBagLayout());
        pBody.setBackground(Color.WHITE);
        pBody.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, new Color(220,220,220)));

        JTextArea txtMessage = new JTextArea(message);
        txtMessage.setFont(new Font("SansSerif", Font.PLAIN, 15));
        txtMessage.setForeground(MAU_CHU);
        txtMessage.setWrapStyleWord(true);
        txtMessage.setLineWrap(true);
        txtMessage.setEditable(false);
        txtMessage.setFocusable(false);
        txtMessage.setBackground(Color.WHITE);
        txtMessage.setPreferredSize(new Dimension(320, 60));
        pBody.add(txtMessage);

        // 3. Footer (Chứa 2 nút)
        JPanel pFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pFooter.setBackground(Color.WHITE);
        pFooter.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(220,220,220)));

        JButton btnHuy = new JButton("Hủy bỏ");
        btnHuy.setBackground(new Color(240, 240, 240));
        btnHuy.setForeground(MAU_CHU);
        btnHuy.setFocusPainted(false);
        btnHuy.setBorderPainted(false);
        btnHuy.setPreferredSize(new Dimension(100, 35));
        btnHuy.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHuy.addActionListener(e -> {
            result = false;
            dialog.dispose();
        });

        JButton btnDongY = new JButton("Đồng ý");
        btnDongY.setBackground(MAU_CAM);
        btnDongY.setForeground(Color.WHITE);
        btnDongY.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnDongY.setFocusPainted(false);
        btnDongY.setBorderPainted(false);
        btnDongY.setPreferredSize(new Dimension(100, 35));
        btnDongY.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDongY.addActionListener(e -> {
            result = true;
            dialog.dispose();
        });

        pFooter.add(btnHuy);
        pFooter.add(btnDongY);

        dialog.add(pHeader, BorderLayout.NORTH);
        dialog.add(pBody, BorderLayout.CENTER);
        dialog.add(pFooter, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);

        return result;
    }
}