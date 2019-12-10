package DB.selectSentence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectPutTogether{
    Connection cn = null;
    Statement st = null;
    ResultSet rs = null;
    public SelectPutTogether(Connection cn,String sql){
        this.cn = cn;
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("SelectPutTogether�̃R���X�g���N�^�ŗ�O");
        }
    }

    public ResultSet getRs() {
        return rs;
    }
}