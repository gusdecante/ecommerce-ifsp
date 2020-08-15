package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, u.getIdUser());
        st.setString(2, u.getEmail());
        st.setString(3, u.getPassword());
        st.setInt(4, u.getTypeOfUser());
        st.setString(5, u.getToken());

        st.execute();
        st.close();
        con.close();
    }
    
}