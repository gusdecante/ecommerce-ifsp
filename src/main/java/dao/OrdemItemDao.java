package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.OrderItem;
import util.MysqlConection;


public class OrderItemDao {
    private static Connection con;

    public OrderDao() {
        con = new MysqlConection().getConnection(); //Create the connection
    }

    public void registerOrder(OrderItem u) throws SQLException {
        String query;
        query = "INSERT INTO ordem_item (id_Order_Item, amount, product_id_Product, order_id_Order) VALUES (?, ?, ?, ?);";

        PreparedStatement st = con.prepareStatement(query); //User the connection to set the values
        st.setInt(1, u.getIdOrderItem());
        st.setInt(2, u.getAmount());
        st.setInt(3, u.getProductIdProduct());
        st.setInt(4, u.getOrderIdOrder());

        st.execute(); //Execute the query
        st.close(); //Close the Statment
        con.close(); //Close the connection
    }


     //This method search the details about the user
     public static OrderItem searchOrdemItemDetails(Date date) throws SQLException, Exception {
        Order ord = null;

        String query = "SELECT * FROM order_item WHERE id_Order_Item = '" + id_Order_Item + "'";

        PreparedStatement st = con.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while(rs.next()) {
             ord.setIdOrderItem( rs.getInt("id_Order_Item "));
             ord.setAmount(rs.getInt("amount"));
             ord.setProductIdProduct(rs.getInt("product_id_Product"));
             ord.setOrderIdOrder(rs.getInt("order_id_Order"));
        }

        con.close();
        return ord;
    }

    /*This method changes the informations about the User, except for the type, 
    this is only registered one time*/
    public static boolean updateOrdemItem(OrderItem orderItem) throws SQLException {
        boolean isSuccess = false;
        try {
            String query = "UPDATE order_item SET id_Ordem_Item = ?, amount = ?, product_id_Product = ?, order_id_Order = ? WHERE id_Ordem_Item = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, user.getIdOrdemItem());
            st.setInt(2, user.getAmount());
            st.setInt(3, user.getProductIdProduct());
            st.setInt(4, user.getOrderIdOrder());//The type of user stay the same

            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        con.close();
        return isSuccess;
    }    

} 
