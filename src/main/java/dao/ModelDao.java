package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.MysqlConection;

public class ModelDao {

    private Connection con;

    //Mysql connection created
    public Dao() {
        con = new MysqlConection().getConnection();
    }

    //Insert register on the mysql table
    public void register( i) throws SQLException {
        String query;
        query = "INSERT INTO  (id_, , , , ) VALUES (?, ?, ?, ?, ?);";

        PreparedStatement st = con.prepareStatement(query); 
        st.setInt(1, i.getId());
        st.setString(2, i.get());

        st.execute(); //Execute the query
        st.close(); //Close the Statment
        con.close(); //Close the connection
    }

    //Select register on the mysql table
    public List<> search() throws SQLException, Exception {

        List<> lista = new ArrayList();
        String query = "SELECT * FROM  WHERE ;";

        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery();

        while(rs.next()) {
             usr.setIdUser( rs.getInt("id_User"));
             usr.setEmail(rs.getString("email"));
             usr.setPassword(rs.getString("password"));
             usr.setTypeOfUser(rs.getInt("type_User"));
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