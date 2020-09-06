package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Address;

import util.MysqlConnection;

public class AddressDao {

    private Connection con;

    //Mysql connection created
    public AddressDao() {
        con = new MysqlConnection().getConnection();
    }

    //Insert register on the mysql table
    public int registerAddress(Address i) {
        int ok = 0;
        try {
            String query = "INSERT INTO address (id_Address, street, number, district, city, state, zip_Code, customer_id_Customer) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getStreet());
            st.setString(3, i.getNumber());
            st.setString(4, i.getDistrict());
            st.setString(5, i.getCity());
            st.setString(6, i.getState());
            st.setString(7, i.getZipCode());
            st.setInt(8, i.getCustomerIdCustomer());

            ok = st.executeUpdate(); //Execute the insert
            st.close(); //Close the Statment
            con.close(); //Close the connection
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ok;
    }

    //Select register on the mysql table
    public List<Address> searchAddress() throws SQLException, Exception {

        List<Address> lista = new ArrayList<Address>();
        String query = "SELECT * FROM address ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Address ad = new Address();

            ad.setIdAddress(rs.getInt("id_Address"));
            ad.setStreet(rs.getString("street"));
            ad.setNumber(rs.getString("number"));
            ad.setDistrict(rs.getString("district"));
            ad.setCity(rs.getString("city"));
            ad.setState(rs.getString("state"));
            ad.setZipCode(rs.getString("zip_Code"));
            ad.setCustomerIdCustomer(rs.getInt("customer_id_Customer"));

            lista.add(ad);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public int updateAddress(Address u) {
        int ok = 0;
        try {
            String query = "UPDATE address SET street = ?, number = ?, district = ?, city = ?, state = ?, zip_Code = ?, customer_id_Customer = ? WHERE id_Address = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<Address> l = new AddressDao().searchAddress(u.getIdAddress());
                
            for (Address lc : l) {
                st.setInt(8, lc.getIdAddress());
            }
            st.setString(1, u.getStreet());
            st.setString(2, u.getNumber());
            st.setString(3, u.getDistrict());
            st.setString(4, u.getCity());
            st.setString(5, u.getState());
            st.setString(6, u.getZipCode());
            st.setInt(7, u.getCustomerIdCustomer());

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
    public int deleteAddress(int d) {
        int ok = 0;
        try {
            String query = "DELETE FROM address WHERE id_Address = ? ;";

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
    public List<Address> searchAddress(int idAddress) throws SQLException, Exception {

        List<Address> lista = new ArrayList<Address>();
        String query = "SELECT * FROM address WHERE id_Address = ? ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, idAddress);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Address ad = new Address();

            ad.setIdAddress(rs.getInt("id_Address"));
            ad.setStreet(rs.getString("street"));
            ad.setNumber(rs.getString("number"));
            ad.setDistrict(rs.getString("district"));
            ad.setCity(rs.getString("city"));
            ad.setState(rs.getString("state"));
            ad.setZipCode(rs.getString("zip_Code"));
            ad.setCustomerIdCustomer(rs.getInt("customer_id_Customer"));

            lista.add(ad);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
    //Select specifical register on the address table by customer
    public List<Address> searchAddressCustomer(int customerIdCustomer) throws SQLException, Exception {

        List<Address> lista = new ArrayList<Address>();
        String query = "SELECT * FROM address WHERE customer_id_Customer = ? ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, customerIdCustomer);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            Address ad = new Address();

            ad.setIdAddress(rs.getInt("id_Address"));
            ad.setStreet(rs.getString("street"));
            ad.setNumber(rs.getString("number"));
            ad.setDistrict(rs.getString("district"));
            ad.setCity(rs.getString("city"));
            ad.setState(rs.getString("state"));
            ad.setZipCode(rs.getString("zip_Code"));
            ad.setCustomerIdCustomer(rs.getInt("customer_id_Customer"));

            lista.add(ad);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

}