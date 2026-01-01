package view;

import view.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.colorchooser.AbstractColorChooserPanel;

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


    public WriteView() {
        setTitle("Dinary - Viết Nhật Ký Mới");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(taoThanhTren(), BorderLayout.NORTH);
        add(taoVungChinh(), BorderLayout.CENTER);

        // tọa Icon cho thanh tiêu đề cửa sổ
//        thietLapIconCuaSo();
    }


    private JPanel taoThanhTren() {
        JPanel pHeader = new JPanel(new BorderLayout());
        pHeader.setBackground(mauThanhTren);
        pHeader.setPreferredSize(new Dimension(900, 60));
        pHeader.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JPanel pLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        pLeft.setOpaque(false);

        JLabel lLogo = new JLabel("Dinary");
        lLogo.setFont(new Font("Serif", Font.BOLD, 25));
        pLeft.add(lLogo);

        JPanel pRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 15));
        pRight.setOpaque(false);

        btnHuy =new JButton("Huỷ");
        btnHuy.setBackground(mauCam);
        btnHuy.setForeground(Color.WHITE);
        btnHuy.setFocusPainted(false);
        btnHuy.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        btnLuu = new JButton ("Lưu");
        btnLuu.setBackground(mauCam);
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setFocusPainted(false);
        btnLuu.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));


        JLabel btnMenu = new JLabel(layIcon("/logo/Menu.png", 25, 25));
        btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        pRight.add(btnHuy); pRight.add(btnLuu);
        pHeader.add(pLeft, BorderLayout.WEST);
        pHeader.add(pRight, BorderLayout.EAST);
        return pHeader;
    }
    // laay gia btn de controller
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

    public JPanel taoVungChinh() {
        JPanel pMain = new JPanel(new BorderLayout(0, 10));
        pMain.setBackground(Color.WHITE);
        pMain.setBorder(BorderFactory.createEmptyBorder(20, 50, 30, 50));

        JLabel lTitle = new JLabel("Viết Nhật Kí", SwingConstants.CENTER);
        lTitle.setFont(new Font("Serif", Font.BOLD, 40));
        lTitle.setForeground(mauCam);
        pMain.add(lTitle, BorderLayout.NORTH);

        JPanel pnlEditor = new JPanel(new BorderLayout(0, 10));
        pnlEditor.setOpaque(false);

        // Bảng công cụ  ngày ; biểu cảm ;màu ;FONT ;picture.
        JPanel pTools = new JPanel(new BorderLayout());
        pTools.setOpaque(false);
        pTools.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(240,240,240)));

        LocalDate date = LocalDate.now();
        DateTimeFormatter dinhDang = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateToday = date.format(dinhDang);
        // 1. Tạo JLabel để chứa chuỗi ngày
        JLabel lblDate = new JLabel("Ngày viết: " + dateToday);
        // 2. Trang trí cho ngày (Font, màu sắc) cho đồng bộ
        lblDate.setFont(new Font("Serif", Font.ITALIC, 16));
        lblDate.setForeground(mauCam);
        lblDate.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        pTools.add(lblDate, BorderLayout.WEST);

        JPanel pIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        pIcons.setOpaque(false);
        JLabel isEmoji = taoLabelIcon("/logo/Face.png", "Chọn biểu cảm");
        JLabel isColor = taoLabelIcon("/logo/Paint Palette.png", "Chọn bảng màu");
        JLabel isFont = taoLabelIcon("/logo/Font.png", "Chỉnh phông chữ");
        JLabel isImage = taoLabelIcon("/logo/Image.png", "Chèn hình ảnh");

        // EMOJI
        isEmoji.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String[] emojis = {"😊 Vui vẻ", "😂 Hài hước", "😢 Buồn", "😍 Yêu", "😡 Giận dữ"};
                String choice = (String) JOptionPane.showInputDialog(null, "Hôm nay bạn thấy thế nào?",
                        "Emoji Picker", JOptionPane.PLAIN_MESSAGE, layIcon("/logo/Face.png", 30, 30), emojis, emojis[0]);
                if(choice != null) txtArea.append(" [" + choice.split(" ")[1] + "] ");
            }
        });


        //  Chonj màu chỉ hiển thị màu RGB
        isColor.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JColorChooser colorChooser = new JColorChooser(txtArea.getForeground());
                AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();

                for (AbstractColorChooserPanel panel : panels) {
                    // chỉ lấy bảng màu RGB
                    if (!panel.getDisplayName().equals("RGB")) {
                        colorChooser.removeChooserPanel(panel);
                    }
                }
                JDialog dialog = JColorChooser.createDialog(
                        null,
                        "Bảng màu RGB",
                        true,
                        colorChooser,
                        okEvent -> {
                            txtArea.setForeground(colorChooser.getColor()); // hiển thị màu neèn ở phâng nội dung viết

                        },
                        null
                );

                dialog.setVisible(true);
            }
        });

        // chọn FONT

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

        // chọn ảnh trong máy
        isImage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "gif"));
                if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(null, "Đã đính kèm ảnh: " + fc.getSelectedFile().getName());
                }
            }
        });

        pIcons.add(isEmoji); pIcons.add(isColor); pIcons.add(isFont); pIcons.add(isImage);
        pTools.add(pIcons, BorderLayout.EAST);

        // phần nội dung cần viết
        JPanel pPaper = new JPanel(new BorderLayout());
        pPaper.setBackground(mauNenNoidung);
        pPaper.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 220, 200), 1), // Viền ngoài
                BorderFactory.createEmptyBorder(20, 40, 20, 40) // Padding bên trong giấy (để chữ không sát mép)
        ));
        txtHeader = new JTextField("Tiêu Đề");
        txtHeader.setFont(new Font("Arial", Font.BOLD, 20));
        txtHeader.setBorder(null);
        txtHeader.setOpaque(false);

        Color mauDongKe = new Color(208, 158, 115, 80);
        // Khởi tạo txtArea bằng class RuledTextArea vừa thêm ở Bước 1
        txtArea = new RuledTextArea("Nội dung nhật ký hôm nay...", mauDongKe);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        // Set khoảng cách đệm (Padding)
        txtArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        JScrollPane scrollPane = new JScrollPane(txtArea);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0)); // Thanh cuộn nhỏ lại cho tinh tế

        pPaper.add(txtHeader, BorderLayout.NORTH);
        pPaper.add(scrollPane, BorderLayout.CENTER);

        pnlEditor.add(pTools, BorderLayout.NORTH);
        pnlEditor.add(pPaper, BorderLayout.CENTER);
        pMain.add(pnlEditor, BorderLayout.CENTER);

        return pMain;
    }

    public String getTxtArea() {
        return txtArea.getText();
    }
    public void setTxtArea (String content){
        txtArea.setText(content);
    }

    public String getTxtHeader() {
        return txtHeader.getText();
    }
    public  void setTxtHeader(String title) {
        txtHeader.setText(title);
    }

//    // Hàm thiết lập Icon tiêu đề cửa sổ
//    private void thietLapIconCuaSo() {
//        URL url = getClass().getResource("/logo/book.png");
//        if (url != null) {
//            Image iconApp = Toolkit.getDefaultToolkit().createImage(url);
//            this.setIconImage(iconApp);
//        }
//    }
//    private void hienThiMenuLogout(JLabel parent) {
//        JPopupMenu menu = new JPopupMenu();
//        JMenuItem itemLogout = new JMenuItem("Đăng xuất");
//        itemLogout.addActionListener(e -> {
//            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                new view.LoginView();
//                this.dispose();
//            }
//        });
//        menu.add(itemLogout);
//        menu.show(parent, 0, parent.getHeight());
//    }

    private JLabel taoLabelIcon(String path, String tooltip) {
        JLabel label = new JLabel(layIcon(path, 25, 25));
        label.setToolTipText(tooltip);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }




    private void quayVeTrangChinh() {
        new view.MainView().setVisible(true);
        this.dispose();
    }

    private ImageIcon layIcon(String path, int w, int h) {
        try {
            URL url = getClass().getResource(path);
            if (url != null) return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
        } catch (Exception e) {}
        return null;
    }

    private static class RuledTextArea extends JTextArea {
        private Color lineColor;

        public RuledTextArea(String text, Color lineColor) {
            super(text);
            this.lineColor = lineColor;
            setOpaque(false); // Để hiện nền của panel cha
            setFont(new Font("Serif", Font.PLAIN, 18)); // Font chữ to rõ ràng hơn
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(lineColor);

            int rowHeight = getRowHeight();
            if (rowHeight == 0) rowHeight = 20;

            // Vẽ dòng kẻ ngang
            int height = getHeight();

            // Vẽ dòng kẻ dựa trên chiều cao dòng
            for (int y = rowHeight; y < height; y += rowHeight) {
                g2.drawLine(5, y + 4, getWidth() - 5, y + 4);
            }
        }
    }
}