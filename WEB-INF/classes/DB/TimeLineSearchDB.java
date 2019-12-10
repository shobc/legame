package DB;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Blob;

import DB.function.AcquisitionImage;
import Bean.TimeLineBean;
import DB.selectSentence.SelectPutTogether;

public class TimeLineSearchDB{
    public ArrayList searchTimeLime(String my_user_id){
        ArrayList timelineArray = new ArrayList();
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("ê⁄ë±äÆóπ");
            String sql = "select t.USER_ID,u.NICKNAME,u.top_picture,t.TIMELINE_ID,t.TIMELINE_SENTENCE,t.TIMELINE_TIME,TLT.TIMELLINE_ID " +
                    "from (TIMELINE_TABLE t left join TIMELINE_LIKE_TABLE TLT on t.TIMELINE_ID = TLT.TIMELLINE_ID) " +
                    "left join USER_INFORMATION_TABLE u on t.USER_ID = u.USER_ID left join FRIEND_TABLE f on u.USER_ID = f.FRIEND_ID " +
                    "where f.USER_ID = '"+my_user_id+"' or t.USER_ID = '"+my_user_id+"' or TLT.USER_ID = '"+my_user_id+"' order by t.TIMELINE_TIME desc";

            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();

            while(rs.next()){
                String user_id = rs.getString(1);
                String name = rs.getString(2);
                Blob blob = rs.getBlob(3);
                String timeline_id = rs.getString(4);
                String timeline_sentence = rs.getString(5);
                String timeline_time = rs.getString(6);
                String timeline_like_id = rs.getString(7);
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String top_picture = acquisitionImage.getImagePath(user_id,timeline_id,blob);
                TimeLineBean tlb = new TimeLineBean();
                tlb.setName(name);
                tlb.setUser_id(user_id);
                tlb.setTimeline_id(timeline_id);
                tlb.setTop_picture(top_picture);
                tlb.setTimeline_sentence(timeline_sentence);
                tlb.setTimeline_time(timeline_time);
                tlb.setTimeline_like_id(timeline_like_id);
                timelineArray.add(tlb);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQLÇÃó·äO");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ëSÇƒÇèEÇ§ó·äO");
        }
        return timelineArray;
    }
}