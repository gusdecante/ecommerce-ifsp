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

import dao.AddressDao;
import model.Address;

public class AddressController extends HttpServlet{

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
            //Select addresses by customer
            if (req.getParameter("customerIdCustomer") == null || req.getParameter("customerIdCustomer").equals("")) {
                parameter = 0;
            } else {
                parameter = Integer.parseInt(req.getParameter("customerIdCustomer"));
            }         

            AddressDao d = new AddressDao();
            List<Address> lst;

            if (parameter == 0) {
                lst = d.searchAddress();
            } else {
                lst = d.searchAddressCustomer(parameter);
            }
            
            n = lst.size();

            if (n == 0) {
                saida.println("[ { \"result\" : \"Não há resultado\" } ]");
            } else {
                saida.println("[");

                for (Address selectAddress : lst) {
         
                    String strAddress = new Gson().toJson(selectAddress);

                    if(n == 1) {
                        saida.printf(strAddress + "]");
                    }else 
                        saida.println(strAddress + ",");
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
                req.getParameter("street") == null || req.getParameter("street").equals("") ||
                req.getParameter("number") == null || req.getParameter("number").equals("") ||
                req.getParameter("district") == null || req.getParameter("district").equals("") ||
                req.getParameter("city") == null || req.getParameter("city").equals("") ||
                req.getParameter("state") == null || req.getParameter("state").equals("") ||
                req.getParameter("zipCode") == null || req.getParameter("zipCode").equals("") ||
                req.getParameter("customerIdCustomer") == null || req.getParameter("customerIdCustomer").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                Address inAddress = new Address();
            
                inAddress.setStreet(req.getParameter("street"));
                inAddress.setNumber(req.getParameter("number"));
                inAddress.setDistrict(req.getParameter("district"));
                inAddress.setCity(req.getParameter("city"));
                inAddress.setState(req.getParameter("state"));
                inAddress.setZipCode(req.getParameter("zipCode"));
                inAddress.setCustomerIdCustomer(Integer.parseInt(req.getParameter("customerIdCustomer")));

                AddressDao d = new AddressDao();

                int ok = d.registerAddress(inAddress);

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
            
            if (req.getParameter("idAddress") == null || req.getParameter("idAddress").equals("")) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                
                AddressDao d = new AddressDao();

                int ok = d.deleteAddress(Integer.parseInt(req.getParameter("idAddress")));

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
                req.getParameter("idAddress") == null || req.getParameter("idAddress").equals("") ||
                req.getParameter("street") == null || req.getParameter("street").equals("") ||
                req.getParameter("number") == null || req.getParameter("number").equals("") ||
                req.getParameter("district") == null || req.getParameter("district").equals("") ||
                req.getParameter("city") == null || req.getParameter("city").equals("") ||
                req.getParameter("state") == null || req.getParameter("state").equals("") ||
                req.getParameter("zipCode") == null || req.getParameter("zipCode").equals("") ||
                req.getParameter("customerIdCustomer") == null || req.getParameter("customerIdCustomer").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");

            } else {
                Address upAddress = new Address();
            
                upAddress.setIdAddress(Integer.parseInt(req.getParameter("idAddress")));
                upAddress.setStreet(req.getParameter("street"));
                upAddress.setNumber(req.getParameter("number"));
                upAddress.setDistrict(req.getParameter("district"));
                upAddress.setCity(req.getParameter("city"));
                upAddress.setState(req.getParameter("state"));
                upAddress.setZipCode(req.getParameter("zipCode"));
                upAddress.setCustomerIdCustomer(Integer.parseInt(req.getParameter("customerIdCustomer")));

                AddressDao d = new AddressDao();

                int ok = d.updateAddress(upAddress);

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