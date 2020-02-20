package admin.dao;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

import dao.function.Base64Image;
import admin.bean.TimeLineBean;
import admin.bean.TimeLinePictureBean;
import java.util.ArrayList;

public class OraTimeLineDao implements TimeLineDao{
    public ArrayList getAllTimeLine(){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList timelineList = new ArrayList();
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{

            cn = aoc.getConnection();
            String sql = "select t.TIMELINE_ID,u.NICKNAME,u.top_picture,t.TIMELINE_SENTENCE,t.TIMELINE_TIME\n" +
                    "from TIMELINE_TABLE t left join USER_INFORMATION_TABLE u on t.USER_ID = u.USER_ID" +
                    " order by t.TIMELINE_TIME desc";
            st = cn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                TimeLineBean tlb = new TimeLineBean();
                tlb.setTimeline_id(rs.getString(1));
                tlb.setName(rs.getString(2));
                Blob blob = rs.getBlob(3);
                System.out.println("blob"+blob);
                Base64Image bi = new Base64Image();
                tlb.setTop_picture(bi.getBase64(blob));
                tlb.setTimeline_sentence(rs.getString(4));
                tlb.setTime(rs.getString(5));
                timelineList.add(tlb);
            }
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
    public ArrayList getReportTimeLine(){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList timelineList = new ArrayList();
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{

            cn = aoc.getConnection();
            String sql = "select t.TIMELINE_ID,u.NICKNAME,u.top_picture,t.TIMELINE_SENTENCE,t.TIMELINE_TIME,(select count(*) from TIMELINE_REPORT_TABLE where TIMELINE_ID = t.TIMELINE_ID)\n" +
                    "from TIMELINE_TABLE t left join USER_INFORMATION_TABLE u on t.USER_ID = u.USER_ID\n" +
                    "where t.TIMELINE_ID = (select distinct TIMELINE_ID from TIMELINE_REPORT_TABLE)\n" +
                    "order by t.TIMELINE_TIME desc";
            st = cn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                TimeLineBean tlb = new TimeLineBean();
                tlb.setTimeline_id(rs.getString(1));
                tlb.setName(rs.getString(2));
                Blob blob = rs.getBlob(3);
                Base64Image bi = new Base64Image();
                tlb.setTop_picture(bi.getBase64(blob));
                tlb.setTimeline_sentence(rs.getString(4));
                tlb.setTime(rs.getString(5));
                tlb.setReport_count(rs.getString(6));
                timelineList.add(tlb);
            }
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
    public ArrayList getTimelinePicture(){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList timelinePictureList = new ArrayList();
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{

            cn = aoc.getConnection();
            String sql = "select tp.TIMELINE_ID,tp.TIMELINE_PICTURE from TIMELINE_PICTURE_TABLE tp\n" +
                    "left join TIMELINE_TABLE t on tp.TIMELINE_ID = t.TIMELINE_ID\n" +
                    "left join USER_INFORMATION_TABLE u on t.USER_ID = u.USER_ID\n" +
                    "order by t.TIMELINE_TIME desc";
            st = cn.prepareStatement(sql);
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
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
    public ArrayList getReportTimelinePicture(){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList timelinePictureList = new ArrayList();
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{

            cn = aoc.getConnection();
            String sql = "select tp.TIMELINE_ID,tp.TIMELINE_PICTURE from TIMELINE_PICTURE_TABLE tp\n" +
                    "left join TIMELINE_TABLE t on tp.TIMELINE_ID = t.TIMELINE_ID\n" +
                    "left join USER_INFORMATION_TABLE u on t.USER_ID = u.USER_ID\n" +
                    "where t.TIMELINE_ID = (select distinct TIMELINE_ID from TIMELINE_REPORT_TABLE)\n" +
                    "order by t.TIMELINE_TIME desc";
            st = cn.prepareStatement(sql);
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
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
    public String getUser_id(String timeline_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        String user_id = null;
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{

            cn = aoc.getConnection();
            String sql = "select user_id from timeline_table where timeline_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,timeline_id);
            rs = st.executeQuery();
            rs.next();
            user_id = rs.getString(1);
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
        return user_id;
    }
    public void DeleteTimeLine(String timeline_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql = "delete timeline_table where timeline_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,timeline_id);
            int count = st.executeUpdate();
            System.out.println(count+"åèèàóùÇµÇ‹ÇµÇΩ");
            aoc.commit();
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
    public boolean deleteTimeLineJudge(String timeline_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        boolean judge = true;
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{

            cn = aoc.getConnection();
            String sql = "select count(*) from timeline_table where timeline_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,timeline_id);
            rs = st.executeQuery();
            rs.next();
            if(rs.getInt(1)==0){
                judge = false;
            }
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
        return judge;
    }
}