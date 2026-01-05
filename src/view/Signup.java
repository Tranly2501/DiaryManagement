package view;
import controller.RegisterController;
import model.RegisterModel;
import javax.swing.*;


import java.awt.*;
import java.net.URL;
public class Signup extends JFrame {
    private JComponent inputField;
    private JTextField fullname;
    private JTextField email;
    private JTextField phone;
    private JTextField username;
    private JPasswordField password;
    private JPasswordField rePassword;
    private JButton btnDangKy;
    private JButton btnQuaylai;
    private JCheckBox check;

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
        formPanel.add(taoOnhap("Họ và tên","fullname", mauCam, mauOnhap));
        formPanel.add(taoOnhap("Tên đăng nhập", "username", mauCam, mauOnhap));
        formPanel.add(taoOnhap("Số điện thoại", "phone", mauCam, mauOnhap));
        formPanel.add(taoOnhap("Mật khẩu", "password", mauCam, mauOnhap));
        formPanel.add(taoOnhap("Email", "email", mauCam, mauOnhap));
        formPanel.add(taoOnhap("Xác nhận lại mật khẩu", "rePassword", mauCam, mauOnhap));

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // nút bấm
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        buttonPanel.setBackground(mauNen);

        btnDangKy = taoNut("Đăng ký", mauCam);
        btnQuaylai = taoNut("Quay Lại", mauCam);
        buttonPanel.add(btnDangKy);
        buttonPanel.add(btnQuaylai);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        //checkBox Password
        ImageIcon chechOn = new ImageIcon("/logo/Eye.png");
        ImageIcon chechOff = new ImageIcon("/logo/Eye off.png");
        check = new JCheckBox();
        check.setIcon(chechOff);
        check.setSelectedIcon(chechOn);
        check.setBorder(null);
        check.setContentAreaFilled(false);
    }

    public JCheckBox getCheckPass() { return check; }
    public JButton getbtnDangKy() { return btnDangKy; }
    public JButton getbtnQuayLai() { return btnQuaylai; }

    // Hàm thiết lập Icon tiêu đề cửa sổ
    private void thietLapIconCuaSo() {
        URL url = getClass().getResource("/logo/book.png");
        if (url != null) {
            Image iconApp = Toolkit.getDefaultToolkit().createImage(url);
            this.setIconImage(iconApp);
        }
    }

    private JPanel taoOnhap(String labelText, String fieldName, Color colorText, Color colorField) {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.setBackground(new Color(253, 246, 240));

        JLabel lbl = new JLabel(labelText);
        lbl.setForeground(colorText);
        lbl.setFont(new Font("Arial", Font.PLAIN, 15));

         // passwordField
        if (fieldName.equals("password") || fieldName.equals("rePassword")) {
            //panel password dùng BorderLayout
            JPanel panelPassword = new JPanel(new BorderLayout());
            JPasswordField passwordField = new JPasswordField();
            panelPassword.setBackground(colorField);
            panelPassword.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            passwordField = new JPasswordField();
            passwordField.setBorder(null);
            passwordField.setBackground(colorField);
            panelPassword.add(passwordField, BorderLayout.CENTER);
            panelPassword.add(check, BorderLayout.EAST);

            if(fieldName.equals("password")) {
                password = passwordField;
            } else {
                rePassword = passwordField;
            }
            p.add(lbl, BorderLayout.NORTH);
            p.add(panelPassword, BorderLayout.CENTER);
            return p;
        }

        //textField
        switch(fieldName) {
            case "fullname":
                fullname = new JTextField();
                inputField = fullname;
                break;
            case "email":
                email = new JTextField();
                inputField = email;
                break;
            case "phone":
                phone = new JTextField();
                inputField = phone;
                break;
            case "username":
                username = new JTextField();
                inputField = username;
                break;
            default:
                inputField = new JTextField();
        }

        inputField.setBackground(colorField);
        inputField.setPreferredSize(new Dimension(0, 40));
        inputField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Cách lề trong

        p.add(lbl, BorderLayout.NORTH);
        p.add(inputField, BorderLayout.CENTER);
        return p;
    }
    public JTextField getFullname() { return fullname; }
    public JTextField getEmail() { return email; }
    public JTextField getPhone() { return phone; }
    public JTextField getUsername() { return username; }
    public JPasswordField getPassword() { return password; }
    public JPasswordField getRePassword() { return rePassword; }

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
        SwingUtilities.invokeLater(() -> {
            Signup signup = new Signup();
            RegisterModel reModel = new RegisterModel();
            new RegisterController(signup, reModel);
            signup.setVisible(true);
        });
    }
}