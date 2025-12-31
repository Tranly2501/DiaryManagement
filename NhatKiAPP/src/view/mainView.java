package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class mainView extends JFrame {
    // Bảng màu
    Color mauCam = new Color(232, 145, 82);
    Color mauNenTren = new Color(208, 158, 115);
    Color mauNenMenu = new Color(255, 240, 225);
    Color mauTheNhatKy = new Color(225, 225, 225);



    public mainView() {
        setTitle("Dinary - Nhật ký của bạn");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
        // add vào
        add(taoThanhTren(), BorderLayout.NORTH);
        add(taoMenuTrai(), BorderLayout.WEST);
        add(taoVungNoiDung(), BorderLayout.CENTER);

        // tọa Icon cho thanh tiêu đề cửa sổ
        thietLapIconCuaSo();
    }

    private JPanel taoThanhTren() {
        JPanel pTren = new JPanel(new GridBagLayout()); // dung bố cục GridBagLayout cho thanh trên
        pTren.setBackground(mauNenTren);
        pTren.setPreferredSize(new Dimension(900, 65));
        pTren.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)); //khoảng cách bên trong ô trắng

        GridBagConstraints gbc = new GridBagConstraints();// là thành phần của GridBagLayout
        gbc.insets = new Insets(0, 20, 0, 20); // insets khoảng trống bên ngoài (Top, Left, Bottom, Right)

        // Logo
        JLabel lLogo = new JLabel("Dinary");
        lLogo.setFont(new Font("Serif", Font.BOLD, 26));
        gbc.gridx = 0; //  vị trí trục (ngang )cột ngoài cùng bên trái ,giá trị =0 tương ứng cột 1
        pTren.add(lLogo, gbc);

        // thanh tìm kiếm
        JPanel pTimKiem = new JPanel(new BorderLayout(10, 0));
        pTimKiem.setBackground(Color.WHITE);
        pTimKiem.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10) //  khoảng cách bên trong ô trắng
        ));
        pTimKiem.setPreferredSize(new Dimension(450, 35));

        // Icon kính lúp
        JLabel lblIconSearch = new JLabel(layIcon("/logo/Search.png", 18, 18));

        JTextField txtSearch = new JTextField("search");
        txtSearch.setBorder(null); // Xóa viền
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 14));

        pTimKiem.add(lblIconSearch, BorderLayout.WEST);
        pTimKiem.add(txtSearch, BorderLayout.CENTER);

        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        pTren.add(pTimKiem, gbc);

        // Nút viết mới
        JButton btnNew = new JButton("Viết nhật kí mới");
        btnNew.setBackground(mauCam);
        btnNew.setForeground(Color.WHITE);
        btnNew.setFont(new Font("Arial", Font.BOLD, 13));
        btnNew.setFocusPainted(false);
        btnNew.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // bấm nút viết mới đer chuyển dang Wtriteview
        btnNew.addActionListener(e -> {
            new WriteView().setVisible(true);
            this.dispose();                   // đóng  trang chủ hiện tại
        });

        gbc.gridx = 2; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        pTren.add(btnNew, gbc);

        return pTren;
    }

    private JPanel taoMenuTrai() {
        JPanel pnlTrai = new JPanel();
        pnlTrai.setLayout(new BoxLayout(pnlTrai, BoxLayout.Y_AXIS));// các thành phần xếp từ trên xuống dưới
        pnlTrai.setBackground(mauNenMenu);
        pnlTrai.setPreferredSize(new Dimension(230, 440));
        pnlTrai.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15)); // tạo khoảng cách pading

        JLabel lblAvatar = new JLabel(layIcon("/logo/People.png", 80, 80));
        lblAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);//Căn giữa ngang trong BoxLayout
        JLabel lblUser = new JLabel("Username1");
        lblUser.setForeground(mauCam);
        lblUser.setFont(new Font("Arial", Font.BOLD, 16));
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        // thêm vào panel
        pnlTrai.add(lblAvatar);
        pnlTrai.add(Box.createVerticalStrut(10));// kcachs avatar và username
        pnlTrai.add(lblUser);
        pnlTrai.add(Box.createVerticalStrut(40)); //kcach username với menu nút

        pnlTrai.add(taoNutAction("Xóa nhật kí", "/logo/Trash.png", "ACTION_XOA"));
        pnlTrai.add(Box.createVerticalStrut(10));
        pnlTrai.add(taoNutAction("Sửa nhật kí", "/logo/Wrench.png", "ACTION_SUA"));
        pnlTrai.add(Box.createVerticalStrut(10));
        pnlTrai.add(taoNutAction("Xuất file", "/logo/Upload to the Cloud.png", "ACTION_XUAT"));

        pnlTrai.add(Box.createVerticalGlue());// đẩy nút Đăng xuất xuống dưới cùng của panel
        pnlTrai.add(taoNutAction("Đăng xuất", "/logo/Vector.png", "ACTION_LOGOUT"));

        return pnlTrai;
    }

    private JButton taoNutAction(String text, String iconPath, String actionCommand) {
        JButton btn = new JButton(text, layIcon(iconPath, 25, 25));
        btn.setFont(new Font("Arial", Font.PLAIN, 15));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setIconTextGap(15);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(220, 45));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setForeground(mauCam); }
            public void mouseExited(MouseEvent e) { btn.setForeground(Color.BLACK); }
        });

        btn.addActionListener(e -> {
            switch (actionCommand) { // giả lập xử lí sự kiện = text
                case "ACTION_XOA": JOptionPane.showMessageDialog(this, "Bạn vừa bấm Xóa nhật ký!"); break;
                case "ACTION_SUA": JOptionPane.showMessageDialog(this, "Bạn vừa bấm Sửa nhật ký!"); break;
                case "ACTION_XUAT": JOptionPane.showMessageDialog(this, "Đang xuất file..."); break;
                case "ACTION_LOGOUT":
                    // JOptionPane.YES_NO_OPTION sẽ chỉ hiển thị 2 nút Yes và No
                    int opt = JOptionPane.showConfirmDialog(
                            this,
                            "Bạn có muốn đăng xuất?",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (opt == JOptionPane.YES_OPTION) {
                        new LoginView().setVisible(true); // quay lại trang đăng nhập
                        this.dispose();                  // đóng trang hiện tại
                    }
                    break;
            }
        });

        return btn;
    }

    // Hàm thiết lập Icon tiêu đề cửa sổ
    private void thietLapIconCuaSo() {
        URL url = getClass().getResource("/logo/book.png");
        if (url != null) {
            Image iconApp = Toolkit.getDefaultToolkit().createImage(url);
            this.setIconImage(iconApp);
        }
    }
    private JScrollPane taoVungNoiDung() {
        JPanel pnlList = new JPanel();
        pnlList.setLayout(new BoxLayout(pnlList, BoxLayout.Y_AXIS));
        pnlList.setBackground(Color.WHITE);
        pnlList.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        for (int i = 1; i <= 4; i++) {
            pnlList.add(taoTheNhatKy("28/12/25", "Nhật ký số " + i, "Nội dung của nhật ký..."));
            pnlList.add(Box.createVerticalStrut(20));
        }

        JScrollPane scroll = new JScrollPane(pnlList);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(15); // giúp việc cuộn bằng chuột mượt
        return scroll;
    }

    private JPanel taoTheNhatKy(String date, String title, String content) {
        JPanel card = new JPanel(new BorderLayout(10, 5));
        card.setBackground(mauTheNhatKy);
        card.setMaximumSize(new Dimension(600, 120));
        card.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel lblDate = new JLabel(date);
        lblDate.setFont(new Font("Arial", Font.ITALIC, 12));
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        JLabel lblContent = new JLabel(content);

        card.add(lblDate, BorderLayout.NORTH);
        card.add(lblTitle, BorderLayout.CENTER);
        card.add(lblContent, BorderLayout.SOUTH);

        return card;
    }

    private ImageIcon layIcon(String path, int w, int h) {
        try {
            URL res = getClass().getResource(path);
            if (res != null) {
                Image img = new ImageIcon(res).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);//co giãn khoảng cach ICon
                return new ImageIcon(img);
            }
        } catch (Exception e) { }
        return null;
    }
}