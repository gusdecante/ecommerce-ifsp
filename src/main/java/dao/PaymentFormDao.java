package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PaymentForm;

import util.MysqlConection;

public class PaymentFormDao {

    private Connection con;

    //Mysql connection created
    public PaymentFormDao() {
        con = new MysqlConection().getConnection();
    }

    //Insert register on the mysql table
    public boolean registerPaymentForm(PaymentForm i) {
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO payment_Form (id_PaymentForm, payment_Form) VALUES (?, ?);";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getPaymentForm());

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
    public List<PaymentForm> searchPaymentForm() throws SQLException, Exception {

        List<PaymentForm> lista = new ArrayList();
        String query = "SELECT * FROM payment_Form ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            PaymentForm pf = new PaymentForm();

            pf.setIdPaymentForm(rs.getInt("id_PaymentForm"));
            pf.setPaymentForm(rs.getString("payment_Form"));

            lista.add(pf);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public boolean updatePaymentForm(PaymentForm u) {
        boolean isSuccess = false;
        try {
            String query = "UPDATE payment_Form SET id_PaymentForm = ?, payment_Form = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<PaymentForm> l = new PaymentFormDao().searchPaymentForm(u.getIdPaymentForm());
                
            for (PaymentForm lc : l) {
                st.setInt(1, lc.getIdPaymentForm());
            }
            st.setString(2, u.getPaymentForm());

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
    public boolean deletePaymentForm(int d) {
        boolean isSuccess = false;
        try {
            String query = "DELETE FROM payment_Form WHERE id_PaymentForm = ?;";

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
    public List<PaymentForm> searchPaymentForm(int idPaymentForm) throws SQLException, Exception {

        List<PaymentForm> lista = new ArrayList();
        String query = "SELECT * FROM payment_Form WHERE id_PaymentForm = ?;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(2, idPaymentForm);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            PaymentForm pf = new PaymentForm();

            pf.setIdPaymentForm(rs.getInt("id_PaymentForm"));
            pf.setPaymentForm(rs.getString("payment_Form"));

            lista.add(pf);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}