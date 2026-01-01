package model;

import view.WriteView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DiaryModel  extends SQLException{
    private  int id;
    private String content;
    private String title;
    private String createAt;
    private String updateAt;
    private String date;
    private String backgroudType;

    WriteView view = new WriteView();
        // db connectio
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;"
                + "databaseName = Dinary;"
                + "integratedSecurity = true;"
                + "trustServerCertificate = true";
        // ===== Kết nối =====

    public DiaryModel(){
        loadData();
    }
    public void saveDinary(){
        title= view.getTxtHeader();
        content = view.getTxtArea();
        date = view.getDateToday();
        String sql = "Insert into DinaryDetail(title, content, create_at) values (?,?,?)";
        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, title);
                ps.setString(2, content);
                ps.setString(3, date);
                ps.executeUpdate();
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
