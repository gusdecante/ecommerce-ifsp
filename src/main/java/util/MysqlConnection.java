package util;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

public class MysqlConnection {
    
    private Connection connection = null;
    private final String HOST = "localhost";
    private final String DB = "ecommerceDB";
    private final String LOGIN = "gerencia";
    private final String PWD = "9eq3y%NhPG83o9fLZ%xL";
    

    public Connection getConnection() {

        try {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setServerName(HOST);
            ds.setDatabaseName(DB);
            ds.setUser(LOGIN);
            ds.setPassword(PWD);
            ds.setServerTimezone("UTC");

            ds.setConnectTimeout(2000);

            connection = ds.getConnection();
            System.out.println("MYSQL CONNECTED.");
            
        } catch (SQLException sqle) {
            System.out.println("CONNECT FAIL: " + sqle.getMessage());
        }

        return connection;
    }

}