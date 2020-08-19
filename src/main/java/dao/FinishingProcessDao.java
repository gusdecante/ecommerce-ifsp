package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.FinishingProcess;

import util.MysqlConection;

public class FinishingProcessDao {

    private Connection con;

    //Mysql connection created
    public FinishingProcessDao() {
        con = new MysqlConection().getConnection();
    }

    //Insert register on the mysql table
    public boolean registerFinishingProcess(FinishingProcess i) {
        boolean isSuccess = false;
        try {
            String query = "INSERT INTO finishing_Process (id_Finishing, finishing_Process) VALUES (?, ?);";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query

            st.setInt(1, 0);
            st.setString(2, i.getFinishingProcess());

            st.executeUpdate(); //Execute the insert
            st.close(); //Close the Statment
            con.close(); //Close the connection
            isSuccess = true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        }
        
        return isSuccess;
    }

    //Select register on the mysql table
    public List<FinishingProcess> searchFinishingProcess() throws SQLException, Exception {

        List<FinishingProcess> lista = new ArrayList();
        String query = "SELECT * FROM finishing_Process ;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            FinishingProcess fp = new FinishingProcess();

            fp.setIdFinishing(rs.getInt("id_FinishingProcess"));
            fp.setFinishingProcess(rs.getString("finishing_Process"));

            lista.add(fp);
        }
        
        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }

    //Update register on the mysql table
    public boolean updateFinishingProcess(FinishingProcess u) {
        boolean isSuccess = false;
        try {
            String query = "UPDATE finishing_Process SET id_Finishing = ?, finishing_Process = ?;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            //Select id informated 
            List<FinishingProcess> l = new FinishingProcessDao().searchFinishingProcess(u.getIdFinishing());
                
            for (FinishingProcess lc : l) {
                st.setInt(1, lc.getIdFinishing());
            }
            st.setString(2, u.getFinishingProcess());

            st.executeUpdate(); //Execute the update
            st.close(); //Close the Statment
            con.close(); //Close the connection

            isSuccess = true;

        } catch (SQLException e) {
            e.printStackTrace();
            isSuccess = false;
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }

    //delete register on the mysql table
    public boolean deleteFinishingProcess(int d) {
        boolean isSuccess = false;
        try {
            String query = "DELETE FROM finishing_Process WHERE id_Finishing = ?;";

            PreparedStatement st = con.prepareStatement(query); //Prepared the query
            st.setInt(1, d);

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

    //Select specifical register on the mysql table
    public List<FinishingProcess> searchFinishingProcess(int idFinishingProcess) throws SQLException, Exception {

        List<FinishingProcess> lista = new ArrayList();
        String query = "SELECT * FROM finishing_Process WHERE id_Finishing = ?;";

        PreparedStatement st = con.prepareStatement(query); //Prepared the query
        st.setInt(2, idFinishingProcess);

        ResultSet rs = st.executeQuery(); //Execute the select

        while(rs.next()) {
            FinishingProcess fp = new FinishingProcess();

            fp.setIdFinishing(rs.getInt("id_Finishing"));
            fp.setFinishingProcess(rs.getString("finishing_Process"));

            lista.add(fp);
           
        }

        st.close(); //Close the Statment
        con.close(); //Close the connection

        return lista;
    }
    
}