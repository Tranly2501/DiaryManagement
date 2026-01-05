package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DraggableImage extends JLabel {
    private Point mousePressLocation;
    private ImageIcon originalIcon; // Lưu ảnh gốc để resize không bị vỡ

    public DraggableImage(String imagePath, int startX, int startY) {
        // 1. Load ảnh gốc
        this.originalIcon = new ImageIcon(imagePath);
        // Mặc định hiển thị kích thước 150x150 lúc đầu
        setIcon(resizeImage(150, 150));
        setBounds(startX, startY, 150, 150); // Vị trí và kích thước ban đầu

        // Đổi con trỏ chuột thành hình bàn tay để biết là di chuyển được
        setCursor(new Cursor(Cursor.MOVE_CURSOR));

        // 2. Thêm bộ lắng nghe chuột
        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);
    }

    // Hàm tiện ích để resize ảnh
    private ImageIcon resizeImage(int width, int height) {
        if (width <= 0) width = 1;
        if (height <= 0) height = 1;
        Image img = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    // Class xử lý sự kiện chuột nằm bên trong
    private class MouseHandler extends MouseAdapter {
        private boolean isResizing = false; // Cờ kiểm tra đang resize hay đang di chuyển

        @Override
        public void mousePressed(MouseEvent e) {
            mousePressLocation = e.getPoint();
            // Kiểm tra xem chuột có nhấn vào góc phải dưới (vùng 20x20 pixel) để resize không
            int w = getWidth();
            int h = getHeight();
            if (e.getX() > w - 20 && e.getY() > h - 20) {
                isResizing = true;
                setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR)); // Đổi chuột thành mũi tên chéo
            } else {
                isResizing = false;
                setCursor(new Cursor(Cursor.MOVE_CURSOR)); // Đổi chuột thành bàn tay
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int dx = e.getX() - mousePressLocation.x;
            int dy = e.getY() - mousePressLocation.y;

            if (isResizing) {
                // --- XỬ LÝ RESIZE (KÉO GÓC) ---
                int newW = getWidth() + dx;
                int newH = getHeight() + dy;

                // Giới hạn nhỏ nhất là 50x50
                if (newW < 50) newW = 50;
                if (newH < 50) newH = 50;

                setBounds(getX(), getY(), newW, newH);
                setIcon(resizeImage(newW, newH)); // Cập nhật lại ảnh theo kích thước mới

                // Cập nhật lại điểm nhấn chuột để mượt hơn
                mousePressLocation = e.getPoint();

            } else {
                // --- XỬ LÝ DI CHUYỂN (MOVE) ---
                int newX = getX() + dx;
                int newY = getY() + dy;
                setLocation(newX, newY);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Khi thả chuột ra, reset lại
            isResizing = false;
            setCursor(new Cursor(Cursor.MOVE_CURSOR));
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // Hiệu ứng hover: Nếu chuột ở góc phải dưới thì hiện mũi tên resize
            int w = getWidth();
            int h = getHeight();
            if (e.getX() > w - 20 && e.getY() > h - 20) {
                setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
            } else {
                setCursor(new Cursor(Cursor.MOVE_CURSOR));
            }
        }
    }
}