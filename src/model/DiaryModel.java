package model;

import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
public class DiaryModel {

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

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         createAt = sdf.format(now);
        String sql = "INSERT INTO DinaryDetail(title, content, create_at) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, createAt);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected> 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
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
    public boolean updateDiary(int id, String title, String content, String updateAt) {
        String sql = "UPDATE DinaryDetail SET title = ?, content = ?, update_at = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, title);
            ps.setString(2, content);

            // lấy thời gian ngày update
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String updateTime = sdf.format(now);
            ps.setString(3, updateTime); // Điền ngày sửa vào dấu ? thứ 3

            ps.setInt(4, id);     // Điền ID vào dấu ? thứ 4

            int rowsAffected = ps.executeUpdate();
            System.out.println("Đang update ID: " + id + " | Ngày: " + updateTime + " | Kết quả: " + (rowsAffected > 0));

            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }



}