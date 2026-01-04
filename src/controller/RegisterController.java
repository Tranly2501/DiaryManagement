//package controller;
//
//import view.Signup;
//import model.RegisterModel;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.*;
//
//public class RegisterController  extends SQLException{
//    private  int id;
//    private String content;
//    private String title;
//    private String createAt;
//    private String updateAt;
//    private String backgroudType;
//
//
//
//
//    Signup controller = new Signup();
//    RegisterModel register = new RegisterModel();
//    // db connectio
//    String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
//            + "databaseName = Dinary;"
//            + "integratedSecurity = true;"
//            + "trustServerCertificate = true";
//    Connection conn = DriverManager.getConnection(url);
//    // ===== Kết nối =====
//
//    public RegisterController(){
//    }
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
