package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import util.MysqlConnection;

public class CategoryDao {

    private Connection con;

    //Mysql connection created
    public CategoryDao() {
        con = new MysqlConnection().getConnection();
    }

    //Insert register on the mysql table
    public boolean registerCategory(Category i) {
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO category (id_Category, category) VALUES (?, ?);";

            PreparedStatement st = con.prepareStatement(query); 
            st.setInt(1, 0);
            st.setString(2, i.getCategory());

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
    public List<Category> searchCategory() throws SQLException, Exception {

        List<Category> lista = new ArrayList();
        String query = "SELECT * FROM category ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Category ct = new Category();

            ct.setIdCategory(rs.getInt("id_Category"));
            ct.setCategory(rs.getString("category"));

            lista.add(ct);

        }
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public boolean updateCategory(Category u) {
        boolean isSuccess = false;     
        try {
            String query = "UPDATE category SET id_Category = ?, category = ?;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<Category> l = new CategoryDao().searchCategory(u.getIdCategory());
            
            for (Category lc : l) {
                st.setInt(1, lc.getIdCategory());
            }

            st.setString(2, u.getCategory());

            st.executeUpdate(); //Execute the update
            st.close(); //Close the Statement
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
    public boolean deleteCategory(int d) {
        boolean isSuccess = false;
        try {
            String query = "DELETE FROM category WHERE id_Category = ?;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            st.setInt(1, d);

            st.executeUpdate(); //Execute the delete
            st.close(); //Close de statement
            con.close(); //Close the connection

            isSuccess = true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        }
        
        return isSuccess;
    }

    //Select specifical register on the mysql table
    public List<Category> searchCategory(int idCategory) throws SQLException, Exception {

        List<Category> lista = new ArrayList();
        String query = "SELECT * FROM Category WHERE id_Category = ?;";

        PreparedStatement st = con.prepareStatement(query);//Prepared the query
        st.setInt(1, idCategory);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {

            Category ct = new Category();
            ct.setIdCategory(rs.getInt("id_Category"));
            ct.setCategory(rs.getString("category"));

            lista.add(ct);
        }

        con.close(); //Close the connection

        return lista;
    }
    
}