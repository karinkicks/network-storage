package auth;

import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasicAuthService implements AuthService{
    private static List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }


    @Override
    public Optional<User> doAuth(String login, String password) {

        for (User user : users) {
            if (user.getNickname().equals(login) && user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean doReg(String login, String password) {

        Boolean flag=false;
        for (User user : users) {
            if (user.getNickname().equals(login) && user.getPassword().equals(password)) {
                flag=true;
            }
        }
        return flag;
        /*
        if (flag) {
            System.out.println("Such user is already registered");
        } else {
            users.add(new User(login, password));
        }*/


    }
}
