package DB.selectSentence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginSelect{
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    public LoginSelect(Connection cn,String Pmail,String Ppass){
        this.cn = cn;
        String sql = "select mail,password from user_table where mail= '" +Pmail+ "' and password = '" +Ppass+"'";
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
        }catch(SQLException e){
            System.out.println("LoginSelect�̃R���X�g���N�^�ŗ�O");
        }
    }

    public ResultSet getRs() {
        return rs;
    }
}