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
            System.out.println("对不起！你的用户名或密码错误！");
        }
        else {
            System.out.println("恭喜您！登录成功！");
        }

        return result;
    }
}
