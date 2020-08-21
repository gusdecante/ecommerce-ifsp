package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.MysqlConection;
import model.Phone;

public class PhoneDao {
    private Connection con;

    public PhoneDao() {
        con = new MysqlConection().getConnection();
    }
    //Register Customer
    public void registerPhone(Phone ph) throws SQLException {
        String query;
        query = "INSERT INTO phone(id_Phone, phone, customer_id_Customer) VALUES(?, ?, ?);";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, ph.getIdPhone());
        st.setString(2, ph.getPhone());
        st.setInt(3, ph.getCustomerIdCustomer());
        st.execute();
        st.close();
        con.close();
    }

    //List of phone number of the customer
    public List<Phone> search(int idCustomer) throws SQLException, Exception{
        List<Phone> listOPhones = new ArrayList<>();
        String query = "SELECT * FROM phone WHERE customer_id_Customer = '" + idCustomer+ "'";
        PreparedStatement st = con.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while(rs.next()) {
            Phone ph = new Phone();

            ph.setIdPhone(rs.getInt("id_Phone"));
            ph.setPhone(rs.getString("phone"));
            ph.setCustomerIdCustomer(rs.getInt("customer_id_Customer"));
            listOPhones.add(ph);
        }

        return listOPhones;
    } 

    //Delete phone
    public boolean delete/*class*/(int idPhone) {
        boolean isSuccess = false;
        try {
            String query = "DELETE FROM phone WHERE id_Phone = ?;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            st.setInt(1, idPhone);

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
}