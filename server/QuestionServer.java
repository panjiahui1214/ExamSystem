package server;

import dao.QuestionDao;
import domain.Question;
import util.MyReflect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class QuestionServer {
    private ArrayList<Question> questions;

    public QuestionServer() {
        QuestionDao qd = MyReflect.getClass("dao.QuestionDao");
        questions = qd.selectQuestions();
    }

    /**
     * 获得试卷
     * @param   count   题目数量
     * @return  HashMap "titles"    题目ArrayList
     *                  "answers"   正确答案ArrayList
     */
    public HashMap<String, ArrayList> getPaper(int count) {
        HashSet<Question> qSet = new HashSet<>();
        // 严谨性判断，如果要求试卷题数超过题库题数，则取题库题数
        if (count > questions.size()) {
            count = questions.size();
        }
        while (qSet.size() < count) {
            Random rand = new Random();
            int index = rand.nextInt(questions.size());
            qSet.add(questions.get(index));
        }

        questions = new ArrayList(qSet);
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < questions.size(); i ++) {
            titles.add(questions.get(i).getTitle());
            answers.add(questions.get(i).getAnswer());
        }
        HashMap<String, ArrayList> paper = new HashMap<>();
        paper.put("titles", titles);
        paper.put("answers", answers);
        return paper;
    }

    /**
     * 获得分数（目前暂时默认一题5分）
     * @param realAnswers   试卷正确答案
     * @param userAnswers   学生选择答案
     * @return  int 学生分数
     */
    public int getScore(ArrayList<String> realAnswers, String[] userAnswers) {
        int score = 0;
        for (int i = 0; i < userAnswers.length; i ++) {
            if (realAnswers.get(i).equals(userAnswers[i])) {
                score += 5;
            }
        }
        return score;
    }
}
