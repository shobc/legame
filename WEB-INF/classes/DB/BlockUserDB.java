package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import DB.updateSentence.FriendFlagUpdate;

public class BlockUserDB{

    public void userBlock(String user_id,String my_user_id){
        int count = 0;
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            cn.setAutoCommit(false);
            System.out.println("Ú‘±Š®—¹");
            FriendFlagUpdate friendflagupdate = new FriendFlagUpdate(cn,user_id,my_user_id,"1");
            count=friendflagupdate.getFrinedFlagupdateCount();

            System.out.println(count+"Œˆ—‚³‚ê‚Ü‚µ‚½");

            cn.commit();

            cn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}