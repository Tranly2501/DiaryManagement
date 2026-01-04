package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class HomeView extends JFrame {
    // --- KHAI BÁO CÁC COMPONENT ---
    private JTextField txtSearch;
    private JPanel pnlList;
    private JButton btnNew;

    private JButton btnXoa, btnSua, btnXuat, btnDangXuat;

    // --- BẢNG MÀU ---
    Color mauCam = new Color(232, 145, 82);
    Color mauNenTren = new Color(255, 255, 255);
    Color mauNenMenu = new Color(250, 248, 245);
    Color mauNenList = new Color(245, 246, 247);
    Color mauChuChinh = new Color(50, 50, 50);
    Color mauChuPhu = new Color(120, 120, 120);

    public HomeView() {
        setTitle("Dinary - Nhật ký của bạn");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));

        add(taoThanhTren(), BorderLayout.NORTH);
        add(taoMenuTrai(), BorderLayout.WEST);
        add(taoVungNoiDung(), BorderLayout.CENTER);

        thietLapIconCuaSo();
    }

    // --- PHẦN 1: HEADER ---
    private JPanel taoThanhTren() {
        JPanel pTren = new JPanel(new GridBagLayout());
        pTren.setBackground(mauNenTren);
        pTren.setPreferredSize(new Dimension(900, 70));
        pTren.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 25, 0, 25);

        // Logo
        JLabel lLogo = new JLabel("Dinary");
        lLogo.setFont(new Font("Serif", Font.BOLD, 28));
        lLogo.setForeground(mauCam);
        gbc.gridx = 0;
        pTren.add(lLogo, gbc);

        // Tìm kiếm
        JPanel pTimKiem = new JPanel(new BorderLayout(10, 0));
        pTimKiem.setBackground(new Color(245, 245, 245));
        pTimKiem.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 10)
        ));
        pTimKiem.setPreferredSize(new Dimension(400, 40));

        JLabel lblIconSearch = new JLabel(layIcon("/logo/Search.png", 18, 18));
        txtSearch = new JTextField(" Tìm kiếm nhật ký...");
        txtSearch.setBackground(new Color(245, 245, 245));
        txtSearch.setBorder(null);
        txtSearch.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtSearch.setForeground(Color.GRAY);

        pTimKiem.add(lblIconSearch, BorderLayout.WEST);
        pTimKiem.add(txtSearch, BorderLayout.CENTER);

        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        pTren.add(pTimKiem, gbc);

        // Nút viết mới
        btnNew = new JButton("+ Viết Mới");
        btnNew.setBackground(mauCam);
        btnNew.setForeground(Color.WHITE);
        btnNew.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnNew.setFocusPainted(false);
        btnNew.setBorder(new EmptyBorder(10, 20, 10, 20));
        btnNew.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gbc.gridx = 2; gbc.weightx = 0; gbc.fill = GridBagConstraints.NONE;
        pTren.add(btnNew, gbc);

        return pTren;
    }

    // --- PHẦN 2: SIDEBAR ---
    private JPanel taoMenuTrai() {
        JPanel pnlTrai = new JPanel();
        pnlTrai.setLayout(new BoxLayout(pnlTrai, BoxLayout.Y_AXIS));
        pnlTrai.setBackground(mauNenMenu);
        pnlTrai.setPreferredSize(new Dimension(240, 440));
        pnlTrai.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(235, 230, 225)));

        JPanel pContainer = new JPanel();
        pContainer.setLayout(new BoxLayout(pContainer, BoxLayout.Y_AXIS));
        pContainer.setBackground(mauNenMenu);
        pContainer.setBorder(new EmptyBorder(30, 20, 30, 20));

        JLabel lblAvatar = new JLabel(layIcon("/logo/People.png", 90, 90));
        lblAvatar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblUser = new JLabel("Username1");
        lblUser.setForeground(mauChuChinh);
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblUser.setAlignmentX(Component.CENTER_ALIGNMENT);

        pContainer.add(lblAvatar);
        pContainer.add(Box.createVerticalStrut(15));
        pContainer.add(lblUser);
        pContainer.add(Box.createVerticalStrut(50));

        btnXoa = taoNutAction("Xóa nhật kí", "/logo/Trash.png", "ACTION_XOA");
        btnSua = taoNutAction("Sửa nhật kí", "/logo/Wrench.png", "ACTION_SUA");
        btnXuat = taoNutAction("Xuất file", "/logo/Upload to the Cloud.png", "ACTION_XUAT");

        pContainer.add(btnXoa);
        pContainer.add(Box.createVerticalStrut(10));
        pContainer.add(btnSua);
        pContainer.add(Box.createVerticalStrut(10));
        pContainer.add(btnXuat);

        pContainer.add(Box.createVerticalGlue());

        btnDangXuat = taoNutAction("Đăng xuất", "/logo/Vector.png", "ACTION_LOGOUT");
        btnDangXuat.setForeground(new Color(200, 80, 80));
        pContainer.add(btnDangXuat);

        pnlTrai.add(pContainer);
        return pnlTrai;
    }

    private JButton taoNutAction(String text, String iconPath, String actionCommand) {
        JButton btn = new JButton(text, layIcon(iconPath, 22, 22));
        btn.setFont(new Font("SansSerif", Font.PLAIN, 15));
        btn.setForeground(mauChuChinh);
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.setIconTextGap(15);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(250, 45));
        btn.setActionCommand(actionCommand);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setForeground(mauCam); }
            public void mouseExited(MouseEvent e) {
                if ("ACTION_LOGOUT".equals(actionCommand)) btn.setForeground(new Color(200, 80, 80));
                else btn.setForeground(mauChuChinh);
            }
        });
        return btn;
    }

    // --- PHẦN 3: LIST DATA ---
    private JScrollPane taoVungNoiDung() {
        pnlList = new JPanel();
        pnlList.setLayout(new BoxLayout(pnlList, BoxLayout.Y_AXIS));
        pnlList.setBackground(mauNenList);
        pnlList.setBorder(new EmptyBorder(25, 40, 25, 40));

        JScrollPane scroll = new JScrollPane(pnlList);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    // --- CÁC HÀM GIAO TIẾP VỚI CONTROLLER ---

    public void themNhatKyVaoList(String title, String day, String month, String preview) {
        JPanel card = taoTheNhatKy(title, day, month, preview);
        pnlList.add(card);
        pnlList.add(Box.createRigidArea(new Dimension(0, 15)));
        pnlList.revalidate();
        pnlList.repaint();
    }

    public void clearList() {
        pnlList.removeAll();
        pnlList.revalidate();
        pnlList.repaint();
    }

    public JButton getBtnNew() { return btnNew; }
    public JButton getBtnXoa() { return btnXoa; }
    public JButton getBtnSua() { return btnSua; }
    public JButton getBtnXuat() { return btnXuat; }
    public JButton getBtnDangXuat() { return btnDangXuat; }

    // --- HÀM TẠO THẺ NHẬT KÝ (View "dumb" - chỉ hiển thị) ---
    private JPanel taoTheNhatKy(String title, String day, String month, String content) {
        JPanel card = new JPanel(new BorderLayout(15, 0));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(2000, 100));

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 225, 225), 1),
                new EmptyBorder(15, 20, 15, 20)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Cột Trái: Ngày tháng (Dùng dữ liệu đã xử lý từ Controller)
        JPanel pDate = new JPanel(new GridLayout(2, 1));
        pDate.setOpaque(false);
        pDate.setPreferredSize(new Dimension(50, 0));

        JLabel lblDay = new JLabel(day);
        lblDay.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblDay.setForeground(mauCam);
        lblDay.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblMonth = new JLabel("T" + month);
        lblMonth.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblMonth.setForeground(Color.GRAY);
        lblMonth.setHorizontalAlignment(SwingConstants.CENTER);

        pDate.add(lblDay);
        pDate.add(lblMonth);

        // Cột Giữa: Nội dung
        JPanel pCenter = new JPanel(new GridLayout(2, 1, 0, 5));
        pCenter.setOpaque(false);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitle.setForeground(mauChuChinh);

        JLabel lblContent = new JLabel(content);
        lblContent.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblContent.setForeground(mauChuPhu);

        pCenter.add(lblTitle);
        pCenter.add(lblContent);

        // Cột Phải: Mũi tên
        JLabel lblArrow = new JLabel("›");
        lblArrow.setFont(new Font("SansSerif", Font.PLAIN, 30));
        lblArrow.setForeground(new Color(220, 220, 220));

        card.add(pDate, BorderLayout.WEST);
        card.add(pCenter, BorderLayout.CENTER);
        card.add(lblArrow, BorderLayout.EAST);

        // Hover Effect
        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(255, 252, 248));
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(mauCam, 1),
                        new EmptyBorder(15, 20, 15, 20)
                ));
            }
            public void mouseExited(MouseEvent e) {
                card.setBackground(Color.WHITE);
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(225, 225, 225), 1),
                        new EmptyBorder(15, 20, 15, 20)
                ));
            }
        });

        return card;
    }

    private void thietLapIconCuaSo() {
        URL url = getClass().getResource("/logo/book.png");
        if (url != null) {
            Image iconApp = Toolkit.getDefaultToolkit().createImage(url);
            this.setIconImage(iconApp);
        }
    }

    private ImageIcon layIcon(String path, int w, int h) {
        try {
            URL res = getClass().getResource(path);
            if (res != null) {
                Image img = new ImageIcon(res).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }
        } catch (Exception e) { }
        return null;
    }

    public Color getMauCam() {
        return mauCam;
    }
}