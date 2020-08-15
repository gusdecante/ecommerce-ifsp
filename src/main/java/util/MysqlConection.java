package util;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

public class MysqlConection {
    
    private Connection conexao = null;
    private final String HOST = "localhost";
    private final String BANCO = "ecommerceDB";
    private final String LOGIN = "gerencia";
    private final String SENHA = "9eq3y%NhPG83o9fLZ%xL";
    

    public Connection getConnection() {

        try {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setServerName(HOST);
            ds.setDatabaseName(BANCO);
            ds.setUser(LOGIN);
            ds.setPassword(SENHA);
            ds.setServerTimezone("UTC");

            ds.setConnectTimeout(2000);

            conexao = ds.getConnection();
            System.out.println("CONECTADO AO BANCO DE DADOS.");
            
        } catch (SQLException sqle) {
            System.out.println("FALHA NA CONEXÃO: " + sqle.getMessage());
        }

        return conexao;
    }

}