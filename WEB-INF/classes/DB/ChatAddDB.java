package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import DB.insertSentence.InsertPutTogether;

public class ChatAddDB{
    public void ChatAdd(String user_id,String friend_id){
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            cn.setAutoCommit(false);
            System.out.println("Ú‘±Š®—¹");
            String sql="insert into chat_table(chat_id,user_chat_id,user_chat1_id) values(caht_sequesnce.nextval,'"+user_id+"','"+friend_id+"')";
            InsertPutTogether ipt = new InsertPutTogether(cn,sql);
            int count=ipt.getCount();

            System.out.println(count+"Œˆ—‚³‚ê‚Ü‚µ‚½");

            cn.commit();

            cn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
}