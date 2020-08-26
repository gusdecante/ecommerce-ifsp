package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import util.MysqlConnection;


public class UserDao {
    private static Connection con;

    public UserDao() {
        con = new MysqlConnection().getConnection(); //Create the connection
    }

    public void registerUser(User u) throws SQLException {
        String query;
        query = "INSERT INTO user (id_User, email, password, user_Type, token) VALUES (?, ?, ?, ?, ?);";

        PreparedStatement st = con.prepareStatement(query); //User the connection to set the values
        st.setInt(1, u.getIdUser());
        st.setString(2, u.getEmail());
        st.setString(3, u.getPassword());
        st.setInt(4, u.getUserType());
        st.setString(5, u.getToken());

        st.execute(); //Execute the query
        st.close(); //Close the Statment
        con.close(); //Close the connection
    }

    //This method search the details about the user
    public static User searchUserDetails(String email) throws SQLException, Exception {
        User usr = null;

        String query = "SELECT * FROM user WHERE email = '" + email + "'";

        PreparedStatement st = con.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while(rs.next()) {
             usr.setIdUser( rs.getInt("id_User"));
             usr.setEmail(rs.getString("email"));
             usr.setPassword(rs.getString("password"));
             usr.setUserType(rs.getInt("user_Type"));
             usr.setToken(rs.getString("token"));
        }

        con.close();
        return usr;
    }

    /*This method changes the informations about the User, except for the type, 
    this is only registered one time*/
    public static boolean updateUser(User user) throws SQLException {
        boolean isSuccess = false;
        try {
            String query = "UPDATE User SET id_User = ?, email = ?, password = ?, token = ? WHERE email = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, user.getIdUser());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setString(4, user.getToken());//The type of user stay the same

            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        con.close();
        return isSuccess;
    }    
    
    
}