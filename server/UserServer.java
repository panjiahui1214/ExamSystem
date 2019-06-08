package server;

import dao.UserDao;
import domain.User;
import util.MyReflect;

public class UserServer {

    public boolean login(String name, String pwd) {
        boolean result = true;

        UserDao ud = MyReflect.getClass("dao.UserDao");
        User user = ud.selectUser(name);
        if (user == null || !user.getPwd().equals(pwd)) {
            result = false;
        }

        return result;
    }
}
