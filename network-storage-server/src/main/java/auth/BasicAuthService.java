package auth;

import entity.User;

import java.util.List;
import java.util.Optional;

public class BasicAuthService implements AuthService{
    private static List<User> users;

    @Override
    public Optional<User> doAuth(String login, String password) {
        for (User user : users) {
            if (user.getNickname().equals(login) && user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
