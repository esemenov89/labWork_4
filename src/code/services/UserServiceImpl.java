package code.services;

import code.model.dao.UserDAOImpl;
import code.model.pojo.StorageUnit;
import code.model.pojo.User;
import org.apache.log4j.Logger;

import code.model.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
//@Service
@Component  // LAB4
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private UserDAO userDAO; // LAB4

    public UserDAO getUserDAO() {
        return userDAO;
    }

    //@Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User auth(String login, String password) {
        User user = userDAO.findUserByLoginAndPassword(login, password);
        LOGGER.debug("user: " + user);

        if (user == null || user.getLocked()==1) {
            return null;
        }
        LOGGER.debug("user not blocked");

        return user;
    }

    @Override
    public User validateUser(String login, String password, String mail){
        User user=new User(login,mail,password,0,0);
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{2,16}$+");
        Matcher m = p.matcher(login);
        if (!m.matches()){
            user.setLogin("@Error1");
        }
        if(userDAO.findUserByLogin(login)!=null){
            user.setLogin("@Error2");
        }
        p = Pattern.compile("^[a-zA-Z0-9]{8,16}$+");
        m = p.matcher(password);
        if (!m.matches()){
            user.setPassword("@Error1");
        }
        p = Pattern.compile("^[a-zA-Z0-9.-@]{8,16}$+");
        m = p.matcher(mail);
        if (!m.matches()){
            user.setMail("@Error1");
        }
        if(userDAO.findUserByMail(mail)!=null){
            user.setMail("@Error2");
        }
        return user;
    }
    @Override
    public void addUser(User user){
        userDAO.addUser(user);
    }

    @Override
    public HashSet<User> getAllUsers(){
        HashSet<User> users = userDAO.getAllUsers();
        return users;
    }
}
