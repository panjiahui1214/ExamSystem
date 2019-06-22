package view;

import server.UserServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends BaseView {
    private String title;

    public LoginView(String title) {
        this.title = title;
        this.init(title);
    }

    private JPanel mainPanel = new JPanel();
    private JLabel titleLabel = new JLabel("在 线 考 试 系 统");
    private JLabel nameLabel = new JLabel("账 号：");
    private JLabel pwdLabel = new JLabel("密 码：");
    private JTextField nameField = new JTextField();
    private JPasswordField pwdField = new JPasswordField();
    private JButton loginButton = new JButton("登录");
    private Font titleFont = new Font("黑体", Font.BOLD, 34);
    private Font normalFont = new Font("黑体", Font.LAYOUT_LEFT_TO_RIGHT, 24);

    @Override
    protected void setElement() {
        mainPanel.setLayout(null);
        titleLabel.setBounds(240, 50, 350, 50);
        titleLabel.setFont(titleFont);
        nameLabel.setBounds(150, 150, 100, 30);
        nameLabel.setFont(normalFont);
        pwdLabel.setBounds(150, 230, 100, 30);
        pwdLabel.setFont(normalFont);
        nameField.setBounds(270, 150, 300, 30);
        pwdLabel.setFont(normalFont);
        pwdField.setBounds(270, 230, 300, 30);
        pwdLabel.setFont(normalFont);
        loginButton.setBounds(350, 320, 100, 30);
        loginButton.setFont(normalFont);
    }

    @Override
    protected void addElement() {
        mainPanel.add(titleLabel);
        mainPanel.add(nameLabel);
        mainPanel.add(pwdLabel);
        mainPanel.add(nameField);
        mainPanel.add(pwdField);
        mainPanel.add(loginButton);
        this.add(mainPanel);
    }

    @Override
    protected void addListener() {
        ActionListener loginListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String pwd = new String(pwdField.getPassword());
                UserServer us = new UserServer();
                if (!us.login(name, pwd)) {
                    String message = "对不起！您的用户名或密码错误！";
                    JOptionPane.showMessageDialog(LoginView.this, message);
                }
                else {
                    LoginView.this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    new ExamView("考试-在线考试系统", 5, 90);
                }
            }
        };
        loginButton.addActionListener(loginListener);
    }
}
