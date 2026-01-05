package model;

import controller.LoginController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.*;

public class LogInModel  extends SQLException{
    // db connectio
    String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
            + "databaseName = Dinary;"
            + "integratedSecurity = true;"
            + "trustServerCertificate = true";
    // ===== Kết nối =====

    public LogInModel() { loadData(); }
    public boolean logIn(String username, String password) {
        String sql = "SELECT * FROM UserInfo WHERE username =? and password =?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);   // Lấy username từ tham số Controller truyền sang
                ps.setString(2, password);  // Lấy password từ tham số Controller truyền sang
                ResultSet rs = ps.executeQuery();
                return rs.next();
        } catch (SQLException error) {
            error.printStackTrace();
            return false;
        }
    }
    public void loadData(){
        try {
            Connection connnect = DriverManager.getConnection(url);
            System.out.println("Connect succeslly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
