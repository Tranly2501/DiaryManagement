package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DraggableImage extends JLabel {
    private Point mousePressLocation;
    private ImageIcon originalIcon; // Lưu ảnh gốc để resize không bị vỡ

    // Các hằng số hướng kéo
    private static final int DIR_NONE = 0; // Di chuyển
    private static final int DIR_NW = 1;   // Tây Bắc (Trên Trái)
    private static final int DIR_NE = 2;   // Đông Bắc (Trên Phải)
    private static final int DIR_SW = 3;   // Tây Nam (Dưới Trái)
    private static final int DIR_SE = 4;   // Đông Nam (Dưới Phải)

    private int currentDirection = DIR_NONE;
    private int cornerSize = 20; // Kích thước vùng góc để bắt sự kiện

    public DraggableImage(String imagePath, int startX, int startY) {
        // 1. Load ảnh gốc
        this.originalIcon = new ImageIcon(imagePath);
        // Mặc định hiển thị kích thước 150x150 lúc đầu
        setIcon(resizeImage(150, 150));
        setBounds(startX, startY, 150, 150); // Vị trí và kích thước ban đầu

        // Đổi con trỏ chuột thành hình bàn tay để biết là di chuyển được
        setCursor(new Cursor(Cursor.MOVE_CURSOR));
        // Gán bộ lắng nghe chuột
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

    // Hàm xác định xem chuột đang ở vùng nào
    private int getCursorDirection(int x, int y) {
        int w = getWidth();
        int h = getHeight();

        // 1. Góc Trên Trái (NW)
        if (x < cornerSize && y < cornerSize) return DIR_NW;

        // 2. Góc Trên Phải (NE)
        if (x > w - cornerSize && y < cornerSize) return DIR_NE;

        // 3. Góc Dưới Trái (SW)
        if (x < cornerSize && y > h - cornerSize) return DIR_SW;

        // 4. Góc Dưới Phải (SE)
        if (x > w - cornerSize && y > h - cornerSize) return DIR_SE;

        // Còn lại là di chuyển
        return DIR_NONE;

    }

    private class MouseHandler extends MouseAdapter {

        @Override
        public void mouseMoved(MouseEvent e) {
            // Thay đổi hình dáng con trỏ chuột dựa vào vị trí
            int dir = getCursorDirection(e.getX(), e.getY());
            switch (dir) {
                case DIR_NW:
                    setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));
                    break;
                case DIR_NE:
                    setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));
                    break;
                case DIR_SW:
                    setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                    break;
                case DIR_SE:
                    setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                    break;
                default:
                    setCursor(new Cursor(Cursor.MOVE_CURSOR));
                    break;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mousePressLocation = e.getPoint();
            // Lưu lại hướng muốn kéo khi bắt đầu nhấn chuột
            currentDirection = getCursorDirection(e.getX(), e.getY());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // Tính khoảng cách chuột di chuyển
            int dx = e.getX() - mousePressLocation.x;
            int dy = e.getY() - mousePressLocation.y;

            // Lấy vị trí và kích thước hiện tại
            int x = getX();
            int y = getY();
            int w = getWidth();
            int h = getHeight();

            // Tính toán vị trí/kích thước mới dựa trên hướng kéo
            int newX = x;
            int newY = y;
            int newW = w;
            int newH = h;

            switch (currentDirection) {
                case DIR_NONE: // DI CHUYỂN
                    newX = x + dx;
                    newY = y + dy;
                    break;

                case DIR_SE: // Dưới Phải: Tăng W, Tăng H (Dễ nhất)
                    newW = w + dx;
                    newH = h + dy;
                    // Cập nhật điểm nhấn để chuột không bị trượt
                    mousePressLocation = e.getPoint();
                    break;

                case DIR_NW: // Trên Trái: Giảm X, Giảm Y, Tăng W, Tăng H
                    // Logic: Kéo sang trái (dx âm) -> W tăng, X giảm
                    newW = w - dx;
                    newH = h - dy;
                    newX = x + dx;
                    newY = y + dy;
                    break;

                case DIR_NE: // Trên Phải: Giảm Y, Tăng W, Tăng H
                    newW = w + dx;
                    newH = h - dy;
                    newY = y + dy;
                    // Cần cập nhật điểm nhấn X để mượt hơn
                    mousePressLocation = new Point(e.getX(), mousePressLocation.y);
                    break;

                case DIR_SW: // Dưới Trái: Giảm X, Tăng W, Tăng H
                    newW = w - dx;
                    newH = h + dy;
                    newX = x + dx;
                    // Cần cập nhật điểm nhấn Y để mượt hơn
                    mousePressLocation = new Point(mousePressLocation.x, e.getY());
                    break;
            }

            // --- RÀNG BUỘC KÍCH THƯỚC TỐI THIỂU (50x50) ---
            if (newW < 50) {
                newW = 50;
                if (currentDirection == DIR_NW || currentDirection == DIR_SW) {
                    newX = x + (w - 50); // Giữ nguyên cạnh phải
                }
            }
            if (newH < 50) {
                newH = 50;
                if (currentDirection == DIR_NW || currentDirection == DIR_NE) {
                    newY = y + (h - 50); // Giữ nguyên cạnh dưới
                }
            }

            // Cập nhật hình ảnh và vị trí
            setBounds(newX, newY, newW, newH);
            if (currentDirection != DIR_NONE) {
                setIcon(resizeImage(newW, newH));
            }
        }
    }
}