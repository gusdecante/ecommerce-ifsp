package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.OrderDao;
import util.MysqlConection;


public class OrderDao {
    private static Connection con;

    public OrderDao() {
        con = new MysqlConection().getConnection(); //Create the connection
    }

    public void registerOrder(User u) throws SQLException {
        String query;
        query = "INSERT INTO order (id_Order, date, payment_form_id_Payment_Form) VALUES (?, ?, ?);";

        PreparedStatement st = con.prepareStatement(query); //User the connection to set the values
        st.setInt(1, u.getIdOrder());
        st.setDate(2, u.getDate());
        st.setInt(3, u.getPaymentFormIdPaymentForm());

        st.execute(); //Execute the query
        st.close(); //Close the Statment
        con.close(); //Close the connection
    }


     //This method search the details about the user
     public static Order searchOrderDetails(Date date) throws SQLException, Exception {
        Order ord = null;

        String query = "SELECT * FROM order WHERE date = '" + date + "'";

        PreparedStatement st = con.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while(rs.next()) {
             ord.setIdOrder( rs.getInt("id_Order"));
             ord.setDate(rs.getDate("date"));
             ord.setPaymentFormIdPaymentForm(rs.getInt("getPaymentFormIdPaymentForm"));
        }

        con.close();
        return ord;
    }


    /*This method changes the informations about the User, except for the type, 
    this is only registered one time*/
    public static boolean updateOrder(Order order) throws SQLException {
        boolean isSuccess = false;
        try {
            String query = "UPDATE Order SET id_Order = ?, date = ?, payment_form_id_Payment_Form = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, order.getidOrder());
            st.setDate(2, order.getDate());
            st.setInt(3, order.getPaymentFormIdPaymentForm());
            //The type of user stay the same

            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        con.close();
        return isSuccess;
    }    
    
}

