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
    public int registerProduct(Product i) {
        int ok = 0;
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
            
            ok = st.executeUpdate(); //Execute the insert
            st.close(); //Close the Statment
            con.close(); //Close the connection
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ok;
    }

    //Select register on the mysql table
    public List<Product> searchProduct() throws SQLException, Exception {

        List<Product> lista = new ArrayList<Product>();
        String query = "SELECT product.*, category.* FROM product, category WHERE category_id_Category = id_Category ;";

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

            p.setIdCategory(rs.getInt("id_Category"));
            p.setCategory(rs.getString("category"));
            

            lista.add(p);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public int updateProduct(Product u) {

        int ok = 0;

        try {
            String query = "UPDATE product SET category_id_Category = ?, color = ?, finishing_Process = ?, cuba_Type = ?, description = ?, stock = ?, unitary_Value = ?, image_Link = ? WHERE id_Product = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated
            List<Product> l = new ProductDao().searchProduct(u.getIdProduct());
            
            for (Product lc : l) {
                st.setInt(9, lc.getIdProduct());
            }

            st.setInt(1, u.getCategoryIdCategory());
            st.setString(2, u.getColor());
            st.setString(3, u.getFinishingProcess());
            st.setString(4, u.getCubaType());
            st.setString(5, u.getDescription());
            st.setInt(6, u.getStock());
            st.setDouble(7, u.getUnitaryValue());
            st.setString(8, u.getImageLink());

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
    public int deleteProduct(int d) {
        int ok = 0;

        try {
            String query = "DELETE FROM product WHERE id_Product = ?;";

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
    public List<Product> searchProduct(int idProduct) throws SQLException, Exception {

        List<Product> lista = new ArrayList<Product>();
        String query = "SELECT product.*, category.* FROM product, category WHERE category_id_Category = id_Category AND id_Product = ? ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, idProduct);

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

            p.setIdCategory(rs.getInt("id_Category"));
            p.setCategory(rs.getString("category"));

            lista.add(p);
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }


    //Select specifical register on the mysql table
    public List<Product> searchProductCategory(int idCategory) throws SQLException, Exception {

        List<Product> lista = new ArrayList<Product>();
        String query = "SELECT product.*, category.* FROM product, category WHERE category_id_Category = id_Category AND category_id_Category = ? ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, idCategory);

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

            p.setIdCategory(rs.getInt("id_Category"));
            p.setCategory(rs.getString("category"));

            lista.add(p);
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
       
}