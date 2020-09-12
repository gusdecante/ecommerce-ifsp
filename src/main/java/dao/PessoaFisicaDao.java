package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PessoaFisica;

import util.MysqlConnection;

public class PessoaFisicaDao {

    private Connection con;

    //Mysql connection created
    public PessoaFisicaDao() {
        con = new MysqlConnection().getConnection();
    }

    //Insert register on the mysql table
    public int registerPessoaFisica(PessoaFisica i) {
        int ok = 0;
        try {
            String query = "INSERT INTO pessoa_Fisica (id_Pessoa_Fisica, name_Customer, CPF, RG, date_Birth, customer_id_Customer) VALUES (?, ?, ?, ?, ?, ?) ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getNameCustomer());
            st.setString(3, i.getCPF());
            st.setString(4, i.getRG());
            st.setString(5, i.getDateBirth());
            st.setInt(6, i.getCustomerIdCustomer());

            ok = st.executeUpdate(); //Execute the insert
            st.close(); //Close the Statment
            con.close(); //Close the connection
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ok;
    }

    //Select register on the mysql table
    public List<PessoaFisica> searchPessoaFisica() throws SQLException, Exception {

        List<PessoaFisica> lista = new ArrayList<PessoaFisica>();
        String query = "SELECT pessoa_Fisica.*, customer.*, user.* FROM pessoa_Fisica, customer, user WHERE pessoa_Fisica.customer_id_Customer = customer.id_Customer AND customer.user_id_User = user.id_User ;";
        
        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            PessoaFisica pf = new PessoaFisica();

            //Pessoa_Fisica table
            pf.setIdPessoaFisica(rs.getInt("id_Pessoa_Fisica"));
            pf.setNameCustomer(rs.getString("name_Customer"));
            pf.setCPF(rs.getString("CPF"));
            pf.setRG(rs.getString("RG"));
            pf.setDateBirth(rs.getString("date_Birth"));
            pf.setCustomerIdCustomer(rs.getInt("pessoa_Fisica.customer_id_Customer"));
            //customer table
            pf.setIdCustomer(rs.getInt("id_Customer"));
            pf.setUserIdUser(rs.getInt("user_id_User"));
            //user table
            pf.setIdUser(rs.getInt("id_User"));
            pf.setEmail(rs.getString("email"));
            pf.setPassword(rs.getString("password"));
            pf.setUserType(rs.getInt("user_Type"));
            pf.setToken(rs.getString("token"));

            lista.add(pf);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public int updatePessoaFisica(PessoaFisica u) {
        int ok = 0;
        try {
            String query = "UPDATE pessoa_Fisica SET name_Customer = ?, CPF = ?, RG = ?, date_Birth = ?, customer_id_Customer = ? WHERE id_Pessoa_Fisica = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<PessoaFisica> l = new PessoaFisicaDao().searchPessoaFisica(u.getIdPessoaFisica());
                
            for (PessoaFisica lc : l) {
                st.setInt(6, lc.getIdPessoaFisica());
            }
            st.setString(1, u.getNameCustomer());
            st.setString(2, u.getCPF());
            st.setString(3, u.getRG());
            st.setString(4, u.getDateBirth());
            st.setInt(5, u.getCustomerIdCustomer());

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
    public int deletePessoaFisica(int d) {
        int ok = 0;
        try {
            String query = "DELETE FROM pessoa_Fisica WHERE id_Pessoa_Fisica = ?;";

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
    public List<PessoaFisica> searchPessoaFisica(int idUser) throws SQLException, Exception {

        List<PessoaFisica> lista = new ArrayList<PessoaFisica>();
        String query = "SELECT pessoa_Fisica.*, customer.*, user.* FROM pessoa_Fisica, customer, user WHERE id_User = ? AND customer.user_id_User = user.id_User AND pessoa_Fisica.customer_id_Customer = customer.id_Customer ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, idUser);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            PessoaFisica pf = new PessoaFisica();

            //Pessoa_Fisica table
            pf.setIdPessoaFisica(rs.getInt("id_Pessoa_Fisica"));
            pf.setNameCustomer(rs.getString("name_Customer"));
            pf.setCPF(rs.getString("CPF"));
            pf.setRG(rs.getString("RG"));
            pf.setDateBirth(rs.getString("date_Birth"));
            pf.setCustomerIdCustomer(rs.getInt("pessoa_Fisica.customer_id_Customer"));
            //customer table
            pf.setIdCustomer(rs.getInt("id_Customer"));
            pf.setUserIdUser(rs.getInt("user_id_User"));
            //user table
            pf.setIdUser(rs.getInt("id_User"));
            pf.setEmail(rs.getString("email"));
            pf.setPassword(rs.getString("password"));
            pf.setUserType(rs.getInt("user_Type"));
            pf.setToken(rs.getString("token"));


            lista.add(pf);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}