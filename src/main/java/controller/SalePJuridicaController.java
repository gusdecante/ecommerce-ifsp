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

import dao.SalePJuridicaDao;
import model.SalePJuridica;

public class SalePJuridicaController extends HttpServlet {

    private static final long serialVersionUID = 12L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        req.setCharacterEncoding("UTF-8");

        PrintWriter saida = resp.getWriter();

        // CREATE JSON WITH QUERY FROM DB
        try {
            int pJuridica;
            String strParameter = "";

            if (req.getParameter("idPessoaJuridica") == null || req.getParameter("idPessoaJuridica").equals("")) {
                pJuridica = 0;
            } else {
                pJuridica = Integer.parseInt(req.getParameter("idPessoaJuridica"));
            }

            if (req.getParameter("idSalePJuridica") == null || req.getParameter("idSalePJuridica").equals("")) {
                strParameter = "";
            } else {
                strParameter = req.getParameter("idSalePJuridica");
            }

            SalePJuridicaDao sd = new SalePJuridicaDao();
            String strJson = "[ { \"result\" : \"Não há resultado\" } ]";

            // conditions for select
            if (strParameter.equals("") && pJuridica == 0) {
                strJson = "[ { \"result\" : \"Não há resultado\" } ]";

            } else if (pJuridica != 0 && strParameter.equals("")) {
                strJson = sd.searchSalePJuridica(pJuridica);

            } else if (!strParameter.equals("") && pJuridica == 0) {
                strJson = sd.searchSalePJuridicaIdMongo(strParameter);

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

            SalePJuridica s = new SalePJuridica();

            // Fields Pessoa_Juridica
            s.setIdPessoaJuridica(jsonObj.get("idPessoaJuridica").getAsInt());
            s.setCnpj(jsonObj.get("cnpj").getAsString());
            s.setRazaoSocial(jsonObj.get("razaoSocial").getAsString());

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
                    && jsonObj.get("amount2") != null && jsonObj.get("category2") != null) {
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

            // Field Sale Pessoa Juridica
            s.setTotal(jsonObj.get("total").getAsDouble());

            SalePJuridicaDao sd = new SalePJuridicaDao();

            int ok = sd.registerSalePJuridica(s);

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

            if (req.getParameter("idSalePJuridica") == null || req.getParameter("idSalePJuridica").equals("")) {
                saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
            } else {

                SalePJuridicaDao sd = new SalePJuridicaDao();

                boolean ok = sd.DeleteSalePJuridicaIdMongo(req.getParameter("idSalePJuridica"));

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

}