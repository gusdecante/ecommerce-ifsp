package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Color;

import util.MysqlConection;

public class ColorDao {

    private Connection con;

    //Mysql connection created
    public ColorDao() {
        con = new MysqlConection().getConnection();
    }

    //Insert register on the mysql table
    public boolean registerColor(Color i) {
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO color (id_Color, name_Color) VALUES (?, ?);";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getNameColor());

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
    public List<Color> searchColor() throws SQLException, Exception {

        List<Color> lista = new ArrayList();
        String query = "SELECT * FROM color ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Color cl = new Color();

            cl.setIdColor(rs.getInt("id_Color"));
            cl.setNameColor(rs.getString("name_Color"));

            lista.add(cl);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public boolean updateColor(Color u) {
        boolean isSuccess = false;
        try {
            String query = "UPDATE color SET id_Color = ?, name_Color = ?;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<Color> l = new ColorDao().searchColor(u.getIdColor());
                
            for (Color lc : l) {
                st.setInt(1, lc.getIdColor());
            }
            st.setString(2, u.getNameColor());

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
    public boolean deleteColor(int d) {
        boolean isSuccess = false;
        try {
            String query = "DELETE FROM color WHERE id_Color = ?;";

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
    public List<Color> searchColor(int idColor) throws SQLException, Exception {

        List<Color> lista = new ArrayList();
        String query = "SELECT * FROM color WHERE id_Color = ?;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(2, idColor);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Color cl = new Color();

            cl.setIdColor(rs.getInt("id_Color"));
            cl.setNameColor(rs.getString("name_Color"));

            lista.add(cl);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}