package view;
import javax.swing.*;


import java.awt.*;
import java.net.URL;
public class Signup extends JFrame {

    public Signup() {

        setTitle("Hệ Thống Đăng Ký");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// hiển thị cửa sổ ở chính giữa màn hình.
        setLocationRelativeTo(null);
        // tọa Icon cho thanh tiêu đề cửa sổ
        thietLapIconCuaSo();

        // màu sắc chủ đạo
        Color mauNen = new Color(253, 246, 240);
        Color mauCam = new Color(232, 145, 82);
        Color mauOnhap = new Color(255, 233, 208);

        // panel chính  dùng BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(0, 30));
        mainPanel.setBackground(mauNen);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        // tiêu đề
        JLabel lblTitle = new JLabel("TẠO TÀI KHOẢN MỚI", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setForeground(mauCam);
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // Form dùng  GridLayout: 3 hàng, 2 cột
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 60, 20));
        formPanel.setBackground(mauNen);

        // thêm các ô nhập
        formPanel.add(taoOnhap("Họ và tên", mauCam, mauOnhap));
        formPanel.add(taoOnhap("Tên đăng nhập", mauCam, mauOnhap));
        formPanel.add(taoOnhap("Số điện thoại", mauCam, mauOnhap));
        formPanel.add(taoOnhap("Mật khẩu", mauCam, mauOnhap));
        formPanel.add(taoOnhap("Email", mauCam, mauOnhap));
        formPanel.add(taoOnhap("Xác nhận lại mật khẩu", mauCam, mauOnhap));

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // nút bấm
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        buttonPanel.setBackground(mauNen);

        JButton btnDangKy = taoNut("Đăng ký", mauCam);
        JButton btnQuayLai = taoNut("Quay Lại", mauCam);

     // chuyển trang  đăng ký xong -> Mở trang chính
        btnDangKy.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Đăng ký thành công!");
            new HomeView().setVisible(true);
            this.dispose();
        });

// chuyển trang bấm quay lại  -> Về trang đăng nhập
        btnQuayLai.addActionListener(e -> {
            new view.LoginView();
            this.dispose();
        });
        buttonPanel.add(btnDangKy);
        buttonPanel.add(btnQuayLai);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Hàm thiết lập Icon tiêu đề cửa sổ
    private void thietLapIconCuaSo() {
        URL url = getClass().getResource("/logo/book.png");
        if (url != null) {
            Image iconApp = Toolkit.getDefaultToolkit().createImage(url);
            this.setIconImage(iconApp);
        }
    }
    private JPanel taoOnhap(String labelText, Color colorText, Color colorField) {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.setBackground(new Color(253, 246, 240));

        JLabel lbl = new JLabel(labelText);
        lbl.setForeground(colorText);
        lbl.setFont(new Font("Arial", Font.PLAIN, 15));

        // mật khẩu dùng JPasswordField
        JComponent inputField;
        if (labelText.toLowerCase().contains("mật khẩu")) {
            JPasswordField pf = new JPasswordField();
            pf.setEchoChar('•'); // thiết lập ký tự che là dấu chấm tròn
            inputField = pf;
        } else {
            inputField = new JTextField();
        }

        inputField.setBackground(colorField);
        inputField.setPreferredSize(new Dimension(0, 40));
        inputField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Cách lề trong

        p.add(lbl, BorderLayout.NORTH);
        p.add(inputField, BorderLayout.CENTER);
        return p;
    }

    private JButton taoNut(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(180, 45));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Arial", Font.BOLD, 16));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Signup().setVisible(true));
    }
}