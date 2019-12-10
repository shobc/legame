package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import DB.selectSentence.SelectPutTogether;


public class ChatIdDB{
    public static String getChatId(String user_id,String friend_id){
        String chat_id = null;
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("ê⁄ë±äÆóπ");

            String sql = "select chat_id from chat_table " +
                    "where (user_chat_id= '"+user_id+"'  and user_chat1_id = '"+friend_id+"') " +
                    "or (user_chat_id= '"+friend_id+"'  and user_chat1_id = '"+user_id+"')";

            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();

            rs.next();
            chat_id = rs.getString(1);

        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return chat_id;
    }
}