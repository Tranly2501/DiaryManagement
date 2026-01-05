package controller;

import model.DiaryModel;
import model.Dinary;
import view.HomeView;
import view.WriteView;
import javax.swing.*;
import java.awt.FileDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.MsgBox;


public class WriteController {
    private WriteView write;
    private HomeView homeView;
    private DiaryModel diaryModel;
    private int currentId = -1; // -1 : thêm mơi, >0 : sửa

    // thêm mới nhật ký
    public WriteController(WriteView write, HomeView homeView) {
        this.write = write;
        this.homeView = homeView; // Lúc này biến homeView mới có dữ liệu thật
        this.diaryModel = new DiaryModel();
        intController();
    }

    // sửa nhật ký
    public WriteController(WriteView write, HomeView homeView, Dinary oldDiary){
        this.write = write;
        this.homeView = homeView;
        this.currentId = oldDiary.getId(); // lưu id cần sửa
        this.diaryModel = new DiaryModel();

        // render dữ liệu cũ lên view
        write.setTxtHeader(oldDiary.getTitle());
        write.setTxtArea(oldDiary.getContent());
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

        // chọn ảnh
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
            MsgBox.show(write, "Bạn chưa nhập tiêu đề cho nhật ký!", "Nhắc nhở");
            return;
        }

        boolean isSuccess = false;
        //xử lý db
        if ( currentId == -1){
            // thêm mới
            int newId = diaryModel.saveDiary(tieuDe, noiDung, "");
            if (newId != -1) {
                this.currentId = newId;
                isSuccess = true;
            }
            MsgBox.show(write, "Đã lưu nhật ký thành công!", "Thông báo");
        } else {
            // cập nhập sau khi sửa nhật kí
            isSuccess = diaryModel.updateDiary( currentId, tieuDe, noiDung,ngayViet);
            if (isSuccess) {
                MsgBox.show(write, "Đã cập nhập thành công!", "Thông báo");
            }
        }
        if ( isSuccess) {
            write.dispose();
            if (homeView != null) {
                homeView.setVisible(true);
                new HomeController(homeView);
            }
        } else {
            MsgBox.show(write, "Lưu thất bại! Vui lòng thử lại.", "Lỗi hệ thống");
        }
    }

    public void chonAnh(){
        // Khởi tạo FileDialog
        // Tham số: (JFrame cha, Tiêu đề, Chế độ: LOAD để mở file / SAVE để lưu)
        FileDialog fd = new FileDialog(write, "Chọn ảnh đính kèm", FileDialog.LOAD);

        //  Bộ lọc file (Chỉ hiện file ảnh)
        fd.setFile("*.jpg;*.jpeg;*.png;*.gif");

        // Hiển thị cửa sổ
        fd.setVisible(true);

        String fileName = fd.getFile(); // Lấy tên file (ví dụ: anh.jpg)
        String directory = fd.getDirectory(); // Lấy đường dẫn thư mục (ví dụ: C:\Users\Desktop\)

        // Nếu fileName khác null tức là người dùng đã chọn file (không bấm Cancel)
        if (fileName != null) {
            String fullPath = directory + fileName; // Ghép lại thành đường dẫn đầy đủ

            System.out.println("Bạn đã chọn: " + fullPath);

            // Gọi hàm hiển thị ảnh bên View
            write.themAnhVaoGiay(fullPath);
        }
    }
}