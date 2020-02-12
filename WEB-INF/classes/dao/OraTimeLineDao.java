package dao;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Types;

import dao.function.Base64Image;
import bean.TimeLineBean;
import bean.TimeLinePictureBean;
import java.util.ArrayList;
import bean.UserBean;

public class OraTimeLineDao implements TimeLineDao{
    public String addTimeline(TimeLineBean tb){
        CallableStatement stat = null;
        Connection cn = null;
        String id = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "begin\n"
                    +"insert into timeline_table(user_id,timeline_id,timeline_sentence)"
                    + " values(?,timeline_sequence.nextval,?) "
                    + "returning timeline_id into ?;\n"
                    + "end;";
            stat = cn.prepareCall(sql);
            stat.setString(1,tb.getUser_id());
            stat.setString(2,tb.getTimeline_sentence());
            stat.registerOutParameter(3, Types.INTEGER);
            stat.execute();

            id = stat.getString(3);
            System.out.println(id);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            OracleConnectionManager.getInstance().rollback();
        }finally{
            try{
                if(stat != null){
                    stat.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return id;
    }

    public void addTimelinePicture(String timeline_id,String imagePath){
        PreparedStatement st = null;
        Connection cn = null;
        String id = null;
        try{
            FileInputStream fis = new FileInputStream(imagePath);
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="insert into timeline_picture_table(timeline_id,timeline_picture)" +
                    " values(?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,timeline_id);
            st.setBinaryStream(2,fis);
            st.executeUpdate();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            OracleConnectionManager.getInstance().rollback();
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
            int count = st.executeUpdate();
            System.out.println(count+"�����������܂���");
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
            String sql="DELETE FROM timeline_like_table WHERE timelline_id = ? and user_id = ? and comment_id is null";
            st = cn.prepareStatement(sql);
            st.setString(1,tb.getTimeline_id());
            st.setString(2,tb.getUser_id());
            int count = st.executeUpdate();
            System.out.println(count+"�����������܂���");
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
            String sql = "select t.USER_ID,u.NICKNAME,u.top_picture,t.TIMELINE_ID,t.TIMELINE_SENTENCE,t.TIMELINE_TIME,TLT.TIMELLINE_ID,(select count(*) from TIMELINE_LIKE_TABLE where TIMELLINE_ID = t.TIMELINE_ID and COMMENT_ID IS NULL)\n" +
                    "                        from (TIMELINE_TABLE t left join TIMELINE_LIKE_TABLE TLT  on t.TIMELINE_ID = TLT.TIMELLINE_ID and TLT.USER_ID = ? and TLT.COMMENT_ID IS NULL)\n" +
                    "                       left join USER_INFORMATION_TABLE u on t.USER_ID = u.USER_ID\n" +
                    "                       left join FRIEND_TABLE f on u.USER_ID = f.FRIEND_ID and f.FRIEND_FLAG = 0  and f.USER_ID = ?\n" +
                    "                       where f.FRIEND_ID NOT IN(select FRIEND_TABLE.USER_ID from FRIEND_TABLE where FRIEND_FLAG = 1 and FRIEND_TABLE.FRIEND_ID = ?)\n" +
                    "                       and t.user_id IN (select USER_ID from FRIEND_TABLE where user_id IN(select FRIEND_ID from FRIEND_TABLE where user_id = ?) and FRIEND_ID = ?)\n" +
                    "                       and  f.USER_ID = ? or t.USER_ID = ?\n" +
                    "                        order by t.TIMELINE_TIME desc";

            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            st.setString(2,user_id);
            st.setString(3,user_id);
            st.setString(4,user_id);
            st.setString(5,user_id);
            st.setString(6,user_id);
            st.setString(7,user_id);
            rs = st.executeQuery();
            while(rs.next()){
                TimeLineBean tlb = new TimeLineBean();
                tlb.setUser_id(rs.getString(1));
                tlb.setName(rs.getString(2));
                Blob blob = rs.getBlob(3);
                Base64Image bi = new Base64Image();
                tlb.setTop_picture(bi.getBase64(blob));
                tlb.setTimeline_id(rs.getString(4));
                tlb.setTimeline_sentence(rs.getString(5));
                tlb.setTimeline_time(rs.getString(6));
                tlb.setTimeline_like_id(rs.getString(7));
                System.out.println("rs.getString(8)"+rs.getString(8));
                tlb.setLike_count(rs.getString(8));
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
    public ArrayList getTimelinePicture(String user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList timelinePictureList = new ArrayList();
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select tp.TIMELINE_ID,tp.TIMELINE_PICTURE from TIMELINE_PICTURE_TABLE tp\n" +
                    "                    left join TIMELINE_TABLE t on tp.TIMELINE_ID = t.TIMELINE_ID\n" +
                    "                    left join USER_INFORMATION_TABLE u on t.USER_ID = u.USER_ID\n" +
                    "                    left join FRIEND_TABLE f on u.USER_ID = f.FRIEND_ID and f.FRIEND_FLAG = 0  and f.USER_ID = ?\n" +
                    "                    and f.FRIEND_ID NOT IN(select FRIEND_TABLE.USER_ID from FRIEND_TABLE where FRIEND_FLAG = 1 and FRIEND_TABLE.FRIEND_ID = ?)\n" +
                    "                    and t.user_id IN (select USER_ID from FRIEND_TABLE where user_id IN(select FRIEND_ID from FRIEND_TABLE where user_id = ?) and FRIEND_ID = ?)\n" +
                    "                    where f.USER_ID = ? or t.USER_ID = ? order by t.TIMELINE_TIME desc";

            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            st.setString(2,user_id);
            st.setString(3,user_id);
            st.setString(4,user_id);
            st.setString(5,user_id);
            st.setString(6,user_id);
            rs = st.executeQuery();
            while(rs.next()){
                TimeLinePictureBean tlpb = new TimeLinePictureBean();
                String timeline_id = rs.getString(1);
                Blob blob = rs.getBlob(2);
                Base64Image bi = new Base64Image();
                tlpb.setBase64Image(bi.getBase64(blob));
                tlpb.setTimeline_id(timeline_id);
                timelinePictureList.add(tlpb);
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
        return timelinePictureList;
    }

    public TimeLineBean getTimeLine(TimeLineBean tlb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select t.USER_ID,u.NICKNAME,u.top_picture,t.TIMELINE_ID,t.TIMELINE_SENTENCE,t.TIMELINE_TIME,TLT.TIMELLINE_ID " +
                    "from (TIMELINE_TABLE t left join TIMELINE_LIKE_TABLE TLT  on t.TIMELINE_ID = TLT.TIMELLINE_ID and TLT.USER_ID = ? and TLT.COMMENT_ID IS NULL) " +
                    "left join USER_INFORMATION_TABLE u on t.USER_ID = u.USER_ID " +
                    "left join FRIEND_TABLE f on u.USER_ID = f.FRIEND_ID where t.TIMELINE_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,tlb.getUser_id());
            st.setString(2,tlb.getTimeline_id());
            rs = st.executeQuery();
            rs.next();
            tlb.setUser_id(rs.getString(1));
            tlb.setName(rs.getString(2));
            Blob blob = rs.getBlob(3);
            Base64Image bi = new Base64Image();
            tlb.setTop_picture(bi.getBase64(blob));
            tlb.setTimeline_id(rs.getString(4));
            tlb.setTimeline_sentence(rs.getString(5));
            tlb.setTimeline_time(rs.getString(6));
            tlb.setTimeline_like_id(rs.getString(7));


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
    public ArrayList getCommentNotice(String user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList commentList = new ArrayList();
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select c.timeline_id,(select USER_INFORMATION_TABLE.NICKNAME from USER_INFORMATION_TABLE where user_id = c.user_id),c.comment_time " +
                         " from comment_table c where reply_user_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            rs = st.executeQuery();
            while(rs.next()){
                TimeLineBean tlb = new TimeLineBean();
                String timeline_id = rs.getString(1);
                String name = rs.getString(2);
                String timeline_time = rs.getString(3);
                tlb.setName(name);
                tlb.setTimeline_id(timeline_id);
                tlb.setTimeline_time(timeline_time);
                commentList.add(tlb);
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
        return commentList;
    }
    public String getCountNotice(String user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        String count = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select count(TIMELINE_ID) from TIMELINE_TABLE " +
                    "where TIMELINE_ID IN(select comment_table.timeline_id from comment_table where reply_user_id = ? and read_flag = 0)";
            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            rs = st.executeQuery();
            rs.next();
            if(rs.getInt(1)!=0){
                count = "new";
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
        return count;
    }
    public void updateCommentNotice(String user_id){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="update comment_table set read_flag = 1 where REPLY_USER_ID = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            int count = st.executeUpdate();
            System.out.println(count+"���������܂���");
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
    public ArrayList getMyTimeLines(String user_id,String friend_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList timelineList = new ArrayList();
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select t.USER_ID,u.NICKNAME,u.top_picture,t.TIMELINE_ID,t.TIMELINE_SENTENCE,t.TIMELINE_TIME,TLT.TIMELLINE_ID\n" +
                    "from (TIMELINE_TABLE t left join TIMELINE_LIKE_TABLE TLT  on t.TIMELINE_ID = TLT.TIMELLINE_ID and TLT.USER_ID = ? and TLT.COMMENT_ID IS NULL)\n" +
                    "left join USER_INFORMATION_TABLE u on t.USER_ID = u.user_id\n" +
                    "where t.USER_ID = (select FRIEND_TABLE.FRIEND_ID from FRIEND_TABLE where user_id = ? and FRIEND_ID = ? and FRIEND_FLAG = 0)\n" +
                    "  and u.USER_ID = (select FRIEND_TABLE.FRIEND_ID from FRIEND_TABLE where user_id = ? and FRIEND_ID = ? and FRIEND_FLAG = 0)\n" +
                    "order by t.TIMELINE_TIME desc";
            st = cn.prepareStatement(sql);
            st.setString(1,friend_id);
            st.setString(2,user_id);
            st.setString(3,friend_id);
            st.setString(4,user_id);
            st.setString(5,friend_id);
            rs = st.executeQuery();
            while(rs.next()){
                TimeLineBean tlb = new TimeLineBean();
                tlb.setUser_id(rs.getString(1));
                tlb.setName(rs.getString(2));
                Blob blob = rs.getBlob(3);
                Base64Image bi = new Base64Image();
                tlb.setTop_picture(bi.getBase64(blob));
                tlb.setTimeline_id(rs.getString(4));
                tlb.setTimeline_sentence(rs.getString(5));
                tlb.setTimeline_time(rs.getString(6));
                tlb.setTimeline_like_id(rs.getString(7));
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
    public ArrayList getMyTimelinePicture(String user_id,String friend_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList timelinePictureList = new ArrayList();
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select tp.TIMELINE_ID,tp.TIMELINE_PICTURE from TIMELINE_PICTURE_TABLE tp\n" +
                    "left join TIMELINE_TABLE t on tp.TIMELINE_ID = t.TIMELINE_ID\n" +
                    "left join USER_INFORMATION_TABLE u on t.USER_ID = u.USER_ID\n" +
                    "where t.USER_ID = (select FRIEND_TABLE.FRIEND_ID from FRIEND_TABLE where user_id = ? and FRIEND_ID = ? and FRIEND_FLAG = 0)\n" +
                    "and t.USER_ID = (select FRIEND_TABLE.FRIEND_ID from FRIEND_TABLE where user_id = ? and FRIEND_ID = ? and FRIEND_FLAG = 0)\n" +
                    "order by t.TIMELINE_TIME desc";

            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            st.setString(2,friend_id);
            st.setString(3,user_id);
            st.setString(4,friend_id);
            rs = st.executeQuery();
            while(rs.next()){
                TimeLinePictureBean tlpb = new TimeLinePictureBean();
                String timeline_id = rs.getString(1);
                Blob blob = rs.getBlob(2);
                Base64Image bi = new Base64Image();
                tlpb.setBase64Image(bi.getBase64(blob));
                tlpb.setTimeline_id(timeline_id);
                timelinePictureList.add(tlpb);
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
        return timelinePictureList;
    }
    public void deleteTimeLine(String timeline_id){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="delete timeline_table where timeline_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,timeline_id);
            int count = st.executeUpdate();
            System.out.println(count+"���������܂���");
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
}