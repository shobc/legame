package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnector{
    public static Connection getConnection(String name,String pass){
        Connection cn = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl",name,pass);
        }catch (ClassNotFoundException e){

        }catch (SQLException e){

        }catch(Exception e){

        }
        return cn;
    }
}