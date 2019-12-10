package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

import DB.selectSentence.SelectPutTogether;

public class NewFriendSearchDB{
    public String searchNewFriend(String user_id){
            String count = "";
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("Ú‘±Š®—¹");

            String sql = "select count(*) from friend_table " +
                    "where user_id IN(select user_id from friend_table where friend_id = '"+user_id+"') " +
                    "and user_id NOT IN (select friend_id from friend_table where user_id = '"+user_id+"') " +
                    "and friend_id = '"+user_id+"' and friend_flag= 0 and delete_flag = 0";

            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();

            rs.next();
            if(!rs.getString(1).equals("0")){
                count = rs.getString(1);
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQL‚Ì—áŠO");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("‘S‚Ä‚ğE‚¤—áŠO");
        }
        return count;
    }
}