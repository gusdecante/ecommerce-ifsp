package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.MysqlConection;

public class ModelDao {

    private Connection con;

    //Mysql connection created
    public /*class*/Dao() {
        con = new MysqlConection().getConnection();
    }

    //Insert register on the mysql table
    public boolean register/*class*/(/*object*/ i) {
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO *TABLE* (id_, , , , ) VALUES (?, ?, ?, ?, ?);";

            PreparedStatement st = con.prepareStatement(query); 
            st.setInt(1, i.getId/*field*/());
            st.setString(2, i.get/*field*/());
            st.setString(3, i.get/*field*/());
            st.setString(4, i.get/*field*/());
            st.setString(5, i.get/*field*/());

            st.execute(); //Execute the insert
            st.close(); //Close the Statment
            
        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        }
        
        con.close(); //Close the connection
        return isSuccess;
    }

    //Select register on the mysql table
    public List</*class*/> search/*class*/() throws SQLException, Exception {

        List</*class*/> lista = new ArrayList();
        String query = "SELECT * FROM *TABLE* ;";

        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {

            /*Class and object*/ = new /*class*/();
            /*object*/.setId/*field*/(rs.getInt("id_/*field-db*/"));
            /*object*/.set/*field*/(rs.getString("/*field-db*/"));

            lista.add();
           
        }

        con.close(); //Close the Statement

        return lista;
    }

    //Update register on the mysql table
    public boolean update/*class*/(/*class*/ /*object*/) throws SQLException {
        boolean isSuccess = false;
        try {
            String query = "UPDATE *TABLE* SET id_ = ?, *field* = ?, , , ;";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, /*object*/.getId/*field*/());
            st.setString(2, /*object*/.get/*field-db*/());

            isSuccess = true;

        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        }
        con.close();
        return isSuccess;
    }

    //delete register on the mysql table
    public boolean delete/*class*/(/*object*/ d) throws SQLException {
        boolean isSuccess = false;
        try {
            String query = "DELETE FROM *TABLE* WHERE *field* = ?;";

            PreparedStatement st = con.prepareStatement(query); 
            st.setInt(1, d.getId/*field*/());
            st.setString(2, d.get/*field*/());

            st.execute(); //Execute the insert
            st.close(); //Close the Statment
            
        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        }
        
        con.close(); //Close the connection
        return isSuccess;
    }
    
}