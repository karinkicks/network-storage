package auth;

import entity.User;

import java.util.Optional;

public interface AuthService {
    Optional<User> doAuth(String login, String password);
}
