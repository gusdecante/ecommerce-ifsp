package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

import util.MysqlConnection;

public class ProductDao {

    private Connection con;

    //Mysql connection created
    public ProductDao() {
        con = new MysqlConnection().getConnection();
    }

    //Insert register on the mysql table
    public boolean registerProduct(Product i) {
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO product (id_Product, category_id_Category, color, finishing_Process, cuba_Type, description, stock, unitary_Value, image_Link) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setInt(2, i.getCategoryIdCategory());
            st.setString(3, i.getColor());
            st.setString(4, i.getFinishingProcess());
            st.setString(5, i.getCubaType());
            st.setString(6, i.getDescription());
            st.setInt(7, i.getStock());
            st.setDouble(8, i.getUnitaryValue());
            st.setString(9, i.getImageLink());
            
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
    public List<Product> searchProduct() throws SQLException, Exception {

        List<Product> lista = new ArrayList();
        String query = "SELECT * FROM product ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Product p = new Product();

            p.setIdProduct(rs.getInt("id_Product"));
            p.setCategoryIdCategory(rs.getInt("category_id_Category"));
            p.setColor(rs.getString("color"));
            p.setFinishingProcess(rs.getString("finishing_Process"));
            p.setCubaType(rs.getString("cuba_Type"));
            p.setDescription(rs.getString("description"));
            p.setStock(rs.getInt("stock"));
            p.setUnitaryValue(rs.getDouble("unitary_Value"));
            p.setImageLink(rs.getString("image_Link"));

            lista.add(p);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public boolean updateProduct(Product u) {
        boolean isSuccess = false;
        try {
            String query = "UPDATE product SET id_Product = ?, category_id_Category = ?, color = ?, finishing_Process = ?, cuba_Type = ?, description = ?, stock = ?, unitary_Value = ?, image_Link = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<Product> l = new ProductDao().searchProduct(u.getIdProduct());
                
            for (Product lc : l) {
                st.setInt(1, lc.getIdProduct());
            }
            st.setInt(2, u.getCategoryIdCategory());
            st.setString(3, u.getColor());
            st.setString(4, u.getFinishingProcess());
            st.setString(5, u.getCubaType());
            st.setString(6, u.getDescription());
            st.setInt(7, u.getStock());
            st.setDouble(8, u.getUnitaryValue());
            st.setString(9, u.getImageLink());

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
    public boolean deleteProduct(int d) {
        boolean isSuccess = false;
        try {
            String query = "DELETE FROM product WHERE id_Product = ?;";

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
    public List<Product> searchProduct(int idProduct) throws SQLException, Exception {

        List<Product> lista = new ArrayList();
        String query = "SELECT * FROM product WHERE id_Product = ?;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(2, idProduct);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Product p = new Product();

            p.setIdProduct(rs.getInt("id_Product"));
            p.setCategoryIdCategory(rs.getInt("category_id_Category"));
            p.setColor(rs.getString("color"));
            p.setFinishingProcess(rs.getString("finishing_Process"));
            p.setCubaType(rs.getString("cuba_Type"));
            p.setDescription(rs.getString("description"));
            p.setStock(rs.getInt("stock"));
            p.setUnitaryValue(rs.getDouble("unitary_Value"));
            p.setImageLink(rs.getString("image_Link"));

            lista.add(p);
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}