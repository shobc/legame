package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

import DB.selectSentence.SelectPutTogether;

public class ChatSearchDB{
    public static boolean getBooleanJudge(String user_id,String friend_id){
        boolean judge = false;
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("Ú‘±Š®—¹");

            String sql = "select count(chat_id) from chat_table " +
                    "where (user_chat_id= '"+friend_id+"' and user_chat1_id = '"+user_id+"')" +
                    " or (user_chat_id= '"+user_id+"' and user_chat1_id = '"+friend_id+"')";

            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();
            rs.next();
            String j = rs.getString(1);
            System.out.println("rs.getString(1)="+rs.getString(1));
            System.out.println("j="+j);
            if(j.equals("0")){
                judge = true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL‚Ì—áŠO");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("‘S‚Ä‚ğE‚¤—áŠO");
        }
        System.out.println("judge="+judge);
        return judge;
    }
}