package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import DB.insertSentence.InsertPutTogether;
public class TimeLineCreateDB{

    public void createTimeline(String user_id,String timeline_sentence){
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            cn.setAutoCommit(false);
            System.out.println("Ú‘±Š®—¹");
            String sql="insert into timeline_table(user_id,timeline_id,timeline_sentence)" +
                    " values('"+user_id+"',timeline_sequence.nextval,'"+timeline_sentence+"')";
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