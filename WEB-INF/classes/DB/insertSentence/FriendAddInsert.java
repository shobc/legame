package DB.insertSentence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendAddInsert{
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    String sql;
    public FriendAddInsert(Connection cn,String user_id,String friend_id){
        this.cn = cn;
        System.out.println("user_id"+user_id);
        System.out.println("friend_id"+friend_id);
        sql="insert into friend_table values('"+user_id+"','"+friend_id+"',1)";
        try{
            st = cn.createStatement();
        }catch(SQLException e){
            System.out.println("FriendAddInsertのコンストラクタで例外");
        }
    }
    public int getAddCount(){
        int count = 0;
        try{
            count = st.executeUpdate(sql);
            st.close();
        }catch(SQLException e){
            System.out.println("FriendAddInsertのgetAddCount()で例外");
        }
        return count;
    }
}