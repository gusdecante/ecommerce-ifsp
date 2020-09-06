package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Phone;

import util.MysqlConnection;

public class PhoneDao {

    private Connection con;

    //Mysql connection created
    public PhoneDao() {
        con = new MysqlConnection().getConnection();
    }

    //Insert register on the mysql table
    public int registerPhone(Phone i) {
        int ok = 0;
        try {
            String query = "INSERT INTO phone (id_Phone, phone, customer_id_Customer) VALUES (?, ?, ?) ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getPhone());
            st.setInt(3, i.getCustomerIdCustomer());

            ok = st.executeUpdate(); //Execute the insert
            st.close(); //Close the Statment
            con.close(); //Close the connection
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ok;
    }

    //Select register on the mysql table
    public List<Phone> searchPhone() throws SQLException, Exception {

        List<Phone> lista = new ArrayList<Phone>();
        String query = "SELECT * FROM phone ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Phone ph = new Phone();

            ph.setIdPhone(rs.getInt("id_Phone"));
            ph.setPhone(rs.getString("phone"));
            ph.setCustomerIdCustomer(rs.getInt("customer_id_Customer"));

            lista.add(ph);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public int updatePhone(Phone u) {
        int ok = 0;
        try {
            String query = "UPDATE phone SET phone = ?, customer_id_Customer = ? WHERE id_Phone = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<Phone> l = new PhoneDao().searchPhone(u.getIdPhone());
                
            for (Phone lc : l) {
                st.setInt(3, lc.getIdPhone());
            }
            st.setString(1, u.getPhone());
            st.setInt(2, u.getCustomerIdCustomer());

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
    public int deletePhone(int d) {
        int ok = 0;
        try {
            String query = "DELETE FROM phone WHERE id_Phone = ?;";

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
    public List<Phone> searchPhone(int idPhone) throws SQLException, Exception {

        List<Phone> lista = new ArrayList<Phone>();
        String query = "SELECT * FROM phone WHERE id_Phone = ? ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, idPhone);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Phone ph = new Phone();

            ph.setIdPhone(rs.getInt("id_Phone"));
            ph.setPhone(rs.getString("phone"));
            ph.setCustomerIdCustomer(rs.getInt("customer_id_Customer"));

            lista.add(ph);

        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
    //Select specifical register on the mysql table
    public List<Phone> searchPhoneCustomer(int customerIdCustomer) throws SQLException, Exception {

        List<Phone> lista = new ArrayList<Phone>();
        String query = "SELECT * FROM phone WHERE customer_id_Customer = ? ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, customerIdCustomer);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Phone ph = new Phone();

            ph.setIdPhone(rs.getInt("id_Phone"));
            ph.setPhone(rs.getString("phone"));
            ph.setCustomerIdCustomer(rs.getInt("customer_id_Customer"));

            lista.add(ph);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

}