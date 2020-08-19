package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Address;
import model.PessoaJuridica;
import model.Phone;
import util.MysqlConection;

public class PessoaJuridicaDao {
    private Connection con;

    public PessoaJuridicaDao() {
        con = new MysqlConection().getConnection();
    }

    //Register Businesses 
    //PessoaJuridica extends customer who extends user, so all of the attributes will be here
    public void registerPessoaJuridica(PessoaJuridica p) throws SQLException {
        String query;
        //First - Insert User
        query = "INSERT INTO user (id_User, email, password, user_Tyoe, token) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, p.getIdUser());
        st.setString(2, p.getEmail());
        st.setString(3, p.getPassword());
        st.setInt(4, p.getTypeOfUser());
        st.setString(5, p.getToken());
        st.execute();

        //Second - Insert Customer 
        query = "INSERT INT customer(id_Customer, user_id_User) VALUES(?, ?)";
        st = con.prepareStatement(query);
        st.setInt(1, p.getIdCustomer());
        st.setInt(2, p.getIdUser());
        st.execute();

        //Third - Insert PessoaJuridica
        query = "INSERT INTO pessoa_Juridica(id_Pessoa_Juridica, CNPJ, razao_Social, customer_id_Customer) VALUES(?, ?, ?, ?)";
        st = con.prepareStatement(query);
        st.setInt(1, p.getIdPessoaJuridica());
        st.setString(2, p.getCnpj());
        st.setString(3, p.getRazaoSocial());
        st.setInt(4, p.getIdCustomer());
        st.execute();

        //Fourth - Insert Address
        Address adr = new Address();
        query = "INSERT INTO address(id_Address, street, number, district, city, state, zip_Code, customer_id_Customer) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        st = con.prepareStatement(query);
        st.setInt(1, adr.getIdAdress());
        st.setString(2, adr.getStreet());
        st.setString(3, adr.getNumber());
        st.setString(4, adr.getDistrict());
        st.setString(5, adr.getCity());
        st.setString(6, adr.getState());
        st.setString(7, adr.getZipCode());
        st.setInt(8, adr.getCustomerIdCustomer());
        st.execute();

        //Fifth - Insert the Phone
        Phone ph = new Phone();
        query = "INSERT INTO phone(id_Phone, phone, customer_id_Customer) VALUES(?, ?, ?)";
        st = con.prepareStatement(query);
        st.setInt(1, ph.getIdPhone());
        st.setString(2, ph.getPhone());
        st.setInt(3, ph.getCustomerIdCustomer());
        st.execute();

        st.close();
        con.close();
    }
    //Listing PessoaJuridica - Customers who was business
    public List<PessoaJuridica> search() throws SQLException, Exception {
        List<PessoaJuridica> list = new ArrayList();
        String query = "SELECT * FROM pessoa_Juridica";

        PreparedStatement st = con.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while(rs.next()) {
            PessoaJuridica pj = new PessoaJuridica();

            pj.setIdPessoaJuridica(rs.getInt("id_Pessoa_Juridica"));
            pj.setCnpj(rs.getString("CNPJ"));
            pj.setRazaoSocial(rs.getString("razao_Social"));
            pj.setIdCustomer(rs.getInt("customer_id_Customer"));
            list.add(pj);
        }
        return list;
    }

    //Search for a specific customer
    public List<PessoaJuridica> search(String Cnpj) throws SQLException, Exception {
        List<PessoaJuridica> list = new ArrayList();
        String query = "SELECT * FROM pessoa_Juridica WHERE CNPJ '" + Cnpj + "'";

        PreparedStatement st = con.prepareStatement(query);

        ResultSet rs = st.executeQuery();

        while(rs.next()) {
            PessoaJuridica pj = new PessoaJuridica();

            pj.setIdPessoaJuridica(rs.getInt("id_Pessoa_Juridica"));
            pj.setCnpj(rs.getString("CNPJ"));
            pj.setRazaoSocial(rs.getString("razao_Social"));
            pj.setIdCustomer(rs.getInt("customer_id_Customer"));
            list.add(pj);
        }
        return list;
    }
}