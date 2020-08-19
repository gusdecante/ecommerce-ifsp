package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.PessoaJuridica;
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
        query = "INSERT INT pessoa_Juridica(id_Pessoa_Juridica, CNPJ, razao_Social, customer_id_Customer) VALUES(?, ?, ?, ?)";
        st = con.prepareStatement(query);
        st.setInt(1, p.getIdPessoaJuridica());
        st.setString(2, p.getCnpj());
        st.setString(3, p.getRazaoSocial());
        st.setInt(4, p.getIdCustomer());
        st.execute();
        st.close();
        con.close();
    }
}