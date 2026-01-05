package view;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WriteView extends JFrame {
    // Bảng màu writeView
    Color mauCam = new Color(232, 145, 82);
    Color mauNenNoidung = new Color(255, 244, 225);
    Color mauThanhTren = new Color(208, 158, 115);

    JTextArea txtArea;
    JTextField txtHeader;

    JButton btnLuu;
    JButton btnHuy;

    String dateToday;

    private JLabel btnChonAnh;
    private JLabel lblAnhDinhKem; // Nơi hiển thị ảnh
    private String duongDanAnh;
    private JPanel pnlEditor;
    private JLayeredPane pPaper;

    public WriteView() {
        setTitle("Dinary - Viết Nhật Ký Mới");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(taoThanhTren(), BorderLayout.NORTH);
        add(taoVungChinh(), BorderLayout.CENTER);
    }

    private JPanel taoThanhTren() {
        JPanel pHeader = new JPanel(new BorderLayout());
        thietLapIconCuaSo();
        pHeader.setBackground(mauThanhTren);
        pHeader.setPreferredSize(new Dimension(900, 60));
        pHeader.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        JPanel pLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        pLeft.setOpaque(false);

        JLabel lLogo = new JLabel("Dinary");
        lLogo.setFont(new Font("Serif", Font.BOLD, 25));
        lLogo.setForeground(Color.WHITE);
        pLeft.add(lLogo);

        JPanel pRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));
        pRight.setOpaque(false);

        btnHuy = new JButton("Huỷ");
        btnHuy.setBackground(mauCam);
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setFocusPainted(false);
        btnHuy.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        btnLuu = new JButton("Lưu");
        btnLuu.setBackground(mauCam);
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setFocusPainted(false);
        btnLuu.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Nút Menu (nếu cần xử lý sau này)
        JLabel btnMenu = new JLabel(layIcon("/logo/Menu.png", 25, 25));
        btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pRight.add(btnHuy);
        pRight.add(btnLuu);
        pHeader.add(pLeft, BorderLayout.WEST);
        pHeader.add(pRight, BorderLayout.EAST);
        return pHeader;
    }

    // Getter cho Controller
    public JButton getBtnHuy() {
        return btnHuy;
    }

    public JButton getBtnLuu() {
        return btnLuu;
    }

    public Color getMauCam() {
        return mauCam;
    }

    public String getDateToday() {
        return dateToday;
    }

    // Thiết lập Logo cửa sổ
    private void thietLapIconCuaSo() {
        URL url = getClass().getResource("/logo/book.png");
        if (url != null) {
            Image iconApp = Toolkit.getDefaultToolkit().createImage(url);
            this.setIconImage(iconApp);
        }
    }

    public JPanel taoVungChinh() {
        JPanel pMain = new JPanel(new BorderLayout(0, 10));
        pMain.setBackground(Color.WHITE);
        pMain.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));

        // Khởi tạo Tiêu đề (Header)
        txtHeader = new JTextField("Tiêu Đề Nhật Ký...");
        txtHeader.setFont(new Font("Serif", Font.BOLD, 20));
        txtHeader.setForeground(mauCam);
        txtHeader.setHorizontalAlignment(JTextField.LEFT);
        txtHeader.setBorder(null);
        txtHeader.setBackground(mauNenNoidung); // Đặt background trùng màu giấy

        pnlEditor = new JPanel(new BorderLayout(0, 10));
        pnlEditor.setOpaque(false);

        // --- Bảng công cụ (Ngày, Emoji, Màu, Font, Ảnh) ---
        JPanel pTools = new JPanel(new BorderLayout());
        pTools.setOpaque(false);
        pTools.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(240, 240, 240)));

        LocalDate date = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateToday = date.format(dinhDang);

        JLabel lblDate = new JLabel("Ngày viết: " + dateToday);
        lblDate.setFont(new Font("Serif", Font.ITALIC, 16));
        lblDate.setForeground(mauCam);
        lblDate.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        pTools.add(lblDate, BorderLayout.WEST);

        JPanel pIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        pIcons.setOpaque(false);
        JLabel isEmoji = taoLabelIcon("/logo/Face.png", "Chọn biểu cảm");
        JLabel isColor = taoLabelIcon("/logo/Paint Palette.png", "Chọn bảng màu");
        JLabel isFont = taoLabelIcon("/logo/Font.png", "Chỉnh phông chữ");
        btnChonAnh = taoLabelIcon("/logo/Image.png", "Chèn hình ảnh");

        // Xử lý sự kiện EMOJI
        isEmoji.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String[] emojis = {"😊 Vui vẻ", "😂 Hài hước", "😢 Buồn", "😍 Yêu", "😡 Giận dữ"};
                String choice = (String) JOptionPane.showInputDialog(null, "Hôm nay bạn thấy thế nào?",
                        "Emoji Picker", JOptionPane.PLAIN_MESSAGE, layIcon("/logo/Face.png", 30, 30), emojis, emojis[0]);
                if (choice != null) txtArea.append(" [" + choice.split(" ")[1] + "] ");
            }
        });

        // Xử lý sự kiện COLOR (Chỉ hiện RGB)
        isColor.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JColorChooser colorChooser = new JColorChooser(txtArea.getForeground());
                AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
                for (AbstractColorChooserPanel panel : panels) {
                    if (!panel.getDisplayName().equals("RGB")) {
                        colorChooser.removeChooserPanel(panel);
                    }
                }
                JDialog dialog = JColorChooser.createDialog(null, "Bảng màu RGB", true, colorChooser,
                        okEvent -> txtArea.setForeground(colorChooser.getColor()), null);
                dialog.setVisible(true);
            }
        });

        // Xử lý sự kiện FONT
        isFont.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
                JComboBox<String> combo = new JComboBox<>(fonts);
                combo.setSelectedItem(txtArea.getFont().getFamily());
                if (JOptionPane.showConfirmDialog(null, combo, "Chọn Font chữ", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    txtArea.setFont(new Font((String) combo.getSelectedItem(), Font.PLAIN, 16));
                }
            }
        });


        pIcons.add(isEmoji);
        pIcons.add(isColor);
        pIcons.add(isFont);
        pIcons.add(btnChonAnh);
        pTools.add(pIcons, BorderLayout.EAST);

        // --- Phần nội dung viết (Giấy) ---
         pPaper = new JLayeredPane();
        pPaper.setOpaque(true);
        pPaper.setBackground(mauNenNoidung);
        pPaper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 220, 200), 1),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        Color mauDongKe = new Color(208, 158, 115, 80);
        // Khởi tạo txtArea với nội dung trống
        txtArea = new RuledTextArea("", mauDongKe);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setBorder(BorderFactory.createEmptyBorder(5, 4, 5, 4));
        txtArea.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(txtArea);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));

        // Thêm Header và Nội dung vào trang giấy
        JPanel pContentWrapper = new JPanel(new BorderLayout());
        pContentWrapper.setOpaque(false);
        pContentWrapper.add(txtHeader, BorderLayout.NORTH);
        pContentWrapper.add(scrollPane, BorderLayout.CENTER);

        /*
        * Đưa nội dung giấy vào Lớp thấp nhất (DEFAULT_LAYER)
        // Vì JLayeredPane dùng null layout, ta phải setBounds thủ công
        * */
        pPaper.add(pContentWrapper, JLayeredPane.DEFAULT_LAYER);
        // Khi cửa sổ thay đổi kích thước -> Giấy viết cũng phải co giãn theo
        pPaper.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent e) {
                pContentWrapper.setBounds(0, 0, pPaper.getWidth(), pPaper.getHeight());
                pPaper.revalidate();
            }
        });

        pnlEditor.add(pTools, BorderLayout.NORTH);
        pnlEditor.add(pPaper, BorderLayout.CENTER);
        pMain.add(pnlEditor, BorderLayout.CENTER);

        return pMain;
    }

    public JLabel getBtnChonAnh() {
        return btnChonAnh;
    }

    public void themAnhVaoGiay(String path) {
        // Tạo ảnh thông minh có thể kéo thả
        // Vị trí mặc định: x=50, y=50
        DraggableImage imgLabel = new DraggableImage(path, 50, 50);

        // Thêm vào lớp trên cao (PALETTE_LAYER) để đè lên chữ
        pPaper.add(imgLabel, JLayeredPane.PALETTE_LAYER);

        // Vẽ lại giao diện
        pPaper.repaint();
    }

    // lấy đường dẫn ảnh hiện tại để luuw vào DB'
    public  String getDuongDanAnh(){
        return duongDanAnh;
    }
    public String getTxtArea() {
        return txtArea.getText();
    }

    public void setTxtArea(String content) {
        txtArea.setText(content);
    }

    public String getTxtHeader() {
        return txtHeader.getText();
    }

    public void setTxtHeader(String title) {
        txtHeader.setText(title);
    }



    private JLabel taoLabelIcon(String path, String tooltip) {
        JLabel label = new JLabel(layIcon(path, 25, 25));
        label.setToolTipText(tooltip);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }

    private void quayVeTrangChinh() {
        new view.HomeView().setVisible(true); // Đảm bảo bạn có class MainView
        this.dispose();
    }

    private ImageIcon layIcon(String path, int w, int h) {
        try {
            URL url = getClass().getResource(path);
            if (url != null)
                return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            System.err.println("Không tìm thấy ảnh: " + path);
        }
        return null;
    }

    // Class con vẽ dòng kẻ
    private static class RuledTextArea extends JTextArea {
        private Color lineColor;

        public RuledTextArea(String text, Color lineColor) {
            super(text);
            this.lineColor = lineColor;
            setOpaque(false);
            setFont(new Font("Serif", Font.PLAIN, 18));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(lineColor);

            int rowHeight = getRowHeight();
            if (rowHeight == 0) rowHeight = 20;

            int height = getHeight();
            int width = getWidth();

            // Vẽ dòng kẻ
            for (int y = rowHeight; y < height; y += rowHeight) {
                g2.drawLine(5, y + 4, width - 5, y + 4);
            }
        }
    }
}