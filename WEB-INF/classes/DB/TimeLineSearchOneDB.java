package DB;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Blob;

import DB.selectSentence.SelectPutTogether;
import DB.function.AcquisitionImage;
import Bean.TimeLineBean;

public class TimeLineSearchOneDB{
    public TimeLineBean getTimeLine(String this_timeline_id){
        TimeLineBean tlb = new TimeLineBean();
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("ê⁄ë±äÆóπ");
            String sql = "select t.USER_ID,u.NICKNAME,u.top_picture,t.TIMELINE_ID,t.TIMELINE_SENTENCE,t.TIMELINE_TIME " +
                    "from TIMELINE_TABLE t left join USER_INFORMATION_TABLE u on t.USER_ID = u.USER_ID left join FRIEND_TABLE f on u.USER_ID = f.FRIEND_ID " +
                    "where t.TIMELINE_id = '"+this_timeline_id+"'";
            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();

            rs.next();
            String user_id = rs.getString(1);
            String name = rs.getString(2);
            Blob blob = rs.getBlob(3);
            String timeline_id = rs.getString(4);
            String timeline_sentence = rs.getString(5);
            String timeline_time = rs.getString(6);
            AcquisitionImage acquisitionImage = new AcquisitionImage();
            String top_picture = acquisitionImage.getImagePath(user_id,timeline_id,blob);
            tlb.setName(name);
            tlb.setUser_id(user_id);
            tlb.setTimeline_id(timeline_id);
            tlb.setTop_picture(top_picture);
            tlb.setTimeline_sentence(timeline_sentence);
            tlb.setTimeline_time(timeline_time);


        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQLÇÃó·äO");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ëSÇƒÇèEÇ§ó·äO");
        }
        return tlb;
    }
}