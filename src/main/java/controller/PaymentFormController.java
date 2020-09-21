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

import dao.PaymentFormDao;
import model.PaymentForm;

public class PaymentFormController extends HttpServlet{

    private static final long serialVersionUID = 6L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH QUERY FROM DB
        try {
            int parameter, n = 0;
            
            if (req.getParameter("idPaymentForm") == null || req.getParameter("idPaymentForm").equals("")) {
                parameter = 0;
            } else {
                parameter = Integer.parseInt(req.getParameter("idPaymentForm"));
            }         

            PaymentFormDao d = new PaymentFormDao();
            List<PaymentForm> lst;

            if (parameter == 0) {
                lst = d.searchPaymentForm();
            } else {
                lst = d.searchPaymentForm(parameter);
            }
            
            n = lst.size();

            if (n == 0) {
                saida.println("[ { \"result\" : \"Não há resultado\" } ]");
            } else {
                saida.println("[");

                for (PaymentForm selectPaymentForm : lst) {
         
                    String strPaymentForm = new Gson().toJson(selectPaymentForm);

                    if(n == 1) {
                        saida.printf(strPaymentForm + "]");
                    }else 
                        saida.println(strPaymentForm + ",");
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
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH db RESULT OF TRANSATION
        try {
            // criar validação de usuário.
            //
            //
            if (
                req.getParameter("paymentForm") == null || req.getParameter("paymentForm").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                PaymentForm inPaymentForm = new PaymentForm();
            
                inPaymentForm.setPaymentForm(req.getParameter("paymentForm"));

                PaymentFormDao d = new PaymentFormDao();

                int ok = d.registerPaymentForm(inPaymentForm);

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
                
        saida.flush();
        saida.close();

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH QUERY FROM DB
        try {
            // criar validação de usuário.
            //
            //
            
            if (req.getParameter("idPaymentForm") == null || req.getParameter("idPaymentForm").equals("")) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                
                PaymentFormDao d = new PaymentFormDao();

                int ok = d.deletePaymentForm(Integer.parseInt(req.getParameter("idPaymentForm")));

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
                
        saida.flush();
        saida.close();

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH db RESULT OF TRANSATION
        try {
            // criar validação de usuário.
            //
            //
            if (
                req.getParameter("idPaymentForm") == null || req.getParameter("idPaymentForm").equals("") ||
                req.getParameter("paymentForm") == null || req.getParameter("paymentForm").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");

            } else {
                PaymentForm upPaymentForm = new PaymentForm();
            
                upPaymentForm.setIdPaymentForm(Integer.parseInt(req.getParameter("idPaymentForm")));
                upPaymentForm.setPaymentForm(req.getParameter("paymentForm"));

                PaymentFormDao d = new PaymentFormDao();

                int ok = d.updatePaymentForm(upPaymentForm);

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
                
        saida.flush();
        saida.close();

    }

}