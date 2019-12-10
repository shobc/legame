package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import DB.insertSentence.InsertPutTogether;

public class CommentAddDB{

    public void commentAdd(String user_id,String timeline_id,String comment){
        String sql="insert into comment_table(comment_id,user_id,timeline_id,comment_sentence) values(COMMENT_SEQUENCE.nextval,'"+user_id+"','"+timeline_id+"','"+comment+"')";
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            cn.setAutoCommit(false);
            System.out.println("Ú‘±Š®—¹");
            InsertPutTogether ipt = new InsertPutTogether(cn,sql);
            int count=ipt.getCount();
            System.out.println(count+"Œˆ—‚³‚ê‚Ü‚µ‚½");

            cn.commit();

            cn.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}