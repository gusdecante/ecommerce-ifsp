// package dao;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;
// import model./*class*/;

// import util.MysqlConection;

// public class /*Class*/Dao {

//     private Connection con;

//     //Mysql connection created
//     public /*class*/Dao() {
//         con = new MysqlConection().getConnection();
//     }

//     //Insert register on the mysql table
//     public boolean register/*class*/(/*class*/ i) {
//         boolean isSuccess = false;
//         try {
//             String query = "INSERT INTO /*table*/ (id_/*class*/, , , , ) VALUES (?, ?, ?, ?, ?);";

//             PreparedStatement st = con.prepareStatement(query); //Prepared the query

//             st.setInt(1, 0);
//             st.setString(2, i.get/*field*/());
//             st.setString(3, i.get/*field*/());
//             st.setString(4, i.get/*field*/());
//             st.setString(5, i.get/*field*/());

//             st.executeUpdate(); //Execute the insert
//             st.close(); //Close the Statment
//             con.close(); //Close the connection
//             isSuccess = true;
            
//         } catch (SQLException e) {
//             e.printStackTrace();
//             isSuccess = false;
//         }
        
//         return isSuccess;
//     }

//     //Select register on the mysql table
//     public List</*class*/> search/*class*/() throws SQLException, Exception {

//         List</*class*/> lista = new ArrayList();
//         String query = "SELECT * FROM /*table*/ ;";

//         PreparedStatement st = con.prepareStatement(query); //Prepared the query
//         ResultSet rs = st.executeQuery(); //Execute the select

//         while(rs.next()) {
//             /*class*/ /*object*/ = new /*class*/();

//             /*object*/.setId/*class*/(rs.getInt("id_/*class*/"));
//             /*object*/.set/*field*/(rs.getString("/*field-db*/"));
//             /*object*/.set/*field*/(rs.getString("/*field-db*/"));
//             /*object*/.set/*field*/(rs.getString("/*field-db*/"));
//             /*object*/.set/*field*/(rs.getString("/*field-db*/"));

//             lista.add(/*object*/);
//         }
        
//         st.close(); //Close the Statment
//         con.close(); //Close the connection

//         return lista;
//     }

//     //Update register on the mysql table
//     public boolean update/*class*/(/*class*/ u) {
//         boolean isSuccess = false;
//         try {
//             String query = "UPDATE /*table*/ SET id_/*class*/ = ?, *field* = ?, , , ;";

//             PreparedStatement st = con.prepareStatement(query); //Prepared the query
//             //Select id informated 
//             List</*Class*/> l = new /*Class*/Dao().search/*Class*/(u.getId/*Class*/());
                
//             for (/*Class*/ lc : l) {
//                 st.setInt(1, lc.getId/*Class*/());
//             }
//             st.setString(2, u.get/*field-db*/());
//             st.setString(3, u.get/*field-db*/());
//             st.setString(4, u.get/*field-db*/());
//             st.setString(5, u.get/*field-db*/());

//             st.executeUpdate(); //Execute the update
//             st.close(); //Close the Statment
//             con.close(); //Close the connection

//             isSuccess = true;

//         } catch (SQLException e) {
//             e.printStackTrace();
//             isSuccess = false;
//         } catch (Exception e) {
//             e.printStackTrace();
//             isSuccess = false;
//         }

//         return isSuccess;
//     }

//     //delete register on the mysql table
//     public boolean delete/*class*/(int d) {
//         boolean isSuccess = false;
//         try {
//             String query = "DELETE FROM /*table*/ WHERE id_/*class*/ = ?;";

//             PreparedStatement st = con.prepareStatement(query); //Prepared the query
//             st.setInt(1, d);

//             st.executeUpdate(); //Execute the Delete
//             st.close(); //Close the Statment
//             con.close(); //Close the connection

//             isSuccess = true;
            
//         } catch (SQLException e) {
//             e.printStackTrace();
//             isSuccess = false;
//         }
        
//         return isSuccess;
//     }

//     //Select specifical register on the mysql table
//     public List</*class*/> search/*class*/(int id/*Class*/) throws SQLException, Exception {

//         List</*class*/> lista = new ArrayList();
//         String query = "SELECT * FROM /*table*/ WHERE id_/*Class*/ = ?;";

//         PreparedStatement st = con.prepareStatement(query); //Prepared the query
//         st.setInt(2, id/*Class*/);

//         ResultSet rs = st.executeQuery(); //Execute the select

//         while(rs.next()) {
//             /*class*/ /*object*/ = new /*class*/();

//             /*object*/.setId/*class*/(rs.getInt("id_/*class*/"));
//             /*object*/.set/*field*/(rs.getString("/*field-db*/"));
//             /*object*/.set/*field*/(rs.getString("/*field-db*/"));
//             /*object*/.set/*field*/(rs.getString("/*field-db*/"));
//             /*object*/.set/*field*/(rs.getString("/*field-db*/"));

//             lista.add(/*object*/);
           
//         }

//         st.close(); //Close the Statment
//         con.close(); //Close the connection

//         return lista;
//     }
    
// }