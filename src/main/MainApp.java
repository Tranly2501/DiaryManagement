package main;

import controller.HomeController;
import view.HomeView;
import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {

        // 2. Khởi chạy ứng dụng trên luồng sự kiện (Chuẩn Java Swing)
        SwingUtilities.invokeLater(() -> {
            // Tạo giao diện trang chủ (nhưng chưa hiện)
            HomeView view = new HomeView();

            // Kích hoạt Controller để xử lý logic và load dữ liệu
            new HomeController(view);

            // : Hiển thị lên màn hình
            view.setVisible(true);
        });
    }
}