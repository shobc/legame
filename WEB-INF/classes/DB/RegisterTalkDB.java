package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import DB.insertSentence.InsertPutTogether;

public class RegisterTalkDB{

    public void RegisterTalk(String chat_id,String user_id,String content){
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            cn.setAutoCommit(false);
            System.out.println("�ڑ�����");
            String sql="insert into talk_table(chat_id,user_id,content) values('"+chat_id+"','"+user_id+"','"+content+"')";
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