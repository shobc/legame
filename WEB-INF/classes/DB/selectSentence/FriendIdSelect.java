package DB.selectSentence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendIdSelect{
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    public FriendIdSelect(Connection cn,String id){
        this.cn = cn;
        String sql = "select search_id,nickname,single_word,top_picture,user_id from user_information_table where search_id = '" +id+"'";
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
        }catch(SQLException e){
            System.out.println("FriendIdSelectのコンストラクタで例外");
        }
    }

    public ResultSet getRs() {
        return rs;
    }
}