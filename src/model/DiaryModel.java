package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiaryModel {
    // Cấu hình kết nối (Kiểm tra kỹ tên database của bạn là Dinary hay Diary)
    private final String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
            + "databaseName=Dinary;"
            + "integratedSecurity=true;"
            + "trustServerCertificate=true";

    public DiaryModel() {
    }

    /**
     * Thêm mới nhật ký và trả về ID tự tăng từ DB
     */
    public int saveDiary(String title, String content, String createAt) {
        String sql = "INSERT INTO DinaryDetail(title, content, create_at) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, createAt);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // Trả về ID vừa sinh ra
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    /**
     * Cập nhật nhật ký dựa trên ID đã có
     */
    public boolean updateDiary(int id, String title, String content) {
        String sql = "UPDATE DinaryDetail SET title = ?, content = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, content);
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


}