package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import DB.selectSentence.SelectPutTogether;

public class LoginUserIdDB{
    public static String loginUserId(String mail,String pass){
        String user_id = null;
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("Ú‘±Š®—¹");

            String sql = "select user_id from user_table where mail= '" +mail+ "' and password = '" +pass+"'";

            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();

            rs.next();
            user_id = rs.getString(1);

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