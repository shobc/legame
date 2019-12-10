package DB.insertSentence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertPutTogether{
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    String sql = null;
    public InsertPutTogether(Connection cn,String sql){
        this.cn = cn;
        this.sql = sql;
        try{
            st = cn.createStatement();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("InsertPutTogether�̃R���X�g���N�^�ŗ�O");
        }
    }
    public int getCount(){
        int count = 0;
        try{
            count = st.executeUpdate(sql);
            st.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("InsertPutTogether��getACount()�ŗ�O");
        }
        return count;
    }
}