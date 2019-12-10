package DB.updateSentence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendFlagUpdate{
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    String sql;
    public FriendFlagUpdate(Connection cn,String user_id,String my_user_id,String flag){
        this.cn = cn;
        sql="update friend_table set friend_flag = '"+flag+"' where user_id = '"+my_user_id+"' and friend_id = '"+user_id+"'";
        try{
            st = cn.createStatement();
        }catch(SQLException e){
            System.out.println("FriendFlagUpdateのコンストラクタで例外");
        }
    }
    public int getFrinedFlagupdateCount(){
        int count = 0;
        try{
            count = st.executeUpdate(sql);
            st.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("getBlockCountのgetAddCount()で例外");
        }
        return count;
    }
}