package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

import util.MysqlConnection;

public class CustomerDao {

    private Connection con;

    //Mysql connection created
    public CustomerDao() {
        con = new MysqlConnection().getConnection();
    }

    //Insert register on the mysql table
    public int registerCustomer(Customer i) {
        int ok = 0;
        try {
            String query = "INSERT INTO customer (id_Customer, user_id_User) VALUES (?, ?) ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setInt(2, i.getUserIdUser());

            ok = st.executeUpdate(); //Execute the insert

            query = "SELECT LAST_INSERT_ID();";
            st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery(); //Execute the select

            while(rs.next()) {
                ok = rs.getInt(1);    
            }
            st.close(); //Close the Statment
            con.close(); //Close the connection
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ok;
    }

    //Select register on the mysql table
    public List<Customer> searchCustomer() throws SQLException, Exception {

        List<Customer> lista = new ArrayList<Customer>();
        String query = "SELECT * FROM customer ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Customer ct = new Customer();

            ct.setIdCustomer(rs.getInt("id_Customer"));
            ct.setUserIdUser(rs.getInt("user_id_User"));

            lista.add(ct);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public int updateCustomer(Customer u) {
        int ok = 0;
        try {
            String query = "UPDATE customer SET user_id_User = ? WHERE id_Customer = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<Customer> l = new CustomerDao().searchCustomer(u.getIdCustomer());
                
            for (Customer lc : l) {
                st.setInt(2, lc.getIdCustomer());
            }
            st.setInt(1, u.getUserIdUser());

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
    public int deleteCustomer(int d) {
        int ok = 0;
        try {
            String query = "DELETE FROM customer WHERE id_Customer = ?;";

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
    public List<Customer> searchCustomer(int idCustomer) throws SQLException, Exception {

        List<Customer> lista = new ArrayList<Customer>();
        String query = "SELECT * FROM customer WHERE id_Customer = ? ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, idCustomer);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Customer ct = new Customer();

            ct.setIdCustomer(rs.getInt("id_Customer"));
            ct.setUserIdUser(rs.getInt("user_id_User"));

            lista.add(ct);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}