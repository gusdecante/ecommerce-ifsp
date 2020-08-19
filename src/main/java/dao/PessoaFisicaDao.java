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
    public void registerPessoaFisica(PessoaFisica p) throws SQLException {
        
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

        //Third - Insert Pessoa Física 
        query = "INSERT INTO pessoa_Fisica(id_Pessoa_Fisica, name_Customer, CPF, RG, date_Birth, customer_id_Customer) VALUES (?, ?, ?, ?, ?, ?)";
        st = con.prepareStatement(query);
        st.setInt(1, p.getIdPessoaFisica());
        st.setString(2, p.getNameCustomer());
        st.setString(3, p.getCPF());;
        st.setString(4, p.getRG());
        st.setDate(5, p.getDateOfBirth());
        st.setInt(6, p.getIdCustomer());
        st.execute();
        st.close();
        con.close();
         
    }
}