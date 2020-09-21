package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PaymentForm;

import util.MysqlConnection;

public class PaymentFormDao {

    private Connection con;

    //Mysql connection created
    public PaymentFormDao() {
        con = new MysqlConnection().getConnection();
    }

    //Insert register on the mysql table
    public int registerPaymentForm(PaymentForm i) {
        int ok = 0;
        try {
            String query = "INSERT INTO payment_Form (id_Payment_Form, payment_Form) VALUES (?, ?);";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getPaymentForm());

            ok = st.executeUpdate(); //Execute the insert
            st.close(); //Close the Statment
            con.close(); //Close the connection
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ok;
    }

    //Select register on the mysql table
    public List<PaymentForm> searchPaymentForm() throws SQLException, Exception {

        List<PaymentForm> lista = new ArrayList<PaymentForm>();
        String query = "SELECT * FROM payment_Form ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            PaymentForm pf = new PaymentForm();

            pf.setIdPaymentForm(rs.getInt("id_Payment_Form"));
            pf.setPaymentForm(rs.getString("payment_Form"));

            lista.add(pf);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public int updatePaymentForm(PaymentForm u) {
        int ok = 0;
        try {
            String query = "UPDATE payment_Form SET payment_Form = ? WHERE id_Payment_Form = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<PaymentForm> l = new PaymentFormDao().searchPaymentForm(u.getIdPaymentForm());
                
            for (PaymentForm lc : l) {
                st.setInt(2, lc.getIdPaymentForm());
            }
            st.setString(1, u.getPaymentForm());

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
    public int deletePaymentForm(int d) {
        int ok = 0;
        try {
            String query = "DELETE FROM payment_Form WHERE id_Payment_Form = ? ;";

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
    public List<PaymentForm> searchPaymentForm(int idPaymentForm) throws SQLException, Exception {

        List<PaymentForm> lista = new ArrayList<PaymentForm>();
        String query = "SELECT * FROM payment_Form WHERE id_Payment_Form = ?;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, idPaymentForm);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            PaymentForm pf = new PaymentForm();

            pf.setIdPaymentForm(rs.getInt("id_Payment_Form"));
            pf.setPaymentForm(rs.getString("payment_Form"));

            lista.add(pf);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}