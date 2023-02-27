package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
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
