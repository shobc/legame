package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import DB.insertSentence.FriendAddInsert;

public class FriendAddDB{

    public void friendAdd(String user_id,String friend_id){
        int count = 0;
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            cn.setAutoCommit(false);
            System.out.println("Ú‘±Š®—¹");
            FriendAddInsert registeracountinsert = new FriendAddInsert(cn,user_id,friend_id);
            count=registeracountinsert.getAddCount();

            System.out.println(count+"Œˆ—‚³‚ê‚Ü‚µ‚½");

            cn.commit();

            cn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}