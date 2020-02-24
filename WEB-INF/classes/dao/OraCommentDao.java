package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

//�ʐ^�t�@�C����Blob����쐬���邽�߂̃N���X
import dao.function.Base64Image;
import java.util.ArrayList;
import bean.CommentBean;

//�^�C�����C���ɑ΂���R�����g�f�[�^����舵�����߂�Dao�N���X
public class OraCommentDao implements CommentDao{
    //�R�����g�ɑ΂��邢����
    public void addCommentLike(CommentBean cb){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "insert into timeline_like_table values(?,?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,cb.getTimeline_id());
            st.setString(2,cb.getComment_id());
            st.setString(3,cb.getUser_id());
            st.executeUpdate();
            st.close();
            oc.commit();
            oc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
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
    //�R�����g�ɑ΂��邢���˂��폜����
    public void deleteCommentLike(CommentBean cb){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="DELETE FROM timeline_like_table WHERE timelline_id = ? and comment_id = ? and user_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,cb.getTimeline_id());
            st.setString(2,cb.getComment_id());
            st.setString(3,cb.getUser_id());
            st.executeUpdate();
            st.close();
            oc.commit();
            oc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            oc.rollback();
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
    //�^�C�����C���̃R�����g��ǉ�����
    public void addComment(CommentBean cb){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="insert into comment_table(comment_id,user_id,timeline_id,comment_sentence,reply_user_id) values(COMMENT_SEQUENCE.nextval,?,?,?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,cb.getUser_id());
            st.setString(2,cb.getTimeline_id());
            st.setString(3,cb.getComment_sentence());
            st.setString(4,cb.getReply_user_id());
            st.executeUpdate();
            st.close();
            oc.commit();
            oc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            oc.rollback();
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
    //�^�C�����C���̃R�����g�ꗗ��\������
    public ArrayList getComment(CommentBean cb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList commentList = new ArrayList();
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select u.USER_ID,u.NICKNAME,u.TOP_PICTURE,c.comment_sentence,TO_CHAR(comment_time,'YYYY/MM/DD HH24:Mi'),c.timeline_id,\n" +
                    "(select comment_id from timeline_like_table where TIMELINE_LIKE_TABLE.TIMELLINE_ID = ? and user_id = ? and COMMENT_ID = c.COMMENT_ID)\n" +
                    ",c.COMMENT_ID,\n" +
                    "(select '@'||NICKNAME from USER_INFORMATION_TABLE where USER_ID = c.reply_user_id),\n" +
                    "(select count(*) from timeline_like_table where  TIMELINE_ID = c.timeline_id and COMMENT_ID = c.COMMENT_ID  and COMMENT_ID IS NOT NULL)\n" +
                    "from (COMMENT_TABLE c left join TIMELINE_LIKE_TABLE TLT  on c.COMMENT_ID = TLT.COMMENT_ID and TLT.USER_ID =?)\n" +
                    "left join  USER_INFORMATION_TABLE u on u.USER_ID = c.USER_ID where c.TIMELINE_ID = ?" +
                    "order by comment_time desc";
            st = cn.prepareStatement(sql);
            st.setString(1,cb.getTimeline_id());
            st.setString(2,cb.getUser_id());
            st.setString(3,cb.getUser_id());
            st.setString(4,cb.getTimeline_id());
            rs = st.executeQuery();
            while(rs.next()){
                cb = new CommentBean();
                cb.setUser_id(rs.getString(1));
                cb.setName(rs.getString(2));
                Blob blob = rs.getBlob(3);
                Base64Image bi = new Base64Image();
                cb.setTop_picture(bi.getBase64(blob));
                cb.setComment_sentence(rs.getString(4).replaceAll("\n", "<br/>"));
                cb.setComment_time(rs.getString(5));
                cb.setTimeline_id(rs.getString(6));
                cb.setComment_like_id(rs.getString(7));
                cb.setComment_id(rs.getString(8));
                cb.setReply_user_name(rs.getString(9));
                cb.setComment_like_count(rs.getString(10));
                commentList.add(cb);
            }
            oc.closeConnection();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
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