package dao;

import java.sql.Connection;
import java.sql.SQLException;
import model.User;
import util.MysqlConection;


public class UserDao {
    private Connection con;

    public UserDao() {
        this.con = new MysqlConection().getConnection();
    }

    public void register(User u) throws SQLException {
        String query;
        query = "INSERT INTO user (id_User, email, password, user_Tyoe, token) VALUES (?, ?, ?, ?, ?);";
    }
    
}