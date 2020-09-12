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

import dao.UserDao;
import model.User;

public class UserController extends HttpServlet{

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

            UserDao d = new UserDao();
            List<User> lst;

            if (parameter == 0) {
                lst = d.searchUser();
            } else {
                lst = d.searchUser(parameter);
            }
            
            n = lst.size();

            if (n == 0) {
                saida.println("[ { \"result\" : \"Não há resultado\" } ]");
            } else {
                saida.println("[");

                for (User selectUser : lst) {
         
                    String strUser = new Gson().toJson(selectUser);

                    if(n == 1) {
                        saida.printf(strUser + "]");
                    }else 
                        saida.println(strUser + ",");
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
                req.getParameter("email") == null || req.getParameter("email").equals("") ||
                req.getParameter("password") == null || req.getParameter("password").equals("") ||
                req.getParameter("userType") == null || req.getParameter("userType").equals("") ||
                req.getParameter("token") == null || req.getParameter("token").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                User inUser = new User();
             
                inUser.setEmail(req.getParameter("email"));
                inUser.setPassword(req.getParameter("password"));
                inUser.setUserType(Integer.parseInt(req.getParameter("userType")));
                inUser.setToken(req.getParameter("token"));

                UserDao d = new UserDao();

                int ok = d.registerUser(inUser);

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
            
            if (req.getParameter("idUser") == null || req.getParameter("idUser").equals("")) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                
                UserDao d = new UserDao();

                int ok = d.deleteUser(Integer.parseInt(req.getParameter("idUser")));

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
                req.getParameter("idUser") == null || req.getParameter("idUser").equals("") ||
                req.getParameter("email") == null || req.getParameter("email").equals("") ||
                req.getParameter("password") == null || req.getParameter("password").equals("") ||
                req.getParameter("userType") == null || req.getParameter("userType").equals("") ||
                req.getParameter("token") == null || req.getParameter("token").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");

            } else {
                User upUser = new User();
            
                upUser.setIdUser(Integer.parseInt(req.getParameter("idUser")));
                upUser.setEmail(req.getParameter("email"));
                upUser.setPassword(req.getParameter("password"));
                upUser.setUserType(Integer.parseInt(req.getParameter("userType")));
                upUser.setToken(req.getParameter("token"));

                UserDao d = new UserDao();

                int ok = d.updateUser(upUser);

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