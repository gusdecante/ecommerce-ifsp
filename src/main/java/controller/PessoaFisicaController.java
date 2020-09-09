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

import dao.PessoaFisicaDao;
import model.PessoaFisica;

public class PessoaFisicaController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH QUERY FROM DB
        try {
            int parameter, n = 0;
            
            if (req.getParameter("idPessoaFisica") == null || req.getParameter("idPessoaFisica").equals("")) {
                parameter = 0;
            } else {
                parameter = Integer.parseInt(req.getParameter("idPessoaFisica"));
            }         

            PessoaFisicaDao d = new PessoaFisicaDao();
            List<PessoaFisica> lst;

            if (parameter == 0) {
                lst = d.searchPessoaFisica();
            } else {
                lst = d.searchPessoaFisica(parameter);
            }
            
            n = lst.size();

            if (n == 0) {
                saida.println("[ { \"result\" : \"Não há resultado\" } ]");
            } else {
                saida.println("[");

                for (PessoaFisica selectPessoaFisica : lst) {
         
                    String strPessoaFisica = new Gson().toJson(selectPessoaFisica);

                    if(n == 1) {
                        saida.printf(strPessoaFisica + "]");
                    }else 
                        saida.println(strPessoaFisica + ",");
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
                req.getParameter("nameCustomer") == null || req.getParameter("nameCustomer").equals("") ||
                req.getParameter("CPF") == null || req.getParameter("CPF").equals("") ||
                req.getParameter("RG") == null || req.getParameter("RG").equals("") ||
                req.getParameter("dateBirth") == null || req.getParameter("dateBirth").equals("") ||
                req.getParameter("customerIdCustomer") == null || req.getParameter("customerIdCustomer").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                PessoaFisica inPessoaFisica = new PessoaFisica();
            
                inPessoaFisica.setNameCustomer(req.getParameter("nameCustomer"));
                inPessoaFisica.setCPF(req.getParameter("CPF"));
                inPessoaFisica.setRG(req.getParameter("RG"));
                inPessoaFisica.setDateBirth(req.getParameter("dateBirth"));
                inPessoaFisica.setCustomerIdCustomer(Integer.parseInt(req.getParameter("customerIdCustomer")));

                PessoaFisicaDao d = new PessoaFisicaDao();

                int ok = d.registerPessoaFisica(inPessoaFisica);

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
            
            if (req.getParameter("idPessoaFisica") == null || req.getParameter("idPessoaFisica").equals("")) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                
                PessoaFisicaDao d = new PessoaFisicaDao();

                int ok = d.deletePessoaFisica(Integer.parseInt(req.getParameter("idPessoaFisica")));

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
                req.getParameter("idPessoaFisica") == null || req.getParameter("idPessoaFisica").equals("") ||
                req.getParameter("nameCustomer") == null || req.getParameter("nameCustomer").equals("") ||
                req.getParameter("CPF") == null || req.getParameter("CPF").equals("") ||
                req.getParameter("RG") == null || req.getParameter("RG").equals("") ||
                req.getParameter("dateBirth") == null || req.getParameter("dateBirth").equals("") ||
                req.getParameter("customerIdCustomer") == null || req.getParameter("customerIdCustomer").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");

            } else {
                PessoaFisica upPessoaFisica = new PessoaFisica();
            
                upPessoaFisica.setIdPessoaFisica(Integer.parseInt(req.getParameter("idPessoaFisica")));
                upPessoaFisica.setNameCustomer(req.getParameter("nameCustomer"));
                upPessoaFisica.setCPF(req.getParameter("CPF"));
                upPessoaFisica.setRG(req.getParameter("RG"));
                upPessoaFisica.setDateBirth(req.getParameter("dateBirth"));
                upPessoaFisica.setCustomerIdCustomer(Integer.parseInt(req.getParameter("customerIdCustomer")));

                PessoaFisicaDao d = new PessoaFisicaDao();

                int ok = d.updatePessoaFisica(upPessoaFisica);

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