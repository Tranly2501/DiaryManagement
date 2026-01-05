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
        username = view.getTxtUser().getText();
        password = new String(view.getTxtPass().getText());

        //  đăng nhập và chuyển trang nếu thành công
        view.getBtnLogin().addActionListener(e -> {
            if(username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "message", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (model.logIn(username, password)){
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công!", "message", JOptionPane.INFORMATION_MESSAGE);
                new HomeView().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công!",  "message", JOptionPane.WARNING_MESSAGE);
            }
        });
        // bấm đăng ký -> sang trang Signup
        view.getBtnReg().addActionListener(e -> {
            new Signup().setVisible(true);
            view.dispose();
        });
    }
}
