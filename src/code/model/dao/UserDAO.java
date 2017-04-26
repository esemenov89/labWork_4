package code.model.dao;

import code.model.pojo.User;

import java.util.HashSet;

/**
 *
 */
public interface UserDAO {
    User findUserByLoginAndPassword(String login, String password);
    User findUserByLogin(String login);
    User findUserByMail(String mail);
    HashSet<User> getAllUsers();
    void addUser(User user);
}
