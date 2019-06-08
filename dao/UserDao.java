package dao;

import domain.User;
import util.MyFileReader;

import java.util.HashMap;

public class UserDao {
    private static HashMap<String, User> userBox = new HashMap<>();

    public UserDao() {
        MyFileReader mfr = new MyFileReader("User") {
            @Override
            public void dealFileData(String line) {
                int index = line.indexOf("-");
                String name = line.substring(0, index);
                User user = new User(name, line.substring(index+1, line.length()));
                userBox.put(name, user);
            }
        };

        mfr.read();
    }

    public User selectUser(String name) {
        return userBox.get(name);
    }
}
