package tools;

import java.util.ArrayList;
import java.util.List;

import entities.User;

/**
 * Created by BekiAksi1 on 2/8/2015.
 */
public class UserManager {
    public static List<User> users = new ArrayList<User>();

    public static List<User> generateUsers() {
        User user = new User();
        user.setUserID(1);
        user.setUsername("hrisi");
        users.add(user);
        return users;
    }

}
