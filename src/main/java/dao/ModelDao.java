package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.MysqlConection;

public class /*Class*/Dao {

    private Connection con;

    //Mysql connection created
    public /*class*/Dao() {
        con = new MysqlConection().getConnection();
    }

    //Insert register on the mysql table
    public boolean register/*class*/(/*class*/ i) {
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO /*class*/ (id_/*class*/, , , , ) VALUES (?, ?, ?, ?, ?);";

            PreparedStatement st = con.prepareStatement(query); 
            st.setInt(1, 0);
            st.setString(2, i.get/*field*/());
            st.setString(3, i.get/*field*/());
            st.setString(4, i.get/*field*/());
            st.setString(5, i.get/*field*/());

            st.execute(); //Execute the insert
            st.close(); //Close the Statment
            isSuccess = true;
            
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
        String query = "SELECT * FROM /*class*/ ;";

        PreparedStatement st = con.prepareStatement(query);
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {

            /*class*/ /*object*/ = new /*class*/();
            /*object*/.setId/*class*/(rs.getInt("id_/*class*/"));
            /*object*/.set/*field*/(rs.getString("/*field-db*/"));
            /*object*/.set/*field*/(rs.getString("/*field-db*/"));
            /*object*/.set/*field*/(rs.getString("/*field-db*/"));
            /*object*/.set/*field*/(rs.getString("/*field-db*/"));

            lista.add(/*object*/);
           
        }

        con.close(); //Close the Statement

        return lista;
    }

    //Update register on the mysql table
    public boolean update/*class*/(/*class*/ u) {
        boolean isSuccess = false;
        try {
            String query = "UPDATE /*class*/ SET id_/*class*/ = ?, *field* = ?, , , ;";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, u.getId/*class*/());
            st.setString(2, u.get/*field-db*/());
            st.setString(3, u.get/*field-db*/());
            st.setString(4, u.get/*field-db*/());
            st.setString(5, u.get/*field-db*/());

            isSuccess = true;

        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        }
        con.close();
        return isSuccess;
    }

    //delete register on the mysql table
    public boolean delete/*class*/(int d) {
        boolean isSuccess = false;
        try {
            String query = "DELETE FROM /*class*/ WHERE id_/*class*/ = ?;";

            PreparedStatement st = con.prepareStatement(query); 
            st.setInt(1, d);

            st.execute(); //Execute the insert
            st.close(); //Close the Statment
            isSuccess = true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        }
        
        con.close(); //Close the connection
        return isSuccess;
    }

    //Select specifical register on the mysql table
    public List</*class*/> search/*class*/(String parameter) throws SQLException, Exception {

        List</*class*/> lista = new ArrayList();
        String query = "SELECT * FROM /*class*/ WHERE  = ?;";

        PreparedStatement st = con.prepareStatement(query);
        st.setString(2, parameter);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {

            /*class*/ /*object*/ = new /*class*/();
            /*object*/.setId/*class*/(rs.getInt("id_/*class*/"));
            /*object*/.set/*field*/(rs.getString("/*field-db*/"));
            /*object*/.set/*field*/(rs.getString("/*field-db*/"));
            /*object*/.set/*field*/(rs.getString("/*field-db*/"));
            /*object*/.set/*field*/(rs.getString("/*field-db*/"));

            lista.add(/*object*/);
           
        }

        con.close(); //Close the Statement

        return lista;
    }
    
}