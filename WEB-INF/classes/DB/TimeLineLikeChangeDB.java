package DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

import DB.insertSentence.InsertPutTogether;

public class TimeLineLikeChangeDB{
    Connection cn = null;
    String id = null;
    String user_id = null;
    String sql = null;
    public TimeLineLikeChangeDB(String id,String user_id){
        cn = OracleConnector.getConnection("learn","learn");
        System.out.println("Ú‘±Š®—¹");
        this.id = id;
        this.user_id = user_id;
    }
    public void insertLikeRecord(){
        try{

            cn.setAutoCommit(false);
            String sql = "insert into timeline_like_table(timelline_id,user_id) values('"+id+"','"+user_id+"')";
            InsertPutTogether ipt = new InsertPutTogether(cn,sql);
            int count=ipt.getCount();
            System.out.println(count+"Œˆ—‚³‚ê‚Ü‚µ‚½");
            cn.commit();
            cn.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void deleteLikeRecord(){
        try{

            cn.setAutoCommit(false);
            sql="DELETE FROM timeline_like_table WHERE timelline_id = '"+id+"' and user_id = '"+user_id+"'";
            InsertPutTogether ipt = new InsertPutTogether(cn,sql);
            int count=ipt.getCount();
            System.out.println(count+"Œˆ—‚³‚ê‚Ü‚µ‚½");
            cn.commit();
            cn.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}