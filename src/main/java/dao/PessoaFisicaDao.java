package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.PessoaFisica;
import util.MysqlConection;

public class PessoaFisicaDao {
    private Connection con;

    public PessoaFisicaDao() {
        con = new MysqlConection().getConnection();
    }
    
    //Register normal customer, not an business
    //PessoaFísica extends User, so the attributer of user will be userd here as well
    public void registerPessoaFisica(PessoaFisica pf) throws SQLException {
        
        String query;
        //First - Insert User
        query = "INSERT INTO user (id_User, email, password, user_Tyoe, token) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, pf.getIdUser());
        st.setString(2, pf.getEmail());
        st.setString(3, pf.getPassword());
        st.setInt(4, pf.getTypeOfUser());
        st.setString(5, pf.getToken());
        st.execute();

        //Second - Insert Customer 
        query = "INSERT INT customer(id_Customer, user_id_User) VALUES(?, ?)";
        st = con.prepareStatement(query);
        st.setInt(1, pf.getIdCustomer());
        st.setInt(2, pf.getIdUser());
        st.execute();

        //Third - Insert Pessoa Física 
        query = "INSERT INTO pessoa_Fisica(id_Pessoa_Fisica, name_Customer, CPF, RG, date_Birth, customer_id_Customer) VALUES (?, ?, ?, ?, ?, ?)";
        st = con.prepareStatement(query);
        st.setInt(1, pf.getIdPessoaFisica());
        st.setString(2, pf.getNameCustomer());
        st.setString(3, pf.getCPF());;
        st.setString(4, pf.getRG());
        st.setDate(5, pf.getDateOfBirth());
        st.setInt(6, pf.getIdCustomer());
        st.execute();
        st.close();
        con.close();
         
    }

    //Listing PessoaFisica customer
    
}