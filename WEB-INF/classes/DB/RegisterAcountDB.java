package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import DB.insertSentence.RegisterAcountInsert;

public class RegisterAcountDB{

    public static int registerAcount(String mail,String pass){
        int count = 0;
        try{
           Connection cn = OracleConnector.getConnection("learn","learn");
            cn.setAutoCommit(false);
            System.out.println("Ú‘±Š®—¹");
            RegisterAcountInsert registeracountinsert = new RegisterAcountInsert(cn,mail,pass);
            count=registeracountinsert.getRegisterCount();

            System.out.println(count+"Œˆ—‚³‚ê‚Ü‚µ‚½");

            cn.commit();

            cn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        return count;
    }
}

