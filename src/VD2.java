public class VD2 {
}
import javax.swing.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiaryUI extends JFrame {
    private JTextArea txtContent;
    private JButton btnExport;

    public DiaryUI() {
        // Khởi tạo giao diện đơn giản
        setTitle("Ứng dụng Nhật ký Cá nhân");
        txtContent = new JTextArea(10, 30);
        btnExport = new JButton("Xuất file TXT");

        // Thiết lập sự kiện khi nhấn nút
        btnExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = txtContent.getText();

                if (content.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập nội dung!");
                } else {
                    // Gọi code tổng từ lớp FileExporter
                    FileExporter.exportWithAppend(content);
                    JOptionPane.showMessageDialog(null, "Đã lưu nội dung thành công!");
                }
            }
        });

        // Thêm các thành phần vào màn hình
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(txtContent));
        panel.add(btnExport);
        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new DiaryUI();
    }
}