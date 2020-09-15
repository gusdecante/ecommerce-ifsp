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
import dao.CustomerDao;
import dao.PessoaJuridicaDao;
import dao.PhoneDao;
import dao.UserDao;
import model.Address;
import model.Customer;
import model.PessoaJuridica;
import model.Phone;
import model.User;

public class PessoaJuridicaController extends HttpServlet{

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
        resp.setHeader("Access-Control-Allow-Origin", "*");
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
                req.getParameter("token") == null || req.getParameter("token").equals("") ||
                //Pessoa Juridica
                req.getParameter("CNPJ") == null || req.getParameter("CNPJ").equals("") ||
                req.getParameter("razaoSocial") == null || req.getParameter("razaoSocial").equals("") ||
                //Address
                req.getParameter("street") == null || req.getParameter("street").equals("") ||
                req.getParameter("number") == null || req.getParameter("number").equals("") ||
                req.getParameter("district") == null || req.getParameter("district").equals("") ||
                req.getParameter("city") == null || req.getParameter("city").equals("") ||
                req.getParameter("state") == null || req.getParameter("state").equals("") ||
                req.getParameter("zipCode") == null || req.getParameter("zipCode").equals("") ||
                //Phone
                req.getParameter("phone") == null || req.getParameter("phone").equals("")

            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                //Insert data in User
                User inUser = new User();
             
                inUser.setEmail(req.getParameter("email"));
                inUser.setPassword(req.getParameter("password"));
                inUser.setUserType(Integer.parseInt(req.getParameter("userType")));
                inUser.setToken(req.getParameter("token"));

                UserDao d = new UserDao();
                int ok = d.registerUser(inUser);

                //Insert data in Customer
                Customer inCustomer = new Customer();

                inCustomer.setUserIdUser(ok);

                CustomerDao cd = new CustomerDao();
                int ok1 = cd.registerCustomer(inCustomer);

                //Insert data in Pessoa_Juridica
                PessoaJuridica inPessoaJuridica = new PessoaJuridica();
            
                inPessoaJuridica.setCnpj(req.getParameter("CNPJ"));
                inPessoaJuridica.setRazaoSocial(req.getParameter("razaoSocial"));
                inPessoaJuridica.setCustomerIdCustomer(ok1);

                PessoaJuridicaDao pjd = new PessoaJuridicaDao();

                int ok2 = pjd.registerPessoaJuridica(inPessoaJuridica);

                //Insert data in Address
                Address inAddress = new Address();
                            
                inAddress.setStreet(req.getParameter("street"));
                inAddress.setNumber(req.getParameter("number"));
                inAddress.setDistrict(req.getParameter("district"));
                inAddress.setCity(req.getParameter("city"));
                inAddress.setState(req.getParameter("state"));
                inAddress.setZipCode(req.getParameter("zipCode"));
                inAddress.setCustomerIdCustomer(ok1);

                AddressDao ad = new AddressDao();

                int ok3 = ad.registerAddress(inAddress);

                //Insert data in Phone
                Phone inPhone = new Phone();

                inPhone.setPhone(req.getParameter("phone"));
                inPhone.setCustomerIdCustomer(ok1);

                PhoneDao phd = new PhoneDao();

                int ok4 = phd.registerPhone(inPhone);


                if(ok >= 1 && ok1 >=1 && ok2 >= 1 && ok3 >=1 && ok4 >= 1)
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
        resp.setHeader("Access-Control-Allow-Origin", "*");
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