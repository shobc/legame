package DB.deleteSentence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendDelete{
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    String sql;
    public FriendDelete(Connection cn,String user_id,String friend_id){
        this.cn = cn;
        sql="update friend_table set delete_flag = 1 where user_id = '"+user_id+"' and friend_id = '"+friend_id+"'";
        try{
            st = cn.createStatement();
        }catch(SQLException e){
            System.out.println("FriendDelete�̃R���X�g���N�^�ŗ�O");
        }
    }
    public int getFriendDeleteCount(){
        int count = 0;
        try{
            count = st.executeUpdate(sql);
            st.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("FriendDelete��getFriendDeleteCount()�ŗ�O");
        }
        return count;
    }
}