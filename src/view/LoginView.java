package view;

import controller.LoginController;
import model.LogInModel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class LoginView extends JFrame {
    private JButton btnLogin;
    private JButton btnReg;
    private JTextField txtUser;
    private JPasswordField txtPass;

    public LoginView() {
        // kích thước của cửa sổ  900x500
        setTitle("Dinary - Login");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// hiển thị cửa sổ ở chính giữa màn hình.
        setLocationRelativeTo(null);

        // tọa Icon cho thanh tiêu đề cửa sổ
        thietLapIconCuaSo();

        // Màu sắc chủ đạo
        Color mauCam = new Color(232, 145, 82);
        Color mauNenOnhap = new Color(255, 233, 208);
        Color mauTrang = Color.WHITE;

        // Chia đôi màn hình 50/50
        setLayout(new GridLayout(1, 2));

        // phần bên trái
        JPanel pnlLeft = new JPanel(new GridBagLayout());
        pnlLeft.setBackground(mauTrang);

        JLabel lblLogo = new JLabel();
        URL urlBook = getClass().getResource("/logo/LogoBook.png");
        if (urlBook != null) {
            ImageIcon iconGoc = new ImageIcon(urlBook);
            // chỉnh kích thước logo
            Image imgResized = iconGoc.getImage().getScaledInstance(170, 130, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(imgResized));
        };
        JLabel lblName = new JLabel("Dinary");
        lblName.setFont(new Font("Serif", Font.BOLD, 60));
        lblName.setForeground(mauCam);

        GridBagConstraints gbcL = new GridBagConstraints();
        gbcL.gridx = 0;
        gbcL.gridy = 0; pnlLeft.add(lblLogo, gbcL);
        gbcL.gridy = 1; pnlLeft.add(lblName, gbcL);

        //  FORM đăng nhập bên phải
        JPanel pnlRight = new JPanel(new GridBagLayout());
        pnlRight.setBackground(mauTrang);
        GridBagConstraints gbcR = new GridBagConstraints();
        gbcR.fill = GridBagConstraints.HORIZONTAL;
        gbcR.insets = new Insets(5, 50, 5, 50);
        gbcR.gridx = 0;

        JLabel lblUserIcon = new JLabel();
        URL urlAnh = getClass().getResource("/logo/icon.png"); // Tìm file icon.png trong src

        ImageIcon iconGoc = new ImageIcon(urlAnh);
        Image imgChinh = iconGoc.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);//điều chỉnh  co giãn icon
        lblUserIcon.setIcon(new ImageIcon(imgChinh));
        lblUserIcon.setHorizontalAlignment(SwingConstants.CENTER);
        gbcR.gridy = 0; pnlRight.add(lblUserIcon, gbcR);

        // Các ô nhập liệu
        gbcR.gridy = 1; pnlRight.add(taoNhan("Tên đăng nhập", mauCam), gbcR);
        txtUser = taoOnhap("username", mauNenOnhap, false);
        gbcR.gridy = 2; pnlRight.add(txtUser, gbcR);

        gbcR.gridy = 3; pnlRight.add(taoNhan("Mật khẩu", mauCam), gbcR);
        txtPass = (JPasswordField) taoOnhap("password", mauNenOnhap, true);
        gbcR.gridy = 4; pnlRight.add(txtPass, gbcR);

        // Nút bấm
        btnLogin = taoNut("Đăng nhập", mauCam);
        btnReg = taoNut("Đăng ký", mauCam);

        JPanel pnlBtns = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        pnlBtns.setOpaque(false);
        pnlBtns.add(btnLogin);
        pnlBtns.add(btnReg);
        gbcR.gridy = 5; gbcR.insets = new Insets(20, 50, 5, 50);
        pnlRight.add(pnlBtns, gbcR);

        add(pnlLeft);
        add(pnlRight);
    }
    public JButton getBtnLogin() { return btnLogin; }
    public JButton getBtnReg() { return btnReg; }

    // Hàm thiết lập Icon tiêu đề cửa sổ
    private void thietLapIconCuaSo() {
        URL url = getClass().getResource("/logo/book.png");
        if (url != null) {
            Image iconApp = Toolkit.getDefaultToolkit().createImage(url);
            this.setIconImage(iconApp);
        }
    }
    // tạo nhan
    private JLabel taoNhan(String text, Color c) {
        JLabel l = new JLabel(text);
        l.setForeground(c);
        return l;
    }

//    private JTextField taoOnhap(String fieldName, Color bg, boolean laMatKhau) {
//        JTextField t = laMatKhau ? new JPasswordField() : new JTextField();
//        t.setBackground(bg);
//        t.setPreferredSize(new Dimension(300, 35));
//        t.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
//        if (laMatKhau) ((JPasswordField)t).setEchoChar('•'); // Ẩn mật khẩu
//        return t;
//    }

    private JTextField taoOnhap(String fieldName, Color bg, boolean laMatKhau) {
        JTextField field;
        if (laMatKhau) {
            field = new JPasswordField();
            ((JPasswordField) field).setEchoChar('•');
            txtPass = (JPasswordField) field;
        } else {
            field = new JTextField();
            txtUser = field;
        }

        field.setBackground(bg);
        field.setPreferredSize(new Dimension(300, 35));
        field.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        return field;
    }
    public JTextField getTxtUser() { return txtUser; }
    public JPasswordField getTxtPass() { return txtPass; }

    private JButton taoNut(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setPreferredSize(new Dimension(120, 35));
        return b;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            LogInModel logInModel = new LogInModel();
            new LoginController(loginView, logInModel);
            loginView.setVisible(true);
        });
    }
}