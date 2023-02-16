package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.beans.Statement;
import java.sql.SQLException;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        // Проверка коннекшена
        //Util util = new Util();
        //util.getConnection();
        // проверка слоя дао(общение с БД)
        //UserDao userDao = new UserDaoJDBCImpl();
        //userDao.createUsersTable();
        //userDao.saveUser("Name1", "LastName1", (byte) 20);
        //userDao.saveUser("Pirodzhochek_Diana", "Alieva_bomba", (byte) 21);
        //userDao.saveUser("Amir", "Aliev", (byte) 23);
        //userDao.dropUsersTable();
        //userDao.removeUserById(1);
        //userDao.cleanUsersTable();
        //userDao.getAllUsers();
        //обращение к дао слою через сервис
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Amir","Aliev", (byte) 23);
        userService.saveUser("Diana","Aliev", (byte) 21);
        userService.saveUser("Vlad","Larin", (byte) 27);
        userService.saveUser("Shishka","dog", (byte) 1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
