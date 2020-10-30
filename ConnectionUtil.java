package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/courseswithdata?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1111";

    Connection conn = null;

    public static Connection connDB() throws ClassNotFoundException {
        try{
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
