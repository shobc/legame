package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

//写真ファイルをBlobから作成するためのクラス
import dao.function.AcquisitionImage;
import java.util.ArrayList;
import bean.CommentBean;

public class OraCommentDao implements CommentDao{
    //タイムラインのコメントを追加する
    public void addComment(CommentBean cb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="insert into comment_table(comment_id,user_id,timeline_id,comment_sentence) values(COMMENT_SEQUENCE.nextval,?,?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,cb.getUser_id());
            st.setString(2,cb.getTimeline_id());
            st.setString(3,cb.getComment_sentence());
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
    //タイムラインのコメント一覧を表示する
    public ArrayList getComment(String timeline_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList commentList = new ArrayList();
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql= "select u.USER_ID,u.NICKNAME,u.TOP_PICTURE,c.comment_sentence,comment_time from USER_INFORMATION_TABLE u left join COMMENT_TABLE c " +
                    "on u.USER_ID = c.USER_ID where c.TIMELINE_ID = ? ";
            st = cn.prepareStatement(sql);
            st.setString(1,timeline_id);
            rs = st.executeQuery();
            while(rs.next()){
                CommentBean cb = new CommentBean();
                cb.setUser_id(rs.getString(1));
                cb.setName(rs.getString(2));
                Blob blob = rs.getBlob(3);
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String top_picture = acquisitionImage.getImagePath(rs.getString(1),timeline_id,blob);
                cb.setTop_picture(top_picture);
                cb.setComment_sentence(rs.getString(4));
                cb.setComment_time(rs.getString(5));
                commentList.add(cb);
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
}