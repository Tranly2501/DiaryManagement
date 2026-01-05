package controller;

import java.awt.*;
import model.LogInModel;
import view.LoginView;
import view.Signup;
import view.HomeView;

import javax.swing.*;

public class LoginController {
    private LoginView view;
    private LogInModel model;
    private String username;
    private String password;
    public LoginController(LoginView loginView, LogInModel logInModel) {
        this.view = loginView;
        this.model = logInModel;
        inController();
    }

    private void inController() {
        //  đăng nhập và chuyển trang nếu thành công
        view.getBtnLogin().addActionListener(e -> {
            username = view.getTxtUser().getText();
            password = new String(view.getTxtPass().getPassword());

            try {
                if (username.trim().isEmpty() || password.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else if (model.logIn(username, password)) {
                    JOptionPane.showMessageDialog(null, "Đăng nhập thành công!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    new HomeView().setVisible(true);
                    view.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không hợp lệ", "Message", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        // bấm đăng ký -> sang trang Signup
        view.getBtnReg().addActionListener(e -> {
            new Signup().setVisible(true);
            view.dispose();
        });
    }
}
