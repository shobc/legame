package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import DB.insertSentence.InsertPutTogether;

public class FriendAddDB{

    public void friendAdd(String user_id,String friend_id){
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            cn.setAutoCommit(false);
            System.out.println("�ڑ�����");
            String sql="insert into friend_table(user_id ,friend_id) values('"+user_id+"','"+friend_id+"')";
            InsertPutTogether ipt = new InsertPutTogether(cn,sql);
            int count=ipt.getCount();
            System.out.println(count+"����������܂���");

            cn.commit();

            cn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}