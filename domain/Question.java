package domain;

public class Question {
    private String title;
    private String answer;

    public Question() {}
    public Question(String title, String answer) {
        this.title = title;
        this.answer = answer;
    }

    public String getTitle() { return this.title; }
    public String getAnswer() { return this.answer; }
}
