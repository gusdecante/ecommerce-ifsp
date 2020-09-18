package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Order;

import util.MysqlConnection;

public class OrderDao {

    private Connection con;

    //Mysql connection created
    public OrderDao() {
        con = new MysqlConnection().getConnection();
    }

    //Insert register on the mysql table
    public int registerOrder(Order i) {
        int ok = 0;
        try {
            String query = "INSERT INTO ecommerceDB.order (id_Order, date, payment_form_id_Payment_Form) VALUES (?, ?, ?) ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getDate());
            st.setInt(3, i.getPaymentFormIdPaymentForm());

            ok = st.executeUpdate(); //Execute the insert
            st.close(); //Close the Statment
            con.close(); //Close the connection
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ok;
    }

    //Select register on the mysql table
    public List<Order> searchOrder() throws SQLException, Exception {

        List<Order> lista = new ArrayList<Order>();
        String query = "SELECT ecommerceDB.order.*, payment_Form.* FROM ecommerceDB.order, payment_Form WHERE ecommerceDB.order.payment_form_id_Payment_Form = payment_Form.id_Payment_Form;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Order od = new Order();

            od.setIdOrder(rs.getInt("id_Order"));
            od.setDate(rs.getString("date"));
            od.setPaymentFormIdPaymentForm(rs.getInt("payment_form_id_Payment_Form"));

            od.setIdPaymentForm(rs.getInt("id_Payment_Form"));
            od.setPaymentForm(rs.getString("payment_Form"));

            lista.add(od);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public int updateOrder(Order u) {
        int ok = 0;
        try {
            String query = "UPDATE ecommerceDB.order SET date = ?, payment_form_id_Payment_Form = ? WHERE id_Order = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<Order> l = new OrderDao().searchOrder(u.getIdOrder());
                
            for (Order lc : l) {
                st.setInt(3, lc.getIdOrder());
            }
            st.setString(1, u.getDate());
            st.setInt(2, u.getPaymentFormIdPaymentForm());

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
    public int deleteOrder(int d) {
        int ok = 0;
        try {
            String query = "DELETE FROM ecommerceDB.order WHERE id_Order = ?;";

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
    public List<Order> searchOrder(int idOrder) throws SQLException, Exception {

        List<Order> lista = new ArrayList<Order>();
        String query = "SELECT ecommerceDB.order.*, payment_Form.* FROM ecommerceDB.order, payment_Form WHERE ecommerceDB.order.payment_form_id_Payment_Form = payment_Form.id_Payment_Form AND id_Order = ? ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, idOrder);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Order od = new Order();

            od.setIdOrder(rs.getInt("id_Order"));
            od.setDate(rs.getString("date"));
            od.setPaymentFormIdPaymentForm(rs.getInt("payment_form_id_Payment_Form"));

            od.setIdPaymentForm(rs.getInt("id_Payment_Form"));
            od.setPaymentForm(rs.getString("payment_Form"));

            lista.add(od);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}