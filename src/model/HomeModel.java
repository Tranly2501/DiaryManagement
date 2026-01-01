package model;

import view.WriteView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class HomeModel  extends SQLException{
    private  int id;
    private String title;
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
    public boolean deleteDinary(String title, String content) {
        String sql = "SELECT * FROM Dinary WHERE id =?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            int row = ps.executeUpdate();
            return row > 0; // Trả về true nếu lưu thành công

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void loadData(){
        try {
            Connection connnect = DriverManager.getConnection(url);
            System.out.println(" Connect succeslly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
