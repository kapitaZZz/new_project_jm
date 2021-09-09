package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.*;
import jm.task.core.jdbc.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String querry = "CREATE TABLE IF NOT EXISTS Users " +
                "(id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR (45) NOT NULL, " +
                "lastName VARCHAR (45) NOT NULL, " +
                "age INT NOT NULL, " +
                "PRIMARY KEY (id));";

        try (Statement statement = connection.createStatement()) {
            statement.execute(querry);

        } catch (SQLException e) {
            System.out.println("Table did not create! " + e);
        }
    }

    public void dropUsersTable() {
        String querry = "DROP TABLE IF EXISTS users";

        try (Statement statement = connection.createStatement()) {
            statement.execute(querry);
        } catch (SQLException e) {
            System.out.println("Table did not delete! " + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String querry = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, String.valueOf(age));
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Could not save new user! " + e);
        }
    }

    public void removeUserById(long id) {
        String querry = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            System.out.println("Could not remove user! " + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String querry = "SELECT * FROM users";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(querry)) {

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name")
                        , resultSet.getString("lastName")
                        , resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Unable to select any user from table. " + e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String querry = "DELETE FROM users";

        try (Statement statement = connection.createStatement()) {
            statement.execute(querry);
        } catch (SQLException e) {
            System.out.println("Could not delete any data from table. " + e);
        }
    }
}