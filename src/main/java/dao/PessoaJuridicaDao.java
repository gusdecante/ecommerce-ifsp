package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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