package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;
    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }

    public void createUsersTable() {
        String createUsTabl = "CREATE TABLE `preprojectbase`.`users` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL,`age` INT(3) NULL,PRIMARY KEY (`id`));";
        try (Statement statement = connection.createStatement()){
            connection.setAutoCommit(false);
            statement.execute(createUsTabl);
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Такая таблица уже существует!");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    public void dropUsersTable() {
        String dropUsTabl ="DROP TABLE users;";
        try (Statement statement = connection.createStatement()){
            connection.setAutoCommit(false);
            statement.executeUpdate(dropUsTabl);
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Такой таблицы не существует!");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUs = "INSERT INTO users (name,lastName,age) VALUES ('"+name+"','"+lastName+"','"+age+"');";
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(saveUs);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String delUsId ="DELETE FROM users where id ="+ id +";";
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(delUsId);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String getUs = "select * from users;";
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            ResultSet resultSet = statement.executeQuery(getUs);
            connection.commit();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                                     resultSet.getString("lastName"),
                                     resultSet.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        System.out.println(list);
        return list;
    }

    public void cleanUsersTable() {
        String dropUs = "TRUNCATE TABLE users;";
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(dropUs);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
