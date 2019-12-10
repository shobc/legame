package DB.updateSentence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class PassWordUpdate{
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    String sql;
    public PassWordUpdate(Connection cn,String password,String mail){
        System.out.println(password);
        System.out.println(mail);
        this.cn = cn;
        sql = "update user_table set PASSWORD ='"+password+"' where mail = '"+mail+"'";
        try{
            st = cn.createStatement();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("PassWordUpdateのコンストラクタで例外");
        }
    }
    public int getUpdateCount(){
        int count = 0;
        try{
            count = st.executeUpdate(sql);
            st.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("PassWordUpdateのgetRegisterCount()で例外");
        }
        return count;
    }
}