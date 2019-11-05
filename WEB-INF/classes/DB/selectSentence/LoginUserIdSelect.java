package DB.selectSentence;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginUserIdSelect{
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    public LoginUserIdSelect(Connection cn,String mail,String pass){
        this.cn = cn;
        String sql = "select user_id from user_table where mail= '" +mail+ "' and password = '" +pass+"'";
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
        }catch(SQLException e){
            System.out.println("LoginUserIdSelectのコンストラクタで例外");
        }
    }

    public ResultSet getRs() {
        return rs;
    }
}