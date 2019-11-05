package DB.selectSentence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ProfileSelect{
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    public ProfileSelect(Connection cn,int id){
        this.cn = cn;
        String sql = "select search_id,nickname,single_word,top_picture,user_id from user_information_table where user_id = '" +id+"'";
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
        }catch(SQLException e){
            System.out.println("ProfileSelectのコンストラクタで例外");
        }
    }

    public ResultSet getRs() {
        return rs;
    }
}