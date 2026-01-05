package controller;

import model.DiaryModel;
import view.HomeView;
import view.WriteView;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class WriteController {
    private WriteView write;
    private HomeView homeView;
    private DiaryModel diaryModel;
    private int currentId = -1;

    public WriteController(WriteView write, HomeView homeView) {
        this.write = write;
        this.homeView = homeView; // Lúc này biến homeView mới có dữ liệu thật
        this.diaryModel = new DiaryModel();
        intController();
    }

    public void intController() {
        // Nút Hủy
        this.write.getBtnHuy().addActionListener(e -> {
            this.write.dispose();
            // Kiểm tra homeView tồn tại thì mới hiện lại
            if (homeView != null) {
                homeView.setVisible(true);
                // Cập nhật lại list data khi quay về
                new HomeController(homeView);
            }
        });

        // Nút Lưu
        this.write.getBtnLuu().addActionListener(e -> save());
        this.write.getBtnChonAnh().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chonAnh();
            }
        });
    }

    public void save() {
        String tieuDe = write.getTxtHeader();
        String noiDung = write.getTxtArea();
        String ngayViet = write.getDateToday();

        if(tieuDe.isEmpty() || noiDung.isEmpty() || tieuDe.equals("Tiêu Đề Nhật Ký...")) {
            JOptionPane.showMessageDialog(write, "Vui lòng nhập đầy đủ tiêu đề và nội dung!");
            return;
        }

        if (currentId == -1) {
            // Trường hợp 1: Thêm mới
            int newId = diaryModel.saveDiary(tieuDe, noiDung, ngayViet);
            if (newId != -1) {
                this.currentId = newId;

                write.dispose();
                // Hiện lại form Home và load lại dữ liệu
                if (homeView != null) {
                    homeView.setVisible(true);
                    new HomeController(homeView);
                }
            } else {
                JOptionPane.showMessageDialog(write, "Lỗi khi lưu bài mới!");
                return;
            }
        } else {
            // Trường hợp 2: Cập nhật
            boolean thanhCong = diaryModel.updateDiary(currentId, tieuDe, noiDung);
            if (thanhCong) {
                JOptionPane.showMessageDialog(write, "Đã cập nhật thay đổi!");
            } else {
                JOptionPane.showMessageDialog(write, "Lỗi khi cập nhật!");
                return;
            }
        }

        write.dispose();
        if (homeView != null) {
            homeView.setVisible(true);
            new HomeController(homeView);
        }
    }

    public void chonAnh(){
        //Khởi tạo FileDialog (Thay vì JFileChooser)
        // Tham số: (JFrame cha, Tiêu đề, Chế độ: LOAD để mở file / SAVE để lưu)
        FileDialog fd = new FileDialog(write, "Chọn ảnh đính kèm", FileDialog.LOAD);

        fd.setFile("*.jpg;*.jpeg;*.png;*.gif");
        //Hiển thị cửa sổ (
        fd.setVisible(true);
        //Lấy kết quả
        String fileName = fd.getFile(); // Lấy tên file (ví dụ: anh.jpg)
        String directory = fd.getDirectory(); // Lấy đường dẫn thư mục (ví dụ: C:\Users\Desktop\)

        if (fileName != null) {
            String fullPath = directory + fileName; // Ghép lại thành đường dẫn đầy đủ
            System.out.println("Bạn đã chọn: " + fullPath);
            // Gọi hàm hiển thị ảnh bên View
            write.themAnhVaoGiay(fullPath);
        }
    }
}