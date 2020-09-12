package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.PessoaJuridicaDao;
import model.PessoaJuridica;

public class PessoaJuridicaController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH QUERY FROM DB
        try {
            int parameter, n = 0;
            
            if (req.getParameter("idUser") == null || req.getParameter("idUser").equals("")) {
                parameter = 0;
            } else {
                parameter = Integer.parseInt(req.getParameter("idUser"));
            }         

            PessoaJuridicaDao d = new PessoaJuridicaDao();
            List<PessoaJuridica> lst;

            if (parameter == 0) {
                lst = d.searchPessoaJuridica();
            } else {
                lst = d.searchPessoaJuridica(parameter);
            }
            
            n = lst.size();

            if (n == 0) {
                saida.println("[ { \"result\" : \"Não há resultado\" } ]");
            } else {
                saida.println("[");

                for (PessoaJuridica selectPessoaJuridica : lst) {
         
                    String strPessoaJuridica = new Gson().toJson(selectPessoaJuridica);

                    if(n == 1) {
                        saida.printf(strPessoaJuridica + "]");
                    }else 
                        saida.println(strPessoaJuridica + ",");
                    n--;
                }
            
            }
        
        } catch (SQLException e) {
            saida.println("[ { \"result\" : \"Erro S " + e.getMessage() + "\" } ]");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            saida.println("[ { \"result\" : \"Erro N " + e.getMessage() + "\" } ]");
        } catch (Exception e) {
            e.printStackTrace();
            saida.println("[ { \"result\" : \"Erro E " + e.getMessage() + "\" } ]");
        }

        saida.flush();
        saida.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH db RESULT OF TRANSATION
        try {
            // criar validação de usuário.
            //
            //
            if (
                req.getParameter("CNPJ") == null || req.getParameter("CNPJ").equals("") ||
                req.getParameter("razaoSocial") == null || req.getParameter("razaoSocial").equals("") ||
                req.getParameter("customerIdCustomer") == null || req.getParameter("customerIdCustomer").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                PessoaJuridica inPessoaJuridica = new PessoaJuridica();
            
                inPessoaJuridica.setCnpj(req.getParameter("CNPJ"));
                inPessoaJuridica.setRazaoSocial(req.getParameter("razaoSocial"));
                inPessoaJuridica.setCustomerIdCustomer(Integer.parseInt(req.getParameter("customerIdCustomer")));

                PessoaJuridicaDao d = new PessoaJuridicaDao();

                int ok = d.registerPessoaJuridica(inPessoaJuridica);

                if(ok == 1)
                    saida.println("[ { \"result\" : \"Dados inseridos com sucesso\" } ]");
                else
                    saida.println("[ { \"result\" : \"Falha na inserção de dados\" } ]");
            }                        

        } catch (NumberFormatException e) {
            e.printStackTrace();
            saida.println("[ { \"result\" : \"Erro N " + e.getMessage() + "\" } ]");
        } catch (Exception e) {
            e.printStackTrace();
            saida.println("[ { \"result\" : \"Erro E " + e.getMessage() + "\" } ]");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH QUERY FROM DB
        try {
            // criar validação de usuário.
            //
            //
            
            if (req.getParameter("idPessoaJuridica") == null || req.getParameter("idPessoaJuridica").equals("")) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                
                PessoaJuridicaDao d = new PessoaJuridicaDao();

                int ok = d.deletePessoaJuridica(Integer.parseInt(req.getParameter("idPessoaJuridica")));

                if(ok == 1)
                    saida.println("[ { \"result\" : \"Dado excluido com sucesso\" } ]");
                else
                    saida.println("[ { \"result\" : \"Falha na exclusão\" } ]");
            }
            
        } catch (NumberFormatException e) {
            e.printStackTrace();
            saida.println("[ { \"result\" : \"Erro N " + e.getMessage() + "\" } ]");
        } catch (Exception e) {
            e.printStackTrace();
            saida.println("[ { \"result\" : \"Erro E " + e.getMessage() + "\" } ]");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH db RESULT OF TRANSATION
        try {
            // criar validação de usuário.
            //
            //
            if (
                req.getParameter("idPessoaJuridica") == null || req.getParameter("idPessoaJuridica").equals("") ||
                req.getParameter("CNPJ") == null || req.getParameter("CNPJ").equals("") ||
                req.getParameter("razaoSocial") == null || req.getParameter("razaoSocial").equals("") ||
                req.getParameter("customerIdCustomer") == null || req.getParameter("customerIdCustomer").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");

            } else {
                PessoaJuridica upPessoaJuridica = new PessoaJuridica();
            
                upPessoaJuridica.setIdPessoaJuridica(Integer.parseInt(req.getParameter("idPessoaJuridica")));
                upPessoaJuridica.setCnpj(req.getParameter("CNPJ"));
                upPessoaJuridica.setRazaoSocial(req.getParameter("razaoSocial"));
                upPessoaJuridica.setCustomerIdCustomer(Integer.parseInt(req.getParameter("customerIdCustomer")));
                
                PessoaJuridicaDao d = new PessoaJuridicaDao();

                int ok = d.updatePessoaJuridica(upPessoaJuridica);

                if(ok == 1)
                    saida.println("[ { \"result\" : \"Dados atualizados com sucesso\" } ]");
                else
                    saida.println("[ { \"result\" : \"Falha na atualização de dados\" } ]");
            }                        

        } catch (NumberFormatException e) {
            e.printStackTrace();
            saida.println("[ { \"result\" : \"Erro N " + e.getMessage() + "\" } ]");
        } catch (Exception e) {
            e.printStackTrace();
            saida.println("[ { \"result\" : \"Erro E " + e.getMessage() + "\" } ]");
        }

    }

}