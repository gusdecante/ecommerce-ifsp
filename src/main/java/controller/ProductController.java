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

import dao.ProductDao;
import model.Product;

public class ProductController extends HttpServlet{

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

            if (req.getParameter("idProduct") == null || req.getParameter("idProduct").equals("")) {
                parameter = 0;
            } else {
                parameter = Integer.parseInt(req.getParameter("idProduct"));
            }         

            ProductDao d = new ProductDao();
            List<Product> lst;

            if (parameter == 0) {
                lst = d.searchProduct();
            } else {
                lst = d.searchProduct(parameter);
            }
            n = lst.size();

            if (n == 0) {
                saida.println("[ { \"result\" : \"Não há resultado\" } ]");
            } else {
                saida.println("[");

                for (Product selectProduct : lst) {

                    // selectProduct.getIdProduct();
                    // selectProduct.getCategory();
                    // //selectProduct.getCategoryIdCategory();
                    // selectProduct.getColor();
                    // selectProduct.getFinishingProcess();
                    // selectProduct.getCubaType();
                    // selectProduct.getDescription();
                    // selectProduct.getStock();
                    // selectProduct.getUnitaryValue();
                    // selectProduct.getImageLink();
                    
                    
                    String strProduct = new Gson().toJson(selectProduct);

                    if(n == 1) {
                        saida.printf(strProduct + "]");
                    }else 
                        saida.println(strProduct + ",");
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
                req.getParameter("categoryIdCategory") == null || req.getParameter("categoryIdCategory").equals("") ||
                req.getParameter("color") == null || req.getParameter("color").equals("") ||
                req.getParameter("finishingProcess") == null || req.getParameter("finishingProcess").equals("") ||
                req.getParameter("stock") == null || req.getParameter("stock").equals("") ||
                req.getParameter("unitaryValue") == null || req.getParameter("unitaryValue").equals("") ||
                req.getParameter("imageLink") == null || req.getParameter("imageLink").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                Product inProduct = new Product();
            
                inProduct.setCategoryIdCategory(Integer.parseInt(req.getParameter("categoryIdCategory")));
                inProduct.setColor(req.getParameter("color"));
                inProduct.setFinishingProcess(req.getParameter("finishingProcess"));
                inProduct.setCubaType(req.getParameter("cubaType"));
                inProduct.setDescription(req.getParameter("description"));
                inProduct.setStock(Integer.parseInt(req.getParameter("stock")));
                inProduct.setUnitaryValue(Double.parseDouble(req.getParameter("unitaryValue")));
                inProduct.setImageLink(req.getParameter("imageLink"));

                ProductDao d = new ProductDao();

                int ok = d.registerProduct(inProduct);

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
            if (req.getParameter("idProduct") == null || req.getParameter("idProduct").equals("")) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {
                
                ProductDao d = new ProductDao();

                int ok = d.deleteProduct(Integer.parseInt(req.getParameter("idProduct")));

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
                req.getParameter("idProduct") == null || req.getParameter("idProduct").equals("") ||
                req.getParameter("categoryIdCategory") == null || req.getParameter("categoryIdCategory").equals("") ||
                req.getParameter("color") == null || req.getParameter("color").equals("") ||
                req.getParameter("finishingProcess") == null || req.getParameter("finishingProcess").equals("") ||
                req.getParameter("stock") == null || req.getParameter("stock").equals("") ||
                req.getParameter("unitaryValue") == null || req.getParameter("unitaryValue").equals("") ||
                req.getParameter("imageLink") == null || req.getParameter("imageLink").equals("")
            ) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");

            } else {
                Product upProduct = new Product();
            
                upProduct.setIdProduct(Integer.parseInt(req.getParameter("idProduct")));
                upProduct.setCategoryIdCategory(Integer.parseInt(req.getParameter("categoryIdCategory")));
                upProduct.setColor(req.getParameter("color"));
                upProduct.setFinishingProcess(req.getParameter("finishingProcess"));
                upProduct.setCubaType(req.getParameter("cubaType"));
                upProduct.setDescription(req.getParameter("description"));
                upProduct.setStock(Integer.parseInt(req.getParameter("stock")));
                upProduct.setUnitaryValue(Double.parseDouble(req.getParameter("unitaryValue")));
                upProduct.setImageLink(req.getParameter("imageLink"));

                ProductDao d = new ProductDao();

                int ok = d.updateProduct(upProduct);

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