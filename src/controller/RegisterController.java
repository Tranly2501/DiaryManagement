package controller;

import view.Signup;
import view.HomeView;
import view.LoginView;
import model.RegisterModel;
import java.awt.*;
import javax.swing.*;

public class RegisterController {
    private Signup signup;
    private RegisterModel register;
    private String fullname;
    private String phone;
    private String email;
    private String username;
    private String password;
    private String rePassword;

    public RegisterController(Signup signup, RegisterModel register) {
        this.signup = signup;
        this.register = register;
        intController();
    }
    public void intController(){
        signup.getbtnDangKy().addActionListener(e -> {
            fullname = signup.getFullname().getText();
            phone = signup.getPhone().getText();
            email = signup.getEmail().getText();
            username = signup.getUsername().getText();
            password = new String(signup.getPassword().getText());
            rePassword = new String(signup.getRePassword().getText());
            try {
                if (username.isEmpty() || fullname.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin đăng ký!", "Message", JOptionPane.WARNING_MESSAGE);
                } else if (register.isExistUsername(username)) {
                    JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại!", "Message", JOptionPane.WARNING_MESSAGE);
                } else if (register.isExistPhone(phone)) {
                    JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!", "Message", JOptionPane.WARNING_MESSAGE);
                } else if (register.isExistEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Email đã tồn tại!", "Message", JOptionPane.WARNING_MESSAGE);
                } else if (!register.isValidEmail(email)) {
                    JOptionPane.showMessageDialog(null, "Email không hợp lệ!", "Message", JOptionPane.WARNING_MESSAGE);
                } else if (!register.isValidPassword(password)) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu không hợp lệ!", "Message", JOptionPane.WARNING_MESSAGE);
                } else if(!register.isValidPhone(phone)){
                    JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!", "Message", JOptionPane.WARNING_MESSAGE);
                } else if (!register.isValidRePassword(password, rePassword)) {
                    JOptionPane.showMessageDialog(null, "Nhập lại mật khẩu không đúng!", "Message", JOptionPane.WARNING_MESSAGE);
                } else {
                    register.insertInfo(username,  password, email, phone);
                    JOptionPane.showMessageDialog(null, "Đăng ký tài khoản thành công!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    HomeView homeView = new HomeView();
                    homeView.setVisible(true);
                }
            } catch (Exception error) {
                error.printStackTrace();
            }
        });
        signup.getbtnQuayLai().addActionListener(e1 -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
        });
        signup.getCheckPass().addActionListener(e2 -> {
            if(signup.getCheckPass().isSelected()){
                signup.getPassword().setEchoChar((char)0);
                signup.getRePassword().setEchoChar((char)0);
            } else  {
                signup.getPassword().setEchoChar('.');
                signup.getRePassword().setEchoChar('.');
            }
        });
    }
}

