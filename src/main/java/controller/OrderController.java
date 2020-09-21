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

import dao.OrderDao;
import model.Order;

public class OrderController extends HttpServlet{

    private static final long serialVersionUID = 4L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("application/json");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH QUERY FROM DB
        try {
            int parameter, n = 0;
            
            if (req.getParameter("idOrder") == null || req.getParameter("idOrder").equals("")) {
                parameter = 0;
            } else {
                parameter = Integer.parseInt(req.getParameter("idOrder"));
            }         

            OrderDao d = new OrderDao();
            List<Order> lst;

            if (parameter == 0) {
                lst = d.searchOrder();
            } else {
                lst = d.searchOrder(parameter);
            }
            
            n = lst.size();

            if (n == 0) {
                saida.println("[ { \"result\" : \"Não há resultado\" } ]");
            } else {
                saida.println("[");

                for (Order selectOrder : lst) {
         
                    String strOrder = new Gson().toJson(selectOrder);

                    if(n == 1) {
                        saida.printf(strOrder + "]");
                    }else 
                        saida.println(strOrder + ",");
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
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH db RESULT OF TRANSATION
        try {
            // criar validação de usuário.
            //
            //
            if (
                req.getParameter("date") == null || req.getParameter("date").equals("") ||
                req.getParameter("paymentFormIdPaymentForm") == null || req.getParameter("paymentFormIdPaymentForm").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                Order inOrder = new Order();
            
                inOrder.setDate(req.getParameter("date"));
                inOrder.setPaymentFormIdPaymentForm(Integer.parseInt(req.getParameter("paymentFormIdPaymentForm")));

                OrderDao d = new OrderDao();

                int ok = d.registerOrder(inOrder);

                if(ok >= 1) {
                    if (ok > 1)
                        saida.println("[ { \"result\" : \"Dados inseridos com sucesso\", \"last id\":_"+(ok-1)+"} ]");
                    else 
                        saida.println("[ { \"result\" : \"Dados inseridos com sucesso\" } ]");
                } else
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
            
            if (req.getParameter("idOrder") == null || req.getParameter("idOrder").equals("")) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                
                OrderDao d = new OrderDao();

                int ok = d.deleteOrder(Integer.parseInt(req.getParameter("idOrder")));

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
                req.getParameter("idOrder") == null || req.getParameter("idOrder").equals("") ||
                req.getParameter("date") == null || req.getParameter("date").equals("") ||
                req.getParameter("paymentFormIdPaymentForm") == null || req.getParameter("paymentFormIdPaymentForm").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");

            } else {
                Order upOrder = new Order();
            
                upOrder.setIdOrder(Integer.parseInt(req.getParameter("idOrder")));
                upOrder.setDate(req.getParameter("date"));
                upOrder.setPaymentFormIdPaymentForm(Integer.parseInt(req.getParameter("paymentFormIdPaymentForm")));

                OrderDao d = new OrderDao();

                int ok = d.updateOrder(upOrder);

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