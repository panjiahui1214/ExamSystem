package dao;

import domain.Question;
import util.MyFileReader;

import java.util.ArrayList;
import java.util.HashSet;

public class QuestionDao {
    private HashSet<Question> questionBox = new HashSet<>();

    public QuestionDao() {
        MyFileReader mfr = new MyFileReader("Question") {
            @Override
            public void dealFileData(String line) {
                int index = line.indexOf("<#>");
                Question q = new Question(line.substring(0, index), line.substring(index+3, line.length()));
                questionBox.add(q);
            }
        };

        mfr.read();
    }

    public ArrayList<Question> selectQuestions() {
        return new ArrayList(this.questionBox);
    }
}
