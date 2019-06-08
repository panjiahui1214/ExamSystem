package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private String viewTitle = "登录窗口";
    private String labelTitle = "登录";
    private int frameWidth = 800;

    public LoginView() {
        this.init(this.viewTitle, this.labelTitle);
    }
    public LoginView(String systemName) {
        // 给系统名称的字符与字符之间增加空格
        StringBuilder str = new StringBuilder(systemName.substring(0, 1));
        for (int i = 1; i < systemName.length(); i ++) {
            str.append(" " + systemName.substring(i, i+1));
        }

        this.init(systemName.trim() + this.viewTitle, str.toString());
    }

    protected void init(String viewTitle, String labelTitle) {
        JPanel mainPanel = new JPanel();
        JLabel titleLabel = new JLabel(labelTitle);
        JLabel nameLabel = new JLabel("账 号：");
        JLabel pwdLabel = new JLabel("密 码：");
        JTextField nameField = new JTextField();
        JPasswordField pwdField = new JPasswordField();
        JButton loginButton = new JButton("登录");
        JButton exitButton = new JButton("退出");
        Font titleFont = new Font("黑体", Font.BOLD, 34);
        Font normalFont = new Font("黑体", Font.LAYOUT_LEFT_TO_RIGHT, 24);

        mainPanel.setLayout(null);
//        titleLabel.setBounds(240, 50, 350, 50);
        // 使窗口中的系统名称可以自动居中显示
        FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(titleFont);
        int titleWidth = fm.stringWidth(labelTitle);
        titleLabel.setBounds((this.frameWidth - titleWidth)/2, 50, titleWidth, 50);
        titleLabel.setFont(titleFont);
        nameLabel.setBounds(150, 150, 100, 30);
        nameLabel.setFont(normalFont);
        pwdLabel.setBounds(150, 230, 100, 30);
        pwdLabel.setFont(normalFont);
        nameField.setBounds(270, 150, 300, 30);
        pwdLabel.setFont(normalFont);
        pwdField.setBounds(270, 230, 300, 30);
        pwdLabel.setFont(normalFont);
        loginButton.setBounds(290, 320, 100, 30);
        loginButton.setFont(normalFont);
        exitButton.setBounds(430, 320, 100, 30);
        exitButton.setFont(normalFont);

        mainPanel.add(titleLabel);
        mainPanel.add(nameLabel);
        mainPanel.add(pwdLabel);
        mainPanel.add(nameField);
        mainPanel.add(pwdField);
        mainPanel.add(loginButton);
        mainPanel.add(exitButton);
        this.add(mainPanel);

        this.setTitle(viewTitle);
        this.setBounds(300, 100, this.frameWidth, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
