package controller;

import model.DiaryModel;
import model.Dinary;
import model.HomeModel;
import view.HomeView;
import view.MsgBox;
import view.WriteView;

import javax.swing.*;
import java.text.*;
import java.util.Date;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HomeController {
    private HomeView homeView;
    private HomeModel homeModel;

    public HomeController(HomeView view) {
        this.homeView = view;
        this.homeModel = new HomeModel();

        loadData();

        view.getBtnNew().addActionListener(e -> {
            view.setVisible(false);
            WriteView writeView = new WriteView();
            new WriteController(writeView, view);
            writeView.setVisible(true);
        });
        view.getBtnXoa().addActionListener(e -> xoa());
        view.getBtnSua().addActionListener(e -> sua());
        view.getTxtSearch().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                xuLyTimKiem();
            }
        });
    }

    private void loadData() {
        List<Dinary> list = homeModel.getAllDiaries();
        hienThiLenView(list);
    }

    private void hienThiLenView(List<Dinary> list) {
        homeView.clearList(); // Xóa list cũ

        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd"); // Bộ đọc
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");      // Bộ xuất ngày
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");    // Bộ xuất tháng

        for (Dinary nk : list) {
            // Ưu tiên lấy ngày sửa, nếu không có thì lấy ngày tạo
            String rawDate = nk.getUpdateAt();
            if (rawDate == null || rawDate.isEmpty()) {
                rawDate = nk.getCreateAt();
            }
            String dayDisplay = "01";
            String monthDisplay = "01";
            //  Xử lý date a
            try {
                if (rawDate != null && !rawDate.isEmpty()) {
                    // QUAN TRỌNG: Nếu chuỗi dài quá 10 ký tự (có giờ phút), cắt bớt đi
                    // Ví dụ: "2026-01-05 14:00:00" -> Cắt thành "2026-01-05"
                    if (rawDate.length() > 10) {
                        rawDate = rawDate.substring(0, 10);
                    }

                    Date date = parser.parse(rawDate);
                    // Format ra ngày và tháng
                    dayDisplay = dayFormat.format(date);
                    monthDisplay = monthFormat.format(date);

                }
            } catch (Exception e) {
                System.out.println("Lỗi parse ngày ID " + nk.getId() + ": " + rawDate);
            }

            // 3. Xử lý Preview nội dung
            String preview = nk.getContent();
            if (preview != null && preview.length() > 50) {
                preview = preview.substring(0, 50) + "...";
            } else if (preview == null) {
                preview = "";
            }
            // Đẩy dữ liệu ra View
            homeView.themNhatKyVaoList(nk.getId(), nk.getTitle(), dayDisplay, monthDisplay, preview);
        }
    }

    private void xoa(){
        int idCanXoa = homeView.getSelectedId();
        if (idCanXoa == -1){
            MsgBox.show(homeView, "Vui lòng chọn nhật ký cần xóa!", "Nhắc nhở");
            return;
        }

        boolean confirm = MsgBox.confirm(homeView, "Bạn có chắc chắn muốn xóa nhật ký này không?");
        if (confirm) {
            boolean isDeleted = homeModel.deleteDiary(idCanXoa);

            if (isDeleted) {
                MsgBox.show(homeView, "Đã xóa thành công!", "Thông báo");
                loadData(); // Load lại danh sách
            } else {
                MsgBox.show(homeView, "Xóa thất bại. Vui lòng thử lại.", "Lỗi");
            }
        }
    }

    private void sua(){
        int id = homeView.getSelectedId();

        if ( id == -1) {
            JOptionPane.showMessageDialog(homeView, "Vui lòng chọn bài viết cần sửa!");
            return;
        }

        // lấy dữ liệu chi tiết từ model
        Dinary oldDiary = homeModel.getDiaryById(id);
        if (oldDiary != null) {
            homeView.dispose();
            WriteView writeView = new WriteView();
            new WriteController(writeView, homeView, oldDiary);

            writeView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(homeView, "Không tìm thấy dữ liệu!");
        }
    }

    private  void xuLyTimKiem() {
        String keyWord = homeView.getTxtSearch().getText().trim();

        // gọi model để tìm
        List<Dinary> list;
        if ( keyWord.isEmpty()){
            list = homeModel.getAllDiaries();
        } else {
            list = homeModel.searchByTitle(keyWord);
        }
        hienThiLenView(list);
    }
}