<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Product"%>
<%@ page import="model.Category"%>
<%@ page import="dao.ProductDao"%>
<%@ page import="dao.CategoryDao"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ecommerce</title>
</head>
<body>
    <h1>Ecommerce</h1>
    <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Saepe quia ad quibusdam labore, iste corporis. Dicta, placeat ut impedit non nostrum atque deleniti molestias soluta similique iure quod earum explicabo!</p>

    <%  
        
        //try {
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            //response.setCharacterEncoding("UTF-8");                 
             
        //} catch (SQLException e) {
          //  out.println("<p>Erro " + e.getMessage() + "</p>");
        //}     
    
    %>
</body>
</html>