package view;

import javax.swing.*;
import java.awt.*;

public class ExamView extends BaseView {
    private String title;

    public ExamView(String title) {
        this.title = title;
        this.init(title);
    }

    private JPanel jPanel = new JPanel();
    private JTextField mainText = new JTextField();
    private JButton btnA = new JButton("A");
    private JButton btnB = new JButton("B");
    private JButton btnC = new JButton("C");
    private JButton btnD = new JButton("D");

    private JButton btnPrev = new JButton("上一题");
    private JButton btnSubmit = new JButton("交卷");
    private JButton btnNext = new JButton("下一题");
    private JLabel labelNowQ = new JLabel("当前题号：");
    private JLabel labelSumQ = new JLabel("总共题数：");
    private JLabel labelPassQ = new JLabel("已答题数：");
    private JLabel labelWaitQ = new JLabel("未答题数：");

    private JTextField nowQ = new JTextField();
    private JTextField sumQ = new JTextField();
    private JTextField passQ = new JTextField();
    private JTextField waitQ = new JTextField();

    private JLabel labelLeftTime = new JLabel("剩余答题时间");
    private JTextField leftHour = new JTextField();
    private JTextField leftMin = new JTextField();
    private JTextField leftSec = new JTextField();
    private JLabel labelColon1 = new JLabel(":");
    private JLabel labelColon2 = new JLabel(":");

    private Font bigFont = new Font("黑体", Font.LAYOUT_LEFT_TO_RIGHT, 20);

    @Override
    protected void setElement() {
        jPanel.setLayout(null);
        mainText.setBounds(20, 20, 550, 330);
        mainText.setEnabled(false);
        mainText.setFont(bigFont);
        btnA.setBounds(60, 370, 80, 30);
        btnB.setBounds(190, 370, 80, 30);
        btnC.setBounds(310, 370, 80, 30);
        btnD.setBounds(430, 370, 80, 30);
        btnPrev.setBounds(60, 420, 80, 30);
        btnSubmit.setBounds(250, 420, 80, 30);
        btnNext.setBounds(430, 420, 80, 30);

        labelNowQ.setBounds(600, 60, 100, 30);
        labelNowQ.setFont(bigFont);
        labelSumQ.setBounds(600, 130, 100, 30);
        labelSumQ.setFont(bigFont);
        labelPassQ.setBounds(600, 200, 100, 30);
        labelPassQ.setFont(bigFont);
        labelWaitQ.setBounds(600, 270, 100, 30);
        labelWaitQ.setFont(bigFont);

        nowQ.setBounds(715, 60, 50, 30);
        setTextField(nowQ);
        sumQ.setBounds(715, 130, 50, 30);
        setTextField(sumQ);
        passQ.setBounds(715, 200, 50, 30);
        setTextField(passQ);
        waitQ.setBounds(715, 270, 50, 30);
        setTextField(waitQ);

        labelLeftTime.setBounds(620, 350, 120, 30);
        labelLeftTime.setForeground(Color.RED);
        labelLeftTime.setFont(bigFont);
        leftHour.setBounds(620, 400, 30, 30);
        setTextField(leftHour);
        labelColon1.setBounds(650, 400, 15, 30);
        labelColon1.setFont(bigFont);
        leftMin.setBounds(665, 400, 30, 30);
        setTextField(leftMin);
        labelColon2.setBounds(695, 400, 15, 30);
        labelColon2.setFont(bigFont);
        leftSec.setBounds(710, 400, 30, 30);
        setTextField(leftSec);
    }

    protected void setTextField(JTextField jtf) {
        jtf.setHorizontalAlignment(JTextField.CENTER);
        jtf.setForeground(Color.BLUE);
        jtf.setEnabled(false);
        jtf.setFont(bigFont);
    }

    @Override
    protected void addElement() {
        jPanel.add(mainText);
        jPanel.add(btnA);
        jPanel.add(btnB);
        jPanel.add(btnC);
        jPanel.add(btnD);
        jPanel.add(btnPrev);
        jPanel.add(btnSubmit);
        jPanel.add(btnNext);

        jPanel.add(labelNowQ);
        jPanel.add(labelSumQ);
        jPanel.add(labelPassQ);
        jPanel.add(labelWaitQ);

        jPanel.add(nowQ);
        jPanel.add(sumQ);
        jPanel.add(passQ);
        jPanel.add(waitQ);

        jPanel.add(labelLeftTime);
        jPanel.add(leftHour);
        jPanel.add(leftMin);
        jPanel.add(leftSec);
        jPanel.add(labelColon1);
        jPanel.add(labelColon2);

        this.add(jPanel);
    }

    @Override
    protected void addListener() {

    }
}
