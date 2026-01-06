
btnLuu.addActionListener(new java.awt.event.ActionListener() {
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // 1. Lấy dữ liệu từ giao diện
        String tieuDe = txtHeader.getText().trim();
        String noiDung = txtArea.getText().trim();

        // 2. Kiểm tra nếu chưa viết gì thì không cho lưu
        if (noiDung.isEmpty()) {
            MsgBox.show(WriteView.this, "Nội dung nhật ký đang trống, hãy viết gì đó nhé!", "THÔNG BÁO");
            return;
        }

        // 3. Hỏi xác nhận bằng MsgBox.confirm màu cam của bạn
        boolean confirm = MsgBox.confirm(WriteView.this, "Bạn có muốn trích xuất bài viết này ra file .txt không?");

        if (confirm) {
            FileExporter.exportWithAppend(WriteView.this, tieuDe, noiDung);
        }
    }
});