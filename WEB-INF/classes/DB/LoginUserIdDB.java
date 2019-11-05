package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import DB.selectSentence.LoginUserIdSelect;

public class LoginUserIdDB{
    public static int loginUserId(String mail,String pass){
        int user_id = 0;
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("Ú‘±Š®—¹");
            LoginUserIdSelect loginuseridselect = new LoginUserIdSelect(cn,mail,pass);
            ResultSet rs = loginuseridselect.getRs();

            rs.next();
            user_id = Integer.parseInt(rs.getString(1));

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQL‚Ì—áŠO");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("‘S‚Ä‚ğE‚¤—áŠO");
        }
        return user_id;
    }
}