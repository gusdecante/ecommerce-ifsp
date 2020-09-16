// package controller;

// import java.io.IOException;
// import java.io.PrintWriter;
// import java.sql.SQLException;
// import java.util.List;

// import javax.servlet.ServletException;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import com.google.gson.Gson;

// import dao./*Class*/Dao;
// import model./*Class*/;

// public class /*Class*/Controller extends HttpServlet{

//     private static final long serialVersionUID = 1L;

//     @Override
//     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
//         resp.setContentType("application/json");
//         resp.setCharacterEncoding("UTF-8");
//         resp.setHeader("Access-Control-Allow-Origin", "*");
//         req.setCharacterEncoding("UTF-8");

//         PrintWriter saida = resp.getWriter();

//         //CREATE JSON WITH QUERY FROM DB
//         try {
//             int parameter, n = 0;
            
//             if (req.getParameter("id/*Class*/") == null || req.getParameter("id/*Class*/").equals("")) {
//                 parameter = 0;
//             } else {
//                 parameter = Integer.parseInt(req.getParameter("id/*Class*/"));
//             }         

//             /*Class*/Dao d = new /*Class*/Dao();
//             List</*Class*/> lst;

//             if (parameter == 0) {
//                 lst = d.search/*Class*/();
//             } else {
//                 lst = d.search/*Class*/(parameter);
//             }
            
//             n = lst.size();

//             if (n == 0) {
//                 saida.println("[ { \"result\" : \"Não há resultado\" } ]");
//             } else {
//                 saida.println("[");

//                 for (/*Class*/ select/*Class*/ : lst) {
         
//                     String str/*Class*/ = new Gson().toJson(select/*Class*/);

//                     if(n == 1) {
//                         saida.printf(str/*Class*/ + "]");
//                     }else 
//                         saida.println(str/*Class*/ + ",");
//                     n--;
//                 }
            
//             }
        
//         } catch (SQLException e) {
//             saida.println("[ { \"result\" : \"Erro S " + e.getMessage() + "\" } ]");
//         } catch (NumberFormatException e) {
//             e.printStackTrace();
//             saida.println("[ { \"result\" : \"Erro N " + e.getMessage() + "\" } ]");
//         } catch (Exception e) {
//             e.printStackTrace();
//             saida.println("[ { \"result\" : \"Erro E " + e.getMessage() + "\" } ]");
//         }

//         saida.flush();
//         saida.close();

//     }

//     @Override
//     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
//         resp.setContentType("application/json");
//         resp.setCharacterEncoding("UTF-8");
//         resp.setHeader("Access-Control-Allow-Origin", "*");
//         req.setCharacterEncoding("UTF-8");

//         PrintWriter saida = resp.getWriter();

//         //CREATE JSON WITH db RESULT OF TRANSATION
//         try {
//             // criar validação de usuário.
//             //
//             //
//             if (
//                 req.getParameter("") == null || req.getParameter("").equals("") ||
//                 req.getParameter("") == null || req.getParameter("").equals("") ||
//                 req.getParameter("") == null || req.getParameter("").equals("") ||
//                 req.getParameter("") == null || req.getParameter("").equals("") ||
//                 req.getParameter("") == null || req.getParameter("").equals("") ||
//                 req.getParameter("") == null || req.getParameter("").equals("")
//             ) {
//                 saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
//             } else {
//                 /*Class*/ in/*Class*/ = new /*Class*/();
            
//                 in/*Class*/.set(Integer.parseInt(req.getParameter("")));
//                 in/*Class*/.set(req.getParameter(""));
//                 in/*Class*/.set(req.getParameter(""));
//                 in/*Class*/.set(req.getParameter(""));
//                 in/*Class*/.set(req.getParameter(""));

//                 /*Class*/Dao d = new /*Class*/Dao();

//                 int ok = d.register/*Class*/(in/*Class*/);

//                 if(ok == 1)
//                     saida.println("[ { \"result\" : \"Dados inseridos com sucesso\" } ]");
//                 else
//                     saida.println("[ { \"result\" : \"Falha na inserção de dados\" } ]");
//             }                        

//         } catch (NumberFormatException e) {
//             e.printStackTrace();
//             saida.println("[ { \"result\" : \"Erro N " + e.getMessage() + "\" } ]");
//         } catch (Exception e) {
//             e.printStackTrace();
//             saida.println("[ { \"result\" : \"Erro E " + e.getMessage() + "\" } ]");
//         }

//         saida.flush();
//         saida.close();

//     }

//     @Override
//     protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
//         resp.setContentType("application/json");
//         resp.setCharacterEncoding("UTF-8");
//         resp.setHeader("Access-Control-Allow-Origin", "*");
//         req.setCharacterEncoding("UTF-8");

//         PrintWriter saida = resp.getWriter();

//         //CREATE JSON WITH QUERY FROM DB
//         try {
//             // criar validação de usuário.
//             //
//             //
            
//             if (req.getParameter("id/*Class*/") == null || req.getParameter("id/*Class*/").equals("")) {
//                 saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");
//             } else {
                
//                 /*Class*/Dao d = new /*Class*/Dao();

//                 int ok = d.delete/*Class*/(Integer.parseInt(req.getParameter("id/*Class*/")));

//                 if(ok == 1)
//                     saida.println("[ { \"result\" : \"Dado excluido com sucesso\" } ]");
//                 else
//                     saida.println("[ { \"result\" : \"Falha na exclusão\" } ]");
//             }
            
//         } catch (NumberFormatException e) {
//             e.printStackTrace();
//             saida.println("[ { \"result\" : \"Erro N " + e.getMessage() + "\" } ]");
//         } catch (Exception e) {
//             e.printStackTrace();
//             saida.println("[ { \"result\" : \"Erro E " + e.getMessage() + "\" } ]");
//         }

//         saida.flush();
//         saida.close();

//     }

//     @Override
//     protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
//         resp.setContentType("application/json");
//         resp.setCharacterEncoding("UTF-8");
//         resp.setHeader("Access-Control-Allow-Origin", "*");
//         req.setCharacterEncoding("UTF-8");

//         PrintWriter saida = resp.getWriter();

//         //CREATE JSON WITH db RESULT OF TRANSATION
//         try {
//             // criar validação de usuário.
//             //
//             //
//             if (
//                 req.getParameter("id/*Class*/") == null || req.getParameter("id/*Class*/").equals("") ||
//                 req.getParameter("") == null || req.getParameter("").equals("") ||
//                 req.getParameter("") == null || req.getParameter("").equals("") ||
//                 req.getParameter("") == null || req.getParameter("").equals("")
//             ) {
//                 saida.println("[ { \"result\" : \"Existem valores nulos\" } ]");

//             } else {
//                 /*Class*/ up/*Class*/ = new /*Class*/();
            
//                 up/*Class*/.setId/*Class*/(Integer.parseInt(req.getParameter("id/*Class*/")));
//                 up/*Class*/.set(req.getParameter(""));
//                 up/*Class*/.set(req.getParameter(""));
//                 up/*Class*/.set(req.getParameter(""));

//                 /*Class*/Dao d = new /*Class*/Dao();

//                 int ok = d.update/*Class*/(up/*Class*/);

//                 if(ok == 1)
//                     saida.println("[ { \"result\" : \"Dados atualizados com sucesso\" } ]");
//                 else
//                     saida.println("[ { \"result\" : \"Falha na atualização de dados\" } ]");
//             }                        

//         } catch (NumberFormatException e) {
//             e.printStackTrace();
//             saida.println("[ { \"result\" : \"Erro N " + e.getMessage() + "\" } ]");
//         } catch (Exception e) {
//             e.printStackTrace();
//             saida.println("[ { \"result\" : \"Erro E " + e.getMessage() + "\" } ]");
//         }

//         saida.flush();
//         saida.close();

//     }

// }