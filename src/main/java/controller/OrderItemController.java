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

import dao.OrderItemDao;
import model.OrderItem;

public class OrderItemController extends HttpServlet{

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
            
            if (req.getParameter("orderIdOrder") == null || req.getParameter("orderIdOrder").equals("")) {
                parameter = 0;
            } else {
                parameter = Integer.parseInt(req.getParameter("orderIdOrder"));
            }         

            OrderItemDao d = new OrderItemDao();
            List<OrderItem> lst;

            if (parameter == 0) {
                lst = d.searchOrderItem();
            } else {
                lst = d.searchOrderIdOrder(parameter);
            }
            
            n = lst.size();

            if (n == 0) {
                saida.println("[ { \"result\" : \"Não há resultado\" } ]");
            } else {
                saida.println("[");

                for (OrderItem selectOrderItem : lst) {
         
                    String strOrderItem = new Gson().toJson(selectOrderItem);

                    if(n == 1) {
                        saida.printf(strOrderItem + "]");
                    }else 
                        saida.println(strOrderItem + ",");
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
                req.getParameter("amount") == null || req.getParameter("amount").equals("") ||
                req.getParameter("productIdProduct") == null || req.getParameter("productIdProduct").equals("") ||
                req.getParameter("orderIdOrder") == null || req.getParameter("orderIdOrder").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                OrderItem inOrderItem = new OrderItem();
            
                inOrderItem.setAmount(Integer.parseInt(req.getParameter("amount")));
                inOrderItem.setProductIdProduct(Integer.parseInt(req.getParameter("productIdProduct")));
                inOrderItem.setOrderIdItem(Integer.parseInt(req.getParameter("orderIdOrder")));

                OrderItemDao d = new OrderItemDao();

                int ok = d.registerOrderItem(inOrderItem);

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
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH QUERY FROM DB
        try {
            // criar validação de usuário.
            //
            //
            
            if (req.getParameter("idOrderItem") == null || req.getParameter("idOrderItem").equals("")) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                
                OrderItemDao d = new OrderItemDao();

                int ok = d.deleteOrderItem(Integer.parseInt(req.getParameter("idOrderItem")));

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
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        //CREATE JSON WITH db RESULT OF TRANSATION
        try {
            // criar validação de usuário.
            //
            //
            if (
                req.getParameter("idOrderItem") == null || req.getParameter("idOrderItem").equals("") ||
                req.getParameter("amount") == null || req.getParameter("amount").equals("") ||
                req.getParameter("productIdProduct") == null || req.getParameter("productIdProduct").equals("") ||
                req.getParameter("orderIdOrder") == null || req.getParameter("orderIdOrder").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");

            } else {
                OrderItem upOrderItem = new OrderItem();
            
                upOrderItem.setIdOrderItem(Integer.parseInt(req.getParameter("idOrderItem")));
                upOrderItem.setAmount(Integer.parseInt(req.getParameter("amount")));
                upOrderItem.setProductIdProduct(Integer.parseInt(req.getParameter("productIdProduct")));
                upOrderItem.setOrderIdItem(Integer.parseInt(req.getParameter("orderIdOrder")));

                OrderItemDao d = new OrderItemDao();

                int ok = d.updateOrderItem(upOrderItem);

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