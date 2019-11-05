package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import DB.selectSentence.LoginSelect;

public class LoginSearchDB{
    public static boolean searchAcount(String Pmail,String Ppass){
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("Ú‘±Š®—¹");
            LoginSelect loginselect = new LoginSelect(cn,Pmail,Ppass);
            ResultSet rs = loginselect.getRs();

            rs.next();
            String mail = rs.getString(1);
            String pass = rs.getString(2);
            System.out.println(mail);
            System.out.println(pass);

            if(mail!=null&&pass!=null){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQL‚Ì—áŠO");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("‘S‚Ä‚ğE‚¤—áŠO");
        }
        return false;
    }
}