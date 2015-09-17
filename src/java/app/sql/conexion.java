
package app.sql;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexion {
    static String bd = "gears_com_bd";
    static String login = "root";
    static String password = "";
    static String url = "jdbc:mysql://localhost:3306/gears_com_bd";
    
    Connection conn = null;
    
    public conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url,login,password);
            if (conn!=null){
                System.out.println("Coneccion a base de datos "+bd+"OK");
               }
        }catch(SQLException e)  {
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }
 public Connection getConnection(){
     return conn;
 }
 
 public void desconectar(){
     conn=null;
 }
 public void conectarbdreporte (){
   try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url,login,password);
            if (conn!=null){
                System.out.println("Coneccion a base de datos "+bd+"OK");
               }
        }catch(SQLException e)  {
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
 }

   
}
