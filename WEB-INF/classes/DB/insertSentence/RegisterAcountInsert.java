package DB.insertSentence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterAcountInsert{
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    String sql;
    public RegisterAcountInsert(Connection cn,String mail,String pass){
        this.cn = cn;
        sql="insert into user_table(user_id,mail ,password ) values(user_sequesnce.nextval,'"+mail+"','"+pass+"')";
        try{
            st = cn.createStatement();
        }catch(SQLException e){
            System.out.println("RegisterAcountInsertのコンストラクタで例外");
        }
    }
    public int getRegisterCount(){
        int count = 0;
        try{
            count = st.executeUpdate(sql);
            st.close();
        }catch(SQLException e){
            System.out.println("RegisterAcountInsertのgetRegisterCount()で例外");
        }
        return count;
    }
}