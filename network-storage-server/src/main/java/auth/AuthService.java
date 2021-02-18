package auth;

import entity.User;

import java.util.Optional;

public interface AuthService {
    User doAuth(String login, String password);
    void addUser(User user);
    boolean doReg(String login, String password);
}
