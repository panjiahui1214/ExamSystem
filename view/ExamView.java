package view;

import domain.Question;
import server.QuestionServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ExamView extends BaseView {
    private String title; // 窗口标题

    private int sumNum; // 总共题数
    private int nowNum = 0; // 当前题号

    private Color btnAnswerColor = Color.GREEN;

    private ArrayList<String> titles; // 存放试卷题目
    private ArrayList<String> answers; // 存放试卷正确答案
    private String[] userAnswers; // 存放用户的答案

    private JPanel jPanel = new JPanel();
    private JTextArea mainText = new JTextArea();
    private JScrollPane jScrollPane = new JScrollPane(mainText);
    private JButton btnA = new JButton("A");
    private JButton btnB = new JButton("B");
    private JButton btnC = new JButton("C");
    private JButton btnD = new JButton("D");
    private JButton btnSubmit = new JButton("交卷");

    private JPanel numBtnJP = new JPanel();
    private JLabel labelLeftTime = new JLabel("剩余答题时间");

    private JLabel leftHour = new JLabel("01");
    private JLabel leftMin = new JLabel("00");
    private JLabel leftSec = new JLabel("00");
    private JLabel labelColon1 = new JLabel("：");
    private JLabel labelColon2 = new JLabel("：");

    private Font bigFont = new Font("黑体", Font.LAYOUT_LEFT_TO_RIGHT, 20);

    private ActionListener numBtnListener; // 题号按钮的侦听事件

    public ExamView(String title, int sumNum) {
        this.title = title;
        // 获取试题
        QuestionServer qs = new QuestionServer();
        HashMap<String, ArrayList> paper = qs.getPaper(sumNum);
        this.titles = paper.get("titles");
        this.answers = paper.get("answers");
        this.userAnswers = new String[answers.size()];
        this.sumNum = titles.size();
        // 初始化
        this.init(title);
    }

    @Override
    protected void setElement() {
        jPanel.setLayout(null);
        jScrollPane.setBounds(20, 20, 550, 330);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 设置不产生水平滚动条
        mainText.setEnabled(false);
        mainText.setLineWrap(true); // 设置自动换行
        mainText.setWrapStyleWord(true); // 设置换行不断字
        mainText.setFont(new Font("黑体", Font.BOLD, 23));

        btnA.setBounds(60, 370, 80, 30);
        btnB.setBounds(190, 370, 80, 30);
        btnC.setBounds(310, 370, 80, 30);
        btnD.setBounds(430, 370, 80, 30);
        btnSubmit.setBounds(250, 420, 80, 30);
        btnSubmit.setForeground(Color.RED);

        numBtnJP.setBounds(580, 15, 190, 330);

        labelLeftTime.setBounds(605, 365, 120, 30);
        setFontAndRed(labelLeftTime);
        leftHour.setBounds(610, 400, 30, 30);
        setFontAndRed(leftHour);
        labelColon1.setBounds(640, 400, 20, 30);
        setFontAndRed(labelColon1);
        leftMin.setBounds(655, 400, 30, 30);
        setFontAndRed(leftMin);
        labelColon2.setBounds(685, 400, 20, 30);
        setFontAndRed(labelColon2);
        leftSec.setBounds(700, 400, 30, 30);
        setFontAndRed(leftSec);

        this.showQuestion();
    }

    protected void setFontAndRed(JLabel jl) {
        jl.setForeground(Color.RED);
        jl.setFont(bigFont);
    }

    protected void showQuestion() {
        String title = titles.get(nowNum);
        mainText.setText("\n " + (nowNum+1) + "." + title.replace("<br>", "\n  "));
    }

    @Override
    protected void addListener() {
        // 答案选项按钮的监听事件
        ActionListener answerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton)e.getSource();
                userAnswers[nowNum] = btn.getText();
                btn.setBackground(btnAnswerColor);
            }
        };
        btnA.addActionListener(answerListener);
        btnB.addActionListener(answerListener);
        btnC.addActionListener(answerListener);
        btnD.addActionListener(answerListener);

        // 题号按钮的侦听事件
        numBtnListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton)e.getSource();
                btn.setBackground(Color.LIGHT_GRAY);
                nowNum = Integer.parseInt(btn.getText()) - 1;
                showQuestion();
                revertBtnAnswer();
                showSetAnswer();
            }
        };
    }

    protected void revertBtnAnswer() {
        btnA.setBackground(null);
        btnB.setBackground(null);
        btnC.setBackground(null);
        btnD.setBackground(null);
    }
    protected void showSetAnswer() {
        String answer = userAnswers[nowNum];
        if (answer != null) {
            switch (answer) {
                case "A":
                    btnA.setBackground(btnAnswerColor);
                    break;
                case "B":
                    btnB.setBackground(btnAnswerColor);
                    break;
                case "C":
                    btnC.setBackground(btnAnswerColor);
                    break;
                case "D":
                    btnD.setBackground(btnAnswerColor);
                    break;
            }
        }
    }

    @Override
    protected void addElement() {
        jPanel.add(jScrollPane);
        jPanel.add(btnA);
        jPanel.add(btnB);
        jPanel.add(btnC);
        jPanel.add(btnD);
        jPanel.add(btnSubmit);

        // 循环处理题号按钮
        for (int i = 1; i <= sumNum; i ++) {
            JButton tempBtn = new JButton(i+"");
            if (i == 1) {
                tempBtn.setBackground(Color.LIGHT_GRAY);
            }
            tempBtn.addActionListener(numBtnListener);
            numBtnJP.add(tempBtn);
        }
        jPanel.add(numBtnJP);

        jPanel.add(labelLeftTime);
        jPanel.add(leftHour);
        jPanel.add(leftMin);
        jPanel.add(leftSec);
        jPanel.add(labelColon1);
        jPanel.add(labelColon2);

        this.add(jPanel);
    }
}
