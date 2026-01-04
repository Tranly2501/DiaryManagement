package model;

import view.WriteView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomeModel {
    private  int id;
    private String title;
    private String content;
    private String createAt;
    private String updateAt;

    WriteView view = new WriteView();
    // db connectio
    String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
            + "databaseName = Dinary;"
            + "integratedSecurity = true;"
            + "trustServerCertificate = true";
    // ===== Kết nối =====

    public HomeModel(){
        loadData();
    }

    public void loadData(){
        try {
            Connection connnect = DriverManager.getConnection(url);
            System.out.println(" Connect succeslly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Dinary> getAllDiaries() {
        List<Dinary> list = new ArrayList<>();
        String sql = "SELECT * FROM DinaryDetail ORDER BY id DESC";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

           while (resultSet.next()){
            Dinary nk = new Dinary();nk.setId(resultSet.getInt("id"));
               nk.setTitle(resultSet.getString("title"));
               nk.setContent(resultSet.getString("content"));
               nk.setCreateAt(resultSet.getString("create_at")); // Lấy ngày tạo

               list.add(nk);
           }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  list;
    }

    public boolean deleteDiary(int id){
        String sql = "DELETE FROM DinaryDetail WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
