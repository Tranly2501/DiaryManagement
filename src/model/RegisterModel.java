package model;

import controller.RegisterController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.*;

public class RegisterModel {
    // db connection
    String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
            + "databaseName = Dinary;"
            + "integratedSecurity = true;"
            + "trustServerCertificate = true";
    // ===== Kết nối =====
    public void insertInfo(String username, String password, String email, String phone) {
        String sql = "INSERT INTO UserInfo (username, password, email, phone) VALUES (N'" + username + "',N'" + password+ "',N'" + email + "',N'" + phone + "')";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void loadData() {
        try {
            Connection connect = DriverManager.getConnection(url);
            System.out.println("Connect successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isExistUsername(String username) {
        String sql = "SELECT * FROM UserInfo WHERE username= ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isExistEmail(String email) {
        String sql = "SELECT * FROM UserInfo WHERE email= ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isExistPhone(String phone) {
        String sql = "SELECT * FROM UserInfo WHERE phone = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean isValidEmail(String email) { return email.matches("^[A-Za-z0-9_.-]+@gmail.com$"); }
    public boolean isValidPhone(String phone) {
        final String regex = "^0(3|5|7|8|9)\\d{8}$";
        return phone.matches(regex);
    }
    public boolean isValidPassword(String password) { return password.length() > 6; }
    public boolean isValidRePassword(String password, String rePassword) { return rePassword.equals(password); }
}
