package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao t1 = new UserDaoJDBCImpl();
    public UserServiceImpl() {

    }

    public void createUsersTable() {
        t1.createUsersTable();
    }

    public void dropUsersTable() {
        t1.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        t1.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        t1.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return t1.getAllUsers();
    }

    public void cleanUsersTable() {
        t1.cleanUsersTable();
    }
}
