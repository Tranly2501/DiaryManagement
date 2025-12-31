import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class bai1 extends JFrame {

    public bai1() {
        setTitle("Java Swing Toolbar Demo");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        // 1. Ô chọn ngày (Dùng Spinner làm ví dụ đơn giản nếu không có thư viện ngoài)
        SpinnerDateModel model = new SpinnerDateModel();
        JSpinner dateSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yy");
        dateSpinner.setEditor(editor);
        dateSpinner.setPreferredSize(new Dimension(120, 30));
        add(dateSpinner);

        // --- Khu vực chứa các Icon ---
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));

        // 2. Icon chọn Biểu cảm (Emoji)
        JButton btnEmoji = createIconButton("😊");
        btnEmoji.addActionListener(e -> {
            String[] emojis = {"😊", "😂", "❤️", "👍",};
            String selected = (String) JOptionPane.showInputDialog(this, "Chọn biểu cảm:",
                    "Emoji Picker", JOptionPane.PLAIN_MESSAGE, null, emojis, emojis[0]);
            if(selected != null) System.out.println("Đã chọn: " + selected);
        });

        // 3. Icon chọn Bảng màu (Palette)
        JButton btnColor = createIconButton("🎨");
        btnColor.addActionListener(e -> {
            Color color = JColorChooser.showDialog(this, "Chọn màu sắc", Color.ORANGE);
            if (color != null) {
                System.out.println("Màu đã chọn: " + color);
            }
        });

        // 4. Icon chọn Ảnh (File Chooser)
        JButton btnImage = createIconButton("🖼️");
        btnImage.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "gif"));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Bạn đã chọn: " + selectedFile.getName());
            }
        });
        // 5. Icon chọn Font chữ
        JButton btnFont = createIconButton("Aa"); // Icon đại diện cho Font
        btnFont.setToolTipText("Chọn Font chữ");
        btnFont.addActionListener(e -> {
            // Lấy danh sách tất cả font có trong máy tính
            String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

            // Tạo một ComboBox để người dùng chọn font
            JComboBox<String> fontCombo = new JComboBox<>(fonts);
            fontCombo.setSelectedItem("Arial"); // Mặc định

            int option = JOptionPane.showConfirmDialog(this, fontCombo, "Chọn Font chữ", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                String selectedFontPath = (String) fontCombo.getSelectedItem();
                System.out.println("Font đã chọn: " + selectedFontPath);

                // Áp dụng font mới cho một component nào đó (ví dụ label hoặc textarea)
                // targetComponent.setFont(new Font(selectedFontPath, Font.PLAIN, 14));
            }
        });

        iconPanel.add(btnEmoji);
        iconPanel.add(btnColor);
        iconPanel.add(btnImage);
        iconPanel.add( btnFont);
        add(iconPanel);

        setLocationRelativeTo(null);
    }

    // Hàm hỗ trợ tạo Button giống Icon
    private JButton createIconButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new bai1().setVisible(true));
    }
}