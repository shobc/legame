package DB;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.updateSentence.PassWordUpdate;

public class UpdatePasswordDB{
    public void updatePassword(String password,String mail){
        int count = 0;
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            cn.setAutoCommit(false);
            System.out.println("Ú‘±Š®—¹");
            PassWordUpdate passwordupdate = new PassWordUpdate(cn,password,mail);
            count = passwordupdate.getUpdateCount();
            System.out.println(count+"Œˆ—‚µ‚Ü‚µ‚½");
            cn.commit();

            cn.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }

}
