package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

import dao.function.AcquisitionImage;
import bean.TimeLineBean;
import java.util.ArrayList;
import bean.UserBean;

public class OraTimeLineDao implements TimeLineDao{
    public void addTimeline(TimeLineBean tb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="insert into timeline_table(user_id,timeline_id,timeline_sentence)" +
                    " values(?,timeline_sequence.nextval,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,tb.getUser_id());
            st.setString(2,tb.getTimeline_sentence());
            st.executeUpdate();
            st.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            OracleConnectionManager.getInstance().rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public void addTimeLineLike(TimeLineBean tb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "insert into timeline_like_table(timelline_id,user_id) values(?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,tb.getTimeline_id());
            st.setString(2,tb.getUser_id());
            st.executeUpdate();
            st.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            OracleConnectionManager.getInstance().rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public  void deleteTimeLineLike(TimeLineBean tb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="DELETE FROM timeline_like_table WHERE timelline_id = ? and user_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,tb.getTimeline_id());
            st.setString(2,tb.getUser_id());
            st.executeUpdate();
            st.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            OracleConnectionManager.getInstance().rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public ArrayList getTimeLines(String user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList timelineList = new ArrayList();
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select t.USER_ID,u.NICKNAME,u.top_picture,t.TIMELINE_ID,t.TIMELINE_SENTENCE,t.TIMELINE_TIME,TLT.TIMELLINE_ID " +
                    "from (TIMELINE_TABLE t left join TIMELINE_LIKE_TABLE TLT on t.TIMELINE_ID = TLT.TIMELLINE_ID) " +
                    "left join USER_INFORMATION_TABLE u on t.USER_ID = u.USER_ID left join FRIEND_TABLE f on u.USER_ID = f.FRIEND_ID " +
                    "where f.USER_ID = ? or t.USER_ID = ? or TLT.USER_ID = ? order by t.TIMELINE_TIME desc";

            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            st.setString(2,user_id);
            st.setString(3,user_id);
            rs = st.executeQuery();
            while(rs.next()){
                TimeLineBean tlb = new TimeLineBean();
                user_id = rs.getString(1);
                String name = rs.getString(2);
                Blob blob = rs.getBlob(3);
                String timeline_id = rs.getString(4);
                String timeline_sentence = rs.getString(5);
                String timeline_time = rs.getString(6);
                String timeline_like_id = rs.getString(7);
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String top_picture = acquisitionImage.getImagePath(user_id,timeline_id,blob);

                tlb.setName(name);
                tlb.setUser_id(user_id);
                tlb.setTimeline_id(timeline_id);
                tlb.setTop_picture(top_picture);
                tlb.setTimeline_sentence(timeline_sentence);
                tlb.setTimeline_time(timeline_time);
                tlb.setTimeline_like_id(timeline_like_id);
                timelineList.add(tlb);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            OracleConnectionManager.getInstance().rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return timelineList;
    }
    public TimeLineBean getTimeLine(String timeline_id){
        TimeLineBean tlb = new TimeLineBean();
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select t.USER_ID,u.NICKNAME,u.top_picture,t.TIMELINE_ID,t.TIMELINE_SENTENCE,t.TIMELINE_TIME " +
                    "from TIMELINE_TABLE t left join USER_INFORMATION_TABLE u on t.USER_ID = u.USER_ID left join FRIEND_TABLE f on u.USER_ID = f.FRIEND_ID " +
                    "where t.TIMELINE_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,timeline_id);
            rs = st.executeQuery();
            rs.next();
            tlb.setUser_id(rs.getString(1));
            tlb.setName(rs.getString(2));
            Blob blob = rs.getBlob(3);
            tlb.setTimeline_id(rs.getString(4));
            tlb.setTimeline_sentence(rs.getString(5));
            tlb.setTimeline_time(rs.getString(6));
            AcquisitionImage acquisitionImage = new AcquisitionImage();
            String top_picture = acquisitionImage.getImagePath(rs.getString(1),timeline_id,blob);
            tlb.setTop_picture(top_picture);


        }catch(SQLException e){
            System.out.println(e.getMessage());
            OracleConnectionManager.getInstance().rollback();
        }finally{
            try{
                if(st != null){
                    st.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return tlb;
    }
}