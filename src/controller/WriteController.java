package controller;

import model.DiaryModel;
import view.WriteView;

import javax.swing.*;
// import java.awt.*; // Không cần extends Component cho Controller

public class WriteController { // Bỏ "extends Component" đi nhé, không cần thiết
    private WriteView write;
    private DiaryModel diaryModel;

    public WriteController(WriteView write) {
        this.write = write;
        this.diaryModel = new DiaryModel();
        intController();
    }

    public void intController() {
        // Nút Hủy
        this.write.getBtnHuy().addActionListener(e -> {
            // Ví dụ: Đóng cửa sổ hoặc xóa trắng
            write.dispose();
            // Hoặc: write.setTxtArea(""); write.setTxtHeader("");
            System.out.println("Đã bấm hủy");
        });

        // Nút Lưu
        this.write.getBtnLuu().addActionListener(e -> {
            // 1. LẤY DỮ LIỆU TỪ VIEW HIỆN TẠI (Quan trọng!)
            String tieuDe = write.getTxtHeader(); // Lấy tiêu đề người dùng đã nhập
            String noiDung = write.getTxtArea();  // Lấy nội dung người dùng đã nhập

            // 2. Kiểm tra nếu rỗng thì báo lỗi
            if(tieuDe.isEmpty() || noiDung.isEmpty()) {
                JOptionPane.showMessageDialog(write, "Vui lòng nhập đầy đủ tiêu đề và nội dung!");
                return;
            }

            // 3. GỌI MODEL VÀ TRUYỀN DỮ LIỆU VÀO
            // (Lưu ý: Model phải sửa hàm saveDinary để nhận tham số)
            boolean ketQua = diaryModel.saveDinary(tieuDe, noiDung);

            // 4. Thông báo kết quả
            if(ketQua) {
                JOptionPane.showMessageDialog(write, "Đã lưu thành công nhật ký!");
            } else {
                JOptionPane.showMessageDialog(write, "Lưu thất bại, vui lòng kiểm tra lỗi!");
            }
        });
    }
}