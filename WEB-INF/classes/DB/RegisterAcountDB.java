package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import DB.insertSentence.InsertPutTogether;

public class RegisterAcountDB{

    public static void registerAcount(String mail,String pass){
        try{
           Connection cn = OracleConnector.getConnection("learn","learn");
            cn.setAutoCommit(false);
            System.out.println("�ڑ�����");
            String sql="insert into user_table(user_id,mail ,password ) " +
                    "values(user_sequesnce.nextval,'"+mail+"','"+pass+"')";
            InsertPutTogether ipt = new InsertPutTogether(cn,sql);
            int count=ipt.getCount();

            System.out.println(count+"����������܂���");

            cn.commit();

            cn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

