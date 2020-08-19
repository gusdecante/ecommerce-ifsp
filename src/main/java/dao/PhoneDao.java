package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}