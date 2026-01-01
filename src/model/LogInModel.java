package model;

import view.LoginView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.*;

public class LogInModel  extends SQLException{
    private  int id;
    private String username;
    private String password;
    private String email;
    private String phone;


    LoginView view = new LoginView();
    // db connectio
    String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
            + "databaseName = Dinary;"
            + "integratedSecurity = true;"
            + "trustServerCertificate = true";
    // ===== Kết nối =====

//    public DiaryModel(){
//        loadData();
//    }
    public LogInModel() {
        String sql = "SELECT * FROM UserInfo WHERE username =? and password =?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);   // Lấy username từ tham số Controller truyền sang
            ps.setString(2, password); // Lấy password từ tham số Controller truyền sang

            try ( ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công", "Notification",1);
                } else {
                    JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không hợp lệ!", "Error", 0);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
