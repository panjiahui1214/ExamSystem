package server;

import dao.QuestionDao;
import domain.Question;
import util.MyReflect;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class QuestionServer {
    private ArrayList<Question> questions;

    public QuestionServer() {
        QuestionDao qd = MyReflect.getClass("dao.QuestionDao");
        questions = qd.selectQuestions();
    }

    public ArrayList<Question> getPaper(int count) {
        HashSet<Question> qSet = new HashSet<>();
        while (qSet.size() < count) {
            Random rand = new Random();
            int index = rand.nextInt(questions.size());
            qSet.add(questions.get(index));
        }
        return new ArrayList(qSet);
    }
}
