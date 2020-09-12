package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.OrderItem;

import util.MysqlConnection;

public class OrderItemDao {

    private Connection con;

    //Mysql connection created
    public OrderItemDao() {
        con = new MysqlConnection().getConnection();
    }

    //Insert register on the mysql table
    public int registerOrderItem(OrderItem i) {
        int ok = 0;
        try {
            String query = "INSERT INTO order_Item (id_Order_Item, amount, product_id_Product, order_id_Order) VALUES (?, ?, ?, ?) ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setInt(2, i.getAmount());
            st.setInt(3, i.getProductIdProduct());
            st.setInt(4, i.getOrderIdItem());

            ok = st.executeUpdate(); //Execute the insert
            st.close(); //Close the Statment
            con.close(); //Close the connection
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ok;
    }

    //Select register on the mysql table
    public List<OrderItem> searchOrderItem() throws SQLException, Exception {

        List<OrderItem> lista = new ArrayList<OrderItem>();
        String query = "SELECT * FROM order_Item ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            OrderItem oi = new OrderItem();

            oi.setIdOrderItem(rs.getInt("id_Order_Item"));
            oi.setAmount(rs.getInt("amount"));
            oi.setProductIdProduct(rs.getInt("product_id_Product"));
            oi.setOrderIdItem(rs.getInt("order_id_Order"));

            lista.add(oi);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public int updateOrderItem(OrderItem u) {
        int ok = 0;
        try {
            String query = "UPDATE order_Item SET amount = ?, product_id_Product = ?, order_id_Order = ? WHERE id_Order_Item = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<OrderItem> l = new OrderItemDao().searchOrderItem(u.getIdOrderItem());
                
            for (OrderItem lc : l) {
                st.setInt(4, lc.getIdOrderItem());
            }
            st.setInt(1, u.getAmount());
            st.setInt(2, u.getProductIdProduct());
            st.setInt(3, u.getOrderIdItem());

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
    public int deleteOrderItem(int d) {
        int ok = 0;
        try {
            String query = "DELETE FROM order_Item WHERE id_Order_Item = ?;";

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
    public List<OrderItem> searchOrderItem(int idOrderItem) throws SQLException, Exception {

        List<OrderItem> lista = new ArrayList<OrderItem>();
        String query = "SELECT * FROM order_Item WHERE id_Order_Item = ? ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, idOrderItem);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            OrderItem oi = new OrderItem();

            oi.setIdOrderItem(rs.getInt("id_Order_Item"));
            oi.setAmount(rs.getInt("amount"));
            oi.setProductIdProduct(rs.getInt("product_id_Product"));
            oi.setOrderIdItem(rs.getInt("order_id_Order"));

            lista.add(oi);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Select specifical register on the mysql table
    public List<OrderItem> searchOrderIdOrder(int OrderIdOrder) throws SQLException, Exception {

        List<OrderItem> lista = new ArrayList<OrderItem>();
        String query = "SELECT order_Item.*, product.*, category.* FROM order_Item, product, category WHERE order_Item.product_id_Product = product.id_Product AND product.category_id_Category = category.id_Category AND order_Item.order_id_Order = ? ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, OrderIdOrder);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            OrderItem oi = new OrderItem();

            oi.setIdOrderItem(rs.getInt("id_Order_Item"));
            oi.setAmount(rs.getInt("amount"));
            oi.setProductIdProduct(rs.getInt("product_id_Product"));
            oi.setOrderIdItem(rs.getInt("order_id_Order"));

            oi.setIdProduct(rs.getInt("id_Product"));
            oi.setCategoryIdCategory(rs.getInt("category_id_Category"));
            oi.setColor(rs.getString("color"));
            oi.setFinishingProcess(rs.getString("finishing_Process"));
            oi.setCubaType(rs.getString("cuba_Type"));
            oi.setDescription(rs.getString("description"));
            oi.setStock(rs.getInt("stock"));
            oi.setUnitaryValue(rs.getDouble("unitary_Value"));
            oi.setImageLink(rs.getString("image_Link"));

            oi.setIdCategory(rs.getInt("id_Category"));
            oi.setCategory(rs.getString("category"));

            lista.add(oi);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}