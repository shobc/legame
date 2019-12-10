package DB.updateSentence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class QRCodeUpdate{
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    String sql;
    public QRCodeUpdate(Connection cn,String user_id,String randomString){
        this.cn = cn;
        sql="update user_information_table set qrcode= '"+randomString+"' where user_id = '"+user_id+"'";
        try{
            st = cn.createStatement();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("QRCodeUpdateのコンストラクタで例外");
        }
    }
    public int getRegisterCount(){
        int count = 0;
        try{
            count = st.executeUpdate(sql);
            st.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("QRCodeUpdateのgetRegisterCount()で例外");
        }
        return count;
    }
}