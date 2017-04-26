package code.services;

import code.model.pojo.User;

import java.util.HashSet;

/**
 *
 */
public interface UserService {

    User auth(String login, String password);
    HashSet<User> getAllUsers();
    User validateUser(String login, String password, String mail);
    void addUser(User user);
}
