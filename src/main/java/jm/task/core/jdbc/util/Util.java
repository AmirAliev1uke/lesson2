package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    public static final String Driver = "com.mysql.cj.jdbc.Driver";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/preprojectbase";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(Driver);
            connection = DriverManager.getConnection(URL,USER_NAME,PASSWORD);

            System.out.println("con ok");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("con  err");
        }
        return connection;
    }
}
