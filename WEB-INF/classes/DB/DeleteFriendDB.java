package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import DB.deleteSentence.FriendDelete;

public class DeleteFriendDB{

    public void friendDelete(String user_id,String friend_id){
        int count = 0;
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            cn.setAutoCommit(false);
            System.out.println("�ڑ�����");
            FriendDelete frienddelete = new FriendDelete(cn,user_id,friend_id);
            count=frienddelete.getFriendDeleteCount();

            System.out.println(count+"����������܂���");

            cn.commit();

            cn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}