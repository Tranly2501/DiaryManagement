package controller;

import model.Dinary;
import model.HomeModel;
import view.HomeView;
import view.WriteView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeController {
    private HomeView homeView;
    private HomeModel homeModel;

    public HomeController(HomeView view) {
        this.homeView = view;
        this.homeModel = new HomeModel();

        loadData();

        view.getBtnNew().addActionListener(e -> {
            view.dispose();
            WriteView writeView = new WriteView();
            new WriteController(writeView, view);
            writeView.setVisible(true);
        });
    }

    private void loadData() {
        List<Dinary> list = homeModel.getAllDiaries();
        hienThiLenView(list);
    }

    private void hienThiLenView(List<Dinary> list) {
        homeView.clearList(); // Xóa list cũ

        // Dạng đầy đủ (nếu DB trả về timestamp): 2026-01-01 14:30:00
        SimpleDateFormat inputFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Dạng ngắn (nếu DB trả về date): 2026-01-01
        SimpleDateFormat inputShort = new SimpleDateFormat("yyyy-MM-dd");

        // định dạng đầu ra (Output) để hiển thị lên View
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd"); // Lấy ngày (01)
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM"); // Lấy tháng (01)

        for (Dinary nk : list) {
            String rawDate = nk.getCreateAt(); // Ví dụ: "2026-01-01"
            String dayDisplay = "01";
            String monthDisplay = "01";

            // xử lý date
            try {
                if (rawDate != null && !rawDate.isEmpty()) {
                    Date date = null;

                    // Kiểm tra độ dài chuỗi để chọn bộ format đúng
                    if (rawDate.length() > 10) {
                        // Nếu chuỗi dài (có giờ phút) -> dùng inputFull
                        date = inputFull.parse(rawDate);
                    } else {
                        // Nếu chuỗi ngắn (chỉ có ngày) -> dùng inputShort
                        date = inputShort.parse(rawDate);
                    }

                    // Format ra ngày và tháng để hiển thị
                    dayDisplay = dayFormat.format(date);
                    monthDisplay = monthFormat.format(date);

                    // format
                    String temp = dayDisplay;
                    dayDisplay = monthDisplay;
                    monthDisplay = temp;
                }
            } catch (Exception e) {
                // Nếu vẫn lỗi thì in ra console để kiểm tra, nhưng không làm crash app
                System.out.println("Lỗi parse ngày: " + rawDate + " | Chi tiết: " + e.getMessage());
            }

            // --- XỬ LÝ PREVIEW ---
            String preview = nk.getContent();
            if (preview != null && preview.length() > 50) {
                preview = preview.substring(0, 50) + "...";
            } else if (preview == null) {
                preview = "";
            }
            // Đẩy dữ liệu ra View
            homeView.themNhatKyVaoList(nk.getTitle(), dayDisplay, monthDisplay, preview);
        }
    }
}