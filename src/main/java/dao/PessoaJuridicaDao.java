package dao;

import java.sql.Connection;

import util.MysqlConection;

public class PessoaJuridicaDao {
    private Connection con;

    public PessoaJuridicaDao() {
        con = new MysqlConection().getConnection();
    }
}