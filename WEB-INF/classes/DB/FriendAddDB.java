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
            System.out.println("接続完了");
            FriendAddInsert registeracountinsert = new FriendAddInsert(cn,user_id,friend_id);
            count=registeracountinsert.getAddCount();

            System.out.println(count+"件処理されました");

            cn.commit();

            cn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}