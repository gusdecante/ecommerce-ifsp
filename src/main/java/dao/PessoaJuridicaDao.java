package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PessoaJuridica;

import util.MysqlConnection;

public class PessoaJuridicaDao {

    private Connection con;

    //Mysql connection created
    public PessoaJuridicaDao() {
        con = new MysqlConnection().getConnection();
    }

    //Insert register on the mysql table
    public int registerPessoaJuridica(PessoaJuridica i) {
        int ok = 0;
        try {
            String query = "INSERT INTO pessoa_Juridica (id_Pessoa_Juridica, CNPJ, razao_Social, customer_id_Customer) VALUES (?, ?, ?, ?) ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getCnpj());
            st.setString(3, i.getRazaoSocial());
            st.setInt(4, i.getCustomerIdCustomer());

            ok = st.executeUpdate(); //Execute the insert
            st.close(); //Close the Statment
            con.close(); //Close the connection
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ok;
    }

    //Select register on the mysql table
    public List<PessoaJuridica> searchPessoaJuridica() throws SQLException, Exception {

        List<PessoaJuridica> lista = new ArrayList<PessoaJuridica>();
        String query = "SELECT pessoa_Juridica.*, customer.*, user.* FROM pessoa_Juridica, customer, user WHERE pessoa_Juridica.customer_id_Customer = customer.id_Customer AND customer.user_id_User = user.id_User ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            PessoaJuridica pj = new PessoaJuridica();

            pj.setIdPessoaJuridica(rs.getInt("id_Pessoa_Juridica"));
            pj.setCnpj(rs.getString("CNPJ"));
            pj.setRazaoSocial(rs.getString("razao_Social"));
            pj.setCustomerIdCustomer(rs.getInt("customer_id_Customer"));
            //customer table
            pj.setIdCustomer(rs.getInt("id_Customer"));
            pj.setUserIdUser(rs.getInt("user_id_User"));
            //user table
            pj.setIdUser(rs.getInt("id_User"));
            pj.setEmail(rs.getString("email"));
            pj.setPassword(rs.getString("password"));
            pj.setUserType(rs.getInt("user_Type"));
            pj.setToken(rs.getString("token"));

            lista.add(pj);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public int updatePessoaJuridica(PessoaJuridica u) {
        int ok = 0;
        try {
            String query = "UPDATE pessoa_Juridica SET CNPJ = ?, razao_Social = ?, customer_id_Customer = ? WHERE id_Pessoa_Juridica = ? ;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<PessoaJuridica> l = new PessoaJuridicaDao().searchPessoaJuridica(u.getIdPessoaJuridica());
                
            for (PessoaJuridica lc : l) {
                st.setInt(4, lc.getIdPessoaJuridica());
            }
            st.setString(1, u.getCnpj());
            st.setString(2, u.getRazaoSocial());
            st.setInt(3, u.getCustomerIdCustomer());

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
    public int deletePessoaJuridica(int d) {
        int ok = 0;
        try {
            String query = "DELETE FROM pessoa_Juridica WHERE id_Pessoa_Juridica = ?;";

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
    public List<PessoaJuridica> searchPessoaJuridica(int idUser) throws SQLException, Exception {

        List<PessoaJuridica> lista = new ArrayList<PessoaJuridica>();
        String query = "SELECT pessoa_Juridica.*, customer.*, user.* FROM pessoa_Juridica, customer, user WHERE id_User = ? AND customer.user_id_User = user.id_User AND pessoa_Juridica.customer_id_Customer = customer.id_Customer ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(1, idUser);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            PessoaJuridica pj = new PessoaJuridica();

            pj.setIdPessoaJuridica(rs.getInt("id_Pessoa_Juridica"));
            pj.setCnpj(rs.getString("CNPJ"));
            pj.setRazaoSocial(rs.getString("razao_Social"));
            pj.setCustomerIdCustomer(rs.getInt("customer_id_Customer"));
            //customer table
            pj.setIdCustomer(rs.getInt("id_Customer"));
            pj.setUserIdUser(rs.getInt("user_id_User"));
            //user table
            pj.setIdUser(rs.getInt("id_User"));
            pj.setEmail(rs.getString("email"));
            pj.setPassword(rs.getString("password"));
            pj.setUserType(rs.getInt("user_Type"));
            pj.setToken(rs.getString("token"));

            lista.add(pj);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}