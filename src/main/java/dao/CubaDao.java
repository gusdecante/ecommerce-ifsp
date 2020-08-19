package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cuba;

import util.MysqlConection;

public class CubaDao {

    private Connection con;

    //Mysql connection created
    public CubaDao() {
        con = new MysqlConection().getConnection();
    }

    //Insert register on the mysql table
    public boolean registerCuba(Cuba i) {
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO cuba (id_Cuba, name_Cuba, size_Cuba_id_Size_Cuba) VALUES (?, ?, ?);";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getNameCuba());
            st.setInt(3, i.getSizeCubaIdSizeCuba());

            st.executeUpdate(); //Execute the insert
            st.close(); //Close the Statment
            con.close(); //Close the connection
            isSuccess = true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        }
        
        return isSuccess;
    }

    //Select register on the mysql table
    public List<Cuba> searchCuba() throws SQLException, Exception {

        List<Cuba> lista = new ArrayList();
        String query = "SELECT cuba.id_Cuba, cuba.name_Cuba, size_Cuba.size FROM cuba, size_Cuba WHERE cuba.size_Cuba_id_Size_Cuba = size_Cuba.id_Size_Cuba ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Cuba cb = new Cuba();

            cb.setIdCuba(rs.getInt("id_Cuba"));
            cb.setNameCuba(rs.getString("name_Cuba"));
            cb.setSize(rs.getString("size"));

            lista.add(cb);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public boolean updateCuba(Cuba u) {
        boolean isSuccess = false;
        try {
            String query = "UPDATE cuba SET id_Cuba = ?, name_Cuba = ?, size_Cuba_id_Size_Cuba = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<Cuba> l = new CubaDao().searchCuba(u.getIdCuba());
                
            for (Cuba lc : l) {
                st.setInt(1, lc.getIdCuba());
            }

            st.setString(2, u.getNameCuba());
            st.setInt(3, u.getSizeCubaIdSizeCuba());

            st.executeUpdate(); //Execute the update
            st.close(); //Close the Statment
            con.close(); //Close the connection

            isSuccess = true;

        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }

    //delete register on the mysql table
    public boolean deleteCuba(int d) {
        boolean isSuccess = false;
        try {
            String query = "DELETE FROM cuba WHERE id_Cuba = ?;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            st.setInt(1, d);

            st.executeUpdate(); //Execute the Delete
            st.close(); //Close the Statment
            con.close(); //Close the connection

            isSuccess = true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        }
        
        return isSuccess;
    }

    //Select specifical register on the mysql table
    public List<Cuba> searchCuba(int idCuba) throws SQLException, Exception {

        List<Cuba> lista = new ArrayList();
        String query = "SELECT * FROM cuba WHERE id_Cuba = ?;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(2, idCuba);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Cuba cb = new Cuba();

            cb.setIdCuba(rs.getInt("id_Cuba"));
            cb.setNameCuba(rs.getString("name_Cuba"));
            cb.setSizeCubaIdSizeCuba(rs.getInt("size_Cuba_id_Size_Cuba"));

            lista.add(cb);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}