package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

import util.MysqlConection;

public class ProductDao {

    private Connection con;

    //Mysql connection created
    public ProductDao() {
        con = new MysqlConection().getConnection();
    }

    //Insert register on the mysql table
    public boolean registerProduct(Product i) {
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO product (id_Product, name_Product, descricao, imagem_Link, stock, unitary_Value, cuba_id_Cuba, finishing_Process_id_Finishing, color_id_Color, category_id_Category) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?);";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getNameProduct());
            st.setString(3, i.getDescription());
            st.setString(4, i.getImageLink());
            st.setInt(5, i.getStock());
            st.setDouble(6, i.getUnitaryValue());
            st.setInt(7, i.getCubaIdCuba());
            st.setInt(8, i.getFinishingProcessIdFinishing());
            st.setInt(9, i.getColorIdColor());
            st.setInt(10, i.getCategoryIdCategory());
            
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
            p.setNameProduct(rs.getString("name_Product"));
            p.setDescription(rs.getString("description"));
            p.setImageLink(rs.getString("image_Link"));
            p.setStock(rs.getInt("stock"));
            p.setUnitaryValue(rs.getDouble("unitary_Value"));
            p.setCubaIdCuba(rs.getInt("cuba_id_Cuba"));
            p.setFinishingProcessIdFinishing(rs.getInt("finishing_Process_id_Finishing"));
            p.setColorIdColor(rs.getInt("color_id_Color"));
            p.setCategoryIdCategory(rs.getInt("category_id_Category"));

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
            String query = "UPDATE product SET id_Product = ?, name_Product = ?, description = ?, image_Link = ?, stock = ?, unitary_Value = ?, cuba_id_Cuba = ?, finishing_Process_id_Finishing = ?, color_id_Color = ?, category_id_Category = ?;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<Product> l = new ProductDao().searchProduct(u.getIdProduct());
                
            for (Product lc : l) {
                st.setInt(1, lc.getIdProduct());
            }
            st.setString(2, u.getNameProduct());
            st.setString(3, u.getDescription());
            st.setString(4, u.getImageLink());
            st.setInt(5, u.getStock());
            st.setDouble(6, u.getUnitaryValue());
            st.setInt(7, u.getCubaIdCuba());
            st.setInt(8, u.getFinishingProcessIdFinishing());
            st.setInt(9, u.getColorIdColor());
            st.setInt(10, u.getCategoryIdCategory());

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
            p.setNameProduct(rs.getString("name_Product"));
            p.setDescription(rs.getString("description"));
            p.setImageLink(rs.getString("image_Link"));
            p.setStock(rs.getInt("stock"));
            p.setUnitaryValue(rs.getDouble("unitary_Value"));
            p.setCubaIdCuba(rs.getInt("cuba_id_Cuba"));
            p.setFinishingProcessIdFinishing(rs.getInt("finishing_Process_id_Finishing"));
            p.setColorIdColor(rs.getInt("color_id_Color"));
            p.setCategoryIdCategory(rs.getInt("category_id_Category"));

            lista.add(p);
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}