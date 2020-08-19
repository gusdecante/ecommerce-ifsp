package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SizeCuba;

import util.MysqlConection;

public class SizeCubaDao {

    private Connection con;

    //Mysql connection created
    public SizeCubaDao() {
        con = new MysqlConection().getConnection();
    }

    //Insert register on the mysql table
    public boolean registerSizeCuba(SizeCuba i) {
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO size_Cuba (id_SizeCuba, size) VALUES (?, ?);";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getSize());

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
    public List<SizeCuba> searchSizeCuba() throws SQLException, Exception {

        List<SizeCuba> lista = new ArrayList();
        String query = "SELECT * FROM size_Cuba ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            SizeCuba sc = new SizeCuba();

            sc.setIdSizeCuba(rs.getInt("id_SizeCuba"));
            sc.setSize(rs.getString("size"));

            lista.add(sc);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public boolean updateSizeCuba(SizeCuba u) {
        boolean isSuccess = false;
        try {
            String query = "UPDATE size_Cuba SET id_SizeCuba = ?, size = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<SizeCuba> l = new SizeCubaDao().searchSizeCuba(u.getIdSizeCuba());
                
            for (SizeCuba lc : l) {
                st.setInt(1, lc.getIdSizeCuba());
            }
            st.setString(2, u.getSize());

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
    public boolean deleteSizeCuba(int d) {
        boolean isSuccess = false;
        try {
            String query = "DELETE FROM size_Cuba WHERE id_SizeCuba = ?;";

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
    public List<SizeCuba> searchSizeCuba(int idSizeCuba) throws SQLException, Exception {

        List<SizeCuba> lista = new ArrayList();
        String query = "SELECT * FROM size_Cuba WHERE id_SizeCuba = ? ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(2, idSizeCuba);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            SizeCuba sc = new SizeCuba();

            sc.setIdSizeCuba(rs.getInt("id_SizeCuba"));
            sc.setSize(rs.getString("size"));

            lista.add(sc);
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}