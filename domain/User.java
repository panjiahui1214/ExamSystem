package domain;

public class User {
    private String name;
    private String pwd;

    public User() {}
    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() { return this.name; }
    public String getPwd() { return this.pwd; }
}
