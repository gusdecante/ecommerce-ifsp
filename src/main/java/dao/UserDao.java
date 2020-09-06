package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

import util.MysqlConnection;

public class UserDao {

    private Connection con;

    //Mysql connection created
    public UserDao() {
        con = new MysqlConnection().getConnection();
    }

    //Insert register on the mysql table
    public int registerUser(User i) {
        int ok = 0;
        try {
            String query = "INSERT INTO user (id_User, email, password, user_Type, token) VALUES (?, ?, ?, ?, ?) ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getEmail());
            st.setString(3, i.getPassword());
            st.setInt(4, i.getUserType());
            st.setString(5, i.getToken());

            ok = st.executeUpdate(); //Execute the insert
            st.close(); //Close the Statment
            con.close(); //Close the connection
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ok;
    }

    //Select register on the mysql table
    public List<User> searchUser() throws SQLException, Exception {

        List<User> lista = new ArrayList<User>();
        String query = "SELECT * FROM user ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            User u = new User();

            u.setIdUser(rs.getInt("id_User"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setUserType(rs.getInt("user_Type"));
            u.setToken(rs.getString("token"));

            lista.add(u);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public int updateUser(User u) {
        int ok = 0;
        try {
            String query = "UPDATE user SET email = ?, password = ?, user_Type = ?, token = ? WHERE id_User = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<User> l = new UserDao().searchUser(u.getIdUser());
                
            for (User lc : l) {
                st.setInt(5, lc.getIdUser());
            }
            st.setString(1, u.getEmail());
            st.setString(2, u.getPassword());
            st.setInt(3, u.getUserType());
            st.setString(4, u.getToken());

            ok = st.executeUpdate(); //Execute the update
            st.close(); //Close the Statment
            con.close(); //Close the connection

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ok;
    }

    //delete register on the mysql table
    public int deleteUser(int d) {
        int ok = 0;
        try {
            String query = "DELETE FROM user WHERE id_User = ?;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            st.setInt(1, d);

            ok = st.executeUpdate(); //Execute the Delete
            st.close(); //Close the Statment
            con.close(); //Close the connection
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ok;
    }

    //Select specifical register on the mysql table
    public List<User> searchUser(int idUser) throws SQLException, Exception {

        List<User> lista = new ArrayList<User>();
        String query = "SELECT * FROM user WHERE id_User = ? ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, idUser);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            User u = new User();

            u.setIdUser(rs.getInt("id_User"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setUserType(rs.getInt("user_Type"));
            u.setToken(rs.getString("token"));

            lista.add(u);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}