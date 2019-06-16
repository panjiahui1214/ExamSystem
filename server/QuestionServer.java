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
}
