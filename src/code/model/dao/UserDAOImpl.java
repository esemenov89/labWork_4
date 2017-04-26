package code.model.dao;


import org.apache.log4j.Logger;

import code.model.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import code.model.ConnectionPool;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 */
public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        User user = null;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "SELECT * FROM USERS WHERE LOWER(NICK) = '"+login.toLowerCase()+"' AND PASSWORD = '"+password+"'")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }
            LOGGER.debug("user " + user);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        catch (Exception e){
            LOGGER.error(e);
        }
        return user;
    }
    @Override
    public User findUserByLogin(String login) {
        User user = null;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "SELECT * FROM USERS WHERE LOWER(NICK) = '"+login.toLowerCase()+"'")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }
            LOGGER.debug("user " + user);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        catch (Exception e){
            LOGGER.error(e);
        }
        return user;
    }
    @Override
    public User findUserByMail(String mail) {
        User user = null;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "SELECT * FROM USERS WHERE LOWER(MAIL) = '"+mail.toLowerCase()+"'")) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }
            LOGGER.debug("user " + user);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        catch (Exception e){
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "INSERT INTO USERS(NICK,MAIL,PASSWORD,ACCOUNT_TYPE,LOCKED) VALUES(?,?,?,?,?)")) {
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getMail());
            statement.setString(3,user.getPassword());
            statement.setInt(4,user.getAccountType());
            statement.setInt(5,user.getLocked());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        catch (Exception e){
            LOGGER.error(e);
        }
    }

    public HashSet<User> getAllUsers(){
        User user=null;
        HashSet<User> users= new HashSet<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "SELECT * FROM USERS")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = createEntity(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        catch (Exception e){
            LOGGER.error(e);
        }
        return users;
    }

    private User createEntity(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getString("NICK"),
                resultSet.getString("MAIL"),
                resultSet.getString("PASSWORD"),
                resultSet.getInt("ACCOUNT_TYPE"),
                resultSet.getInt("LOCKED"));
    }
}
