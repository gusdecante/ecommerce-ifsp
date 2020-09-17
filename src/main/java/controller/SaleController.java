package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import dao.SaleDao;
import model.Sale;

public class SaleController extends HttpServlet {

    private static final long serialVersionUID = 11L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        // CREATE JSON WITH QUERY FROM DB
        try {
            int pFisica, pJuridica;
            String strParameter = "";

            if (req.getParameter("idPessoaFisica") == null || req.getParameter("idPessoaFisica").equals("")) {
                pFisica = 0;
            } else { 
                pFisica = Integer.parseInt(req.getParameter("idPessoaFisica"));
            }

            if (req.getParameter("idPessoaJuridica") == null || req.getParameter("idPessoaJuridica").equals("")) {
                pJuridica = 0;
            } else { 
                pJuridica = Integer.parseInt(req.getParameter("idPessoaJuridica"));
            } 
            
            if (req.getParameter("idSale") == null || req.getParameter("idSale").equals("")) {
                strParameter = "";
            } else {
                strParameter = req.getParameter("idSale");
            }

            SaleDao sd = new SaleDao();
            String strJson = "[ { \"result\" : \"Não há resultado\" } ]";
            
            //conditions for select
            if (pFisica == 0 && strParameter.equals("") && pJuridica == 0) {
                strJson = "[ { \"result\" : \"Não há resultado\" } ]";

            } else if (pFisica != 0 && strParameter.equals("") && pJuridica == 0) {
                strJson = sd.searchSaleIdFisica(pFisica);

            } else if (pJuridica != 0 && pFisica == 0 && strParameter.equals("")) {
                strJson = sd.searchSaleIdJuridica(pJuridica);

            } else if (!strParameter.equals("") && pFisica == 0 && pJuridica == 0) {
                strJson = sd.searchSaleIdMongo(strParameter);

            } else {
                strJson = "[ { \"result\" : \"Não há resultado, mais de um valor preenchido!\" } ]";

            }

            saida.println(strJson);

            // n = lst.size();

            // if (n == 0) {
            // saida.println("[ { \"result\" : \"Não há resultado\" } ]");
            // } else {
            // saida.println("[");

            // for (Sale selectSale : lst) {

            // String strSale = new Gson().toJson(selectSale);

            // if(n == 1) {
            // saida.printf(strSale + "]");
            // }else
            // saida.println(strSale + ",");
            // n--;
            // }

            // }

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