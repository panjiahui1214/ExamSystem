package view;

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
    private int sumMinute; // 考试时间（分钟）
    private ArrayList<String> titles; // 试卷题目
    private ArrayList<String> answers; // 试卷正确答案
    private String[] userAnswers; // 考生答案
    private String userName; // 考生姓名

    private JPanel jPanel = new JPanel();
    // 左上题目展示区域
    private JTextArea mainText = new JTextArea();
    private JScrollPane jScrollPane = new JScrollPane(mainText);
    // 左下按钮操作区域
    private JButton btnA = new JButton("A");
    private JButton btnB = new JButton("B");
    private JButton btnC = new JButton("C");
    private JButton btnD = new JButton("D");
    private JButton btnSubmit = new JButton("交卷");
    // 右上题号按钮区域
    private JPanel numBtnJP = new JPanel();
    // 右下剩余答题时间区域
    private JLabel labelLeftTime = new JLabel("剩余答题时间");
    private JLabel leftTime = new JLabel();
    // 右下考生姓名区域
    private JLabel labelUserName = new JLabel();

    private Font bigFont = new Font("黑体", Font.LAYOUT_LEFT_TO_RIGHT, 20);

    private ActionListener numBtnListener; // 题号按钮的侦听事件
    private Color btnAnswerColor = Color.CYAN; // 答案选项按钮选中的颜色
    private LeftTimeThread leftTimeThread = new LeftTimeThread(); // 剩余答题时间线程
    private boolean leftTimeSwitch = true; // 剩余答题时间线程开关按钮

    private QuestionServer qs = new QuestionServer();

    public ExamView(String title, int sumNum, int sumMinute, String userName) {
        if (sumNum < 0 || sumMinute < 0) {
            alertAndExit("非常抱歉！题数和答题时间不可为负数！");
        }
        if (sumNum > 32) {
            alertAndExit("非常抱歉！目前该系统最大只支持32道题");
        }
        this.title = title;
        this.sumMinute = sumMinute;
        this.userName = userName;
        labelUserName.setText("考生：" + userName);
        // 获取试题
        HashMap<String, ArrayList> paper = this.qs.getPaper(sumNum);
        this.titles = paper.get("titles");
        this.answers = paper.get("answers");
        this.userAnswers = new String[answers.size()];
        if (sumNum > titles.size()) {
            alertAndExit("非常抱歉！请求题数大于题库数量！");
        }
        this.sumNum = sumNum;
        // 初始化
        this.init(title);
    }

    protected void alertAndExit(String message) {
        JOptionPane.showMessageDialog(ExamView.this, message);
        System.exit(0);
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

        labelLeftTime.setBounds(600, 360, 120, 30);
        setFontAndColor(labelLeftTime, bigFont, Color.RED);
        leftTime.setBounds(625, 390, 120, 30);
        setFontAndColor(leftTime, bigFont, Color.RED);

        labelUserName.setBounds(580, 420, 210, 30);
        setFontAndColor(labelUserName, bigFont, Color.BLUE);

        this.showQuestion();
    }

    protected void setFontAndColor(JLabel jl, Font font, Color color) {
        jl.setFont(font);
        jl.setForeground(color);
    }

    protected void showQuestion() {
        String title = titles.get(nowNum);
        mainText.setText("\n "+ (nowNum+1) +"."+ title.replace("<br>", "\n  "));
    }

    @Override
    protected void addListener() {
        // 答案选项按钮的监听事件
        ActionListener answerListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton btn = (JButton)e.getSource();
                if (userAnswers[nowNum] != null) {
                    revertBtnAnswer();
                }
                userAnswers[nowNum] = btn.getText();
                btn.setBackground(btnAnswerColor);
            }
        };
        btnA.addActionListener(answerListener);
        btnB.addActionListener(answerListener);
        btnC.addActionListener(answerListener);
        btnD.addActionListener(answerListener);

        // 题号按钮的监听事件
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

        // 交卷按钮的监听事件
        ActionListener submitActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(ExamView.this, "确认交卷吗？");
                // confirm: 0-是 1-否 2-取消
                if(confirm == 0) {
                    submit();
                }
            }
        };
        btnSubmit.addActionListener(submitActionListener);
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
        jPanel.add(labelUserName);
        forNumbtn();
        jPanel.add(numBtnJP);
        jPanel.add(labelLeftTime);
        jPanel.add(leftTime);
        this.add(jPanel);
        leftTimeThread.start();
    }

    // 循环处理题号按钮
    protected void forNumbtn() {
        for (int i = 1; i <= this.sumNum; i ++) {
            JButton tempBtn = new JButton(i+"");
            if (i == 1) {
                tempBtn.setBackground(Color.LIGHT_GRAY);
            }
            tempBtn.addActionListener(numBtnListener);
            numBtnJP.add(tempBtn);
        }
    }

    // 使用线程动态显示剩余答题时间
    protected class LeftTimeThread extends Thread {
        public void run() {
            int leftSeconds = sumMinute * 60;
            int hour = sumMinute/60;
            int minute = sumMinute%60;
            int seconds = 0;

            while(leftTimeSwitch) {
                leftTime.setText((hour<10 ? "0"+hour : hour) +":"+ (minute<10 ? "0"+minute : minute) +":"+ (seconds<10 ? "0"+seconds : seconds));
                if (leftSeconds == 0) {
                    submit();
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                leftSeconds--;
                if (seconds == 0) {
                    if (minute == 0) {
                        hour--;
                        minute = 59;
                    }
                    else {
                        minute--;
                    }
                    seconds = 59;
                }
                else {
                    seconds--;
                }
            }
        }
    }

    // 交卷
    protected void submit() {
        // 停止剩余答题时间线程
        leftTimeSwitch = false;
        int score = this.qs.getScore(answers, userAnswers);
        alertAndExit("考试结束，" + userName + "的成绩为" + score + "分");
    }

}
