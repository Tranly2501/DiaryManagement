package model;

import view.Signup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javax.swing.*;

public class RegisterModel extends SQLException {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;

    Signup view = new Signup();
    // db connectio
    String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
            + "databaseName = Dinary;"
            + "integratedSecurity = true;"
            + "trustServerCertificate = true";
    // ===== Kết nối =====
    public RegisterModel() {
        String sql = "INSERT INTO UserInfo (username, password, email, phone) VALUES (N'" + username + "',N'" + password + "',N'" + email + "',N'" + phone + "')";
        try(Connection conn = DriverManager.getConnection(url);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
//    private boolean isExistUsername(String username) {
//        String sql = "SELECT * FROM UserInfo WHERE usename= ?";
//
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, username);
//            ResultSet rs = ps.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    private boolean isExistEmail(String email) {
//        String sql = "SELECT * FROM UserInfo WHERE email= ?";
//
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, email);
//            ResultSet rs = ps.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    private boolean isExistPhone(String phone) {
//        String sql = "SELECT * FROM UserInfo WHERE phone = ?";
//
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, phone);
//            ResultSet rs = ps.executeQuery();
//            return rs.next();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    private boolean isValidEmail(String email) { return email.matches("^[A-Za-z0-9_.-]+@[A-Za-z0-9.-]+$"); }
//    private boolean isValidPhone(String phone) { return phone.matches("^(03|05|08|07|09)\\d(8)$"); }
//    private boolean isValidPassword(String password) { return password.length() < 6; }
//    private boolean isValidRePassword(String password, String rePassword) { return rePassword.equals(password); }
//}
