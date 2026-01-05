package controller;

import model.DiaryModel;
import model.Dinary;
import view.HomeView;
import view.WriteView;
import javax.swing.*;
import java.awt.FileDialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import view.MsgBox;


public class WriteController {
    private WriteView write;
    private HomeView homeView;
    private DiaryModel diaryModel;
    private int currentId = -1; // -1 : thêm mơi, >0 : sửa

    private  Thread autoSave;
    private volatile  boolean isRunning = true; // biến kiểu xóa ( volatile để đồng bộ giữa các luồng)

    // thêm mới nhật ký
    public WriteController(WriteView write, HomeView homeView) {
        this.write = write;
        this.homeView = homeView; // Lúc này biến homeView mới có dữ liệu thật
        this.diaryModel = new DiaryModel();
        intController();

        startAutoSave();
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

        startAutoSave();
    }

    public void intController() {
        // Nút Hủy
        this.write.getBtnHuy().addActionListener(e -> closeForm());

        // Nút Lưu
        this.write.getBtnLuu().addActionListener(e -> save(true));

        // Xử lý khi bấm nút X trên cửa sổ -> Phải dừng Thread
        this.write.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeForm();
            }
        });

        // chọn ảnh
        this.write.getBtnChonAnh().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                chonAnh();
            }
        });
    }

    private void startAutoSave(){
        autoSave = new Thread( () -> {
            while (isRunning) {
                try {

                    Thread.sleep(10000);

                    // kiểm tra biến
                    if (!isRunning) break;
                    // Gọi hàm lưu . Dùng SwingUtilities.invokeLater để cập nhật UI an toàn từ luồng khác
                    SwingUtilities.invokeLater( () ->{
                        // Chỉ lưu nếu tiêu đề và nội dung không rỗng
                        if (!write.getTxtHeader().isEmpty() && !write.getTxtHeader().equals("Tiêu Đề Nhật Ký...")) {
                            save(false);
                            System.out.println("Auto Save: Đã tự động lưu lúc " + new java.util.Date());
                        }
                    });
                }catch (InterruptedException e){
                    // luồng ngăt, thoát vòng lăp
                    break;
                }
            }

        });
        // đặt tên luông
        autoSave.setName(" AutoSave thread");
        // cho chạy ngầm
        autoSave.start();
    }



    private void closeForm() {
        isRunning = false; // Ngắt vòng lặp Auto Save
        if (autoSave!= null) {
            autoSave.interrupt(); // Đánh thức luồng nếu nó đang ngủ
        }
        write.dispose();
        if (homeView != null) {
            homeView.setVisible(true);
            new HomeController(homeView);
        }
    }

    public void save(boolean showMessage) {
        String tieuDe = write.getTxtHeader();
        String noiDung = write.getTxtArea();
        String ngayViet = write.getDateToday();


        if (tieuDe.isEmpty() || noiDung.isEmpty() || tieuDe.equals("Tiêu Đề Nhật Ký...")) {
            MsgBox.show(write, "Bạn chưa nhập tiêu đề cho nhật ký!", "Nhắc nhở");
            return;
        }

        boolean isSuccess = false;
        //xử lý db
        if (currentId == -1) {
            // thêm mới
            int newId = diaryModel.saveDiary(tieuDe, noiDung, "");
            if (newId != -1) {
                this.currentId = newId;
                isSuccess = true;
            }
        } else {
            // cập nhập sau khi sửa nhật kí
            isSuccess = diaryModel.updateDiary(currentId, tieuDe, noiDung, ngayViet);
        }

        // chỉ hiện thông khi người dùng bấm nút lưu
        if (showMessage) {
            if (isSuccess) {
                MsgBox.show(write, "Đã lưu nhật ký thành công!", "Thông báo");
                closeForm();
            } else {
                MsgBox.show(write, "Lưu thất bại! Vui lòng thử lại.", "Lỗi hệ thống");
            }
        } else {
            if (isSuccess) write.setTitle("Viết Nhật Ký (Đã lưu tự động...)");
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