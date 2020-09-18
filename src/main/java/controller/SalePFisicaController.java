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

import dao.SalePFisicaDao;
import model.SalePFisica;

public class SalePFisicaController extends HttpServlet {

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
            int pFisica;
            String strParameter = "";

            if (req.getParameter("idPessoaFisica") == null || req.getParameter("idPessoaFisica").equals("")) {
                pFisica = 0;
            } else {
                pFisica = Integer.parseInt(req.getParameter("idPessoaFisica"));
            }

            if (req.getParameter("idSalePFisica") == null || req.getParameter("idSalePFisica").equals("")) {
                strParameter = "";
            } else {
                strParameter = req.getParameter("idSalePFisica");
            }

            SalePFisicaDao sd = new SalePFisicaDao();
            String strJson = "[ { \"result\" : \"Não há resultado\" } ]";

            // conditions for select
            if (pFisica == 0 && strParameter.equals("")) {
                strJson = "[ { \"result\" : \"Não há resultado\" } ]";

            } else if (pFisica != 0 && strParameter.equals("")) {
                strJson = sd.searchSalePFisica(pFisica);

            } else if (!strParameter.equals("") && pFisica == 0) {
                strJson = sd.searchSalePFisicaIdMongo(strParameter);

            } else {
                strJson = "[ { \"result\" : \"Não há resultado, mais de um valor preenchido!\" } ]";

            }

            saida.println(strJson);

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

        try {
            StringBuffer sb = new StringBuffer();
            String line = null;

            BufferedReader reader = req.getReader();

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            JsonObject jsonObj = new Gson().fromJson(sb.toString(), JsonObject.class);

            SalePFisica s = new SalePFisica();

            // Fields of PessoaFisica
            s.setIdPessoaFisica(jsonObj.get("idPessoaFisica").getAsInt());
            s.setNameCustomer(jsonObj.get("nameCustomer").getAsString());
            s.setCPF(jsonObj.get("CPF").getAsString());
            s.setRG(jsonObj.get("RG").getAsString());
            s.setDateBirth(jsonObj.get("dateBirth").getAsString());

            // Fields user
            s.setEmail(jsonObj.get("email").getAsString());

            // Fields Address
            s.setStreet(jsonObj.get("street").getAsString());
            s.setNumber(jsonObj.get("number").getAsString());
            s.setDistrict(jsonObj.get("district").getAsString());
            s.setCity(jsonObj.get("city").getAsString());
            s.setState(jsonObj.get("state").getAsString());
            s.setZipCode(jsonObj.get("zipCode").getAsString());

            // Fields Phone
            s.setPhone(jsonObj.get("phone").getAsString());

            // Fields Order
            s.setDate(jsonObj.get("date").getAsString());

            // Fields Payment_Form
            s.setPaymentForm(jsonObj.get("paymentForm").getAsString());

            // Fields Product
            s.setColor(jsonObj.get("color").getAsString());
            s.setFinishingProcess(jsonObj.get("finishingProcess").getAsString());
            s.setCubaType(jsonObj.get("cubaType").getAsString());
            s.setDescription(jsonObj.get("description").getAsString());
            s.setImageLink(jsonObj.get("imageLink").getAsString());
            s.setUnitaryValue(jsonObj.get("unitaryValue").getAsDouble());

            // Fields Order_Item
            s.setAmount(jsonObj.get("amount").getAsInt());

            // Fields Category
            s.setCategory(jsonObj.get("category").getAsString());

            if (jsonObj.get("color2") != null && jsonObj.get("finishingProcess2") != null
                && jsonObj.get("cubaType2") != null && jsonObj.get("description2") != null
                && jsonObj.get("imageLink2") != null && jsonObj.get("unitaryValue2") != null
                && jsonObj.get("amount2") != null && jsonObj.get("category2") != null
                ) {
                // Fields Product
                s.setColor2(jsonObj.get("color2").getAsString());
                s.setFinishingProcess2(jsonObj.get("finishingProcess2").getAsString());
                s.setCubaType2(jsonObj.get("cubaType2").getAsString());
                s.setDescription2(jsonObj.get("description2").getAsString());
                s.setImageLink2(jsonObj.get("imageLink2").getAsString());
                s.setUnitaryValue2(jsonObj.get("unitaryValue2").getAsDouble());

                // Fields Order_Item
                s.setAmount2(jsonObj.get("amount2").getAsInt());

                // Fields Category
                s.setCategory2(jsonObj.get("category2").getAsString());
            }

            if (jsonObj.get("color3") != null && jsonObj.get("finishingProcess3") != null
                && jsonObj.get("cubaType3") != null && jsonObj.get("description3") != null
                && jsonObj.get("imageLink3") != null && jsonObj.get("unitaryValue3") != null
                && jsonObj.get("amount3") != null && jsonObj.get("category3") != null) {
                // Fields Product
                s.setColor3(jsonObj.get("color3").getAsString());
                s.setFinishingProcess3(jsonObj.get("finishingProcess3").getAsString());
                s.setCubaType3(jsonObj.get("cubaType3").getAsString());
                s.setDescription3(jsonObj.get("description3").getAsString());
                s.setImageLink3(jsonObj.get("imageLink3").getAsString());
                s.setUnitaryValue3(jsonObj.get("unitaryValue3").getAsDouble());

                // Fields Order_Item
                s.setAmount3(jsonObj.get("amount3").getAsInt());

                // Fields Category
                s.setCategory3(jsonObj.get("category3").getAsString());
            }

            // Field Sale Pessoa Fisica
            s.setTotal(jsonObj.get("total").getAsDouble());

            SalePFisicaDao sd = new SalePFisicaDao();

            int ok = sd.registerSalePFisica(s);

            if (ok == 1)
                saida.println("[ { \"result\" : \"Dados inseridos com sucesso\" } ]");
            else
                saida.println("[ { \"result\" : \"Falha na inserção de dados\" } ]");

        } catch (IOException e) {
            e.printStackTrace();
            saida.println("[ { \"result\" : \"Erro IO " + e.getMessage() + "\" } ]");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            saida.println("[ { \"result\" : \"Erro N " + e.getMessage() + "\" } ]");
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            saida.println("[ { \"result\" : \"Erro J " + e.getMessage() + "\" } ]");
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

        // CREATE JSON WITH QUERY FROM DB
        try {
            // criar validação de usuário.
            //
            //

            if (req.getParameter("idSalePFisica") == null || req.getParameter("idSalePFisica").equals("")) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {

                SalePFisicaDao sd = new SalePFisicaDao();

                boolean ok = sd.DeleteSalePFisicaIdMongo(req.getParameter("idSalePFisica"));

                if(ok)
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

    // @Override
    // protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    //     resp.setContentType("application/json");
    //     resp.setCharacterEncoding("UTF-8");
    //     resp.setHeader("Access-Control-Allow-Origin", "*");
    //     req.setCharacterEncoding("UTF-8");

    //     PrintWriter saida = resp.getWriter();

    //     //CREATE JSON WITH db RESULT OF TRANSATION
    //     try {
    //         // criar validação de usuário.
    //         //
    //         //
    //         if (
    //             req.getParameter("idSale") == null || req.getParameter("idSale").equals("") ||
    //             req.getParameter("") == null || req.getParameter("").equals("") ||
    //             req.getParameter("") == null || req.getParameter("").equals("") ||
    //             req.getParameter("") == null || req.getParameter("").equals("")
    //         ) {
    //             saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");

    //         } else {
    //             Sale upSale = new Sale();
            
    //             upSale.setIdSale(Integer.parseInt(req.getParameter("idSale")));
    //             upSale.set(req.getParameter(""));
    //             upSale.set(req.getParameter(""));
    //             upSale.set(req.getParameter(""));

    //             SaleDao d = new SaleDao();

    //             int ok = d.updateSale(upSale);

    //             if(ok == 1)
    //                 saida.println("[ { \"result\" : \"Dados atualizados com sucesso\" } ]");
    //             else
    //                 saida.println("[ { \"result\" : \"Falha na atualização de dados\" } ]");
    //         }                        

    //     } catch (NumberFormatException e) {
    //         e.printStackTrace();
    //         saida.println("[ { \"result\" : \"Erro N " + e.getMessage() + "\" } ]");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         saida.println("[ { \"result\" : \"Erro E " + e.getMessage() + "\" } ]");
    //     }

    // }

}