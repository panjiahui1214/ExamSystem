package view;

import javax.swing.*;

public abstract class BaseView extends JFrame {

    public BaseView() { init(); }

    public void init() {
        this.setElement();
        this.addElement();
        this.addListener();
        this.setFrame();
    }

    protected abstract void setElement();
    protected abstract void addElement();
    protected abstract void addListener();
    protected abstract void setFrame();
}
