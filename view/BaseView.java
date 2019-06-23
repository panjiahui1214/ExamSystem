package view;

import javax.swing.*;

public abstract class BaseView extends JFrame {

    public BaseView() {}

    public void init(String title) {
        this.setElement();
        this.addListener();
        this.addElement();
        this.setFrame(title);
    }

    protected abstract void setElement();
    protected abstract void addElement();
    protected abstract void addListener();
    protected void setFrame(String title) {
        this.setTitle(title);
        this.setBounds(300, 100, 800, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false); // 设置窗体不可更改大小
    }
}
