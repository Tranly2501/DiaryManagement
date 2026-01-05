package controller;

import model.DiaryModel;
import view.HomeView;
import view.WriteView;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn ảnh đính kèm");

        // chỉ cho phép chọn ảnh
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Hình ảnh (JPG, PNG)", "jpg", "png", "jpeg");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showOpenDialog(write);

        if ( userSelection == JFileChooser.APPROVE_OPTION){
            File fileToSave = fileChooser.getSelectedFile();
            String path = fileToSave.getAbsolutePath();
//            write.hienThiAnh(path);
            write.themAnhVaoGiay(path);
            System.out.println("Đã chọn file: " + path);
        }
    }
}