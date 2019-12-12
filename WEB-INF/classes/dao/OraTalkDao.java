package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

import dao.function.AcquisitionImage;
import bean.TalkBean;
import java.util.ArrayList;

public class OraTalkDao implements TalkDao{
    public ArrayList getTalk(String chat_id){
        ArrayList talkList = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select t.chat_id,t.user_id,t.content,u.TOP_PICTURE,u.nickname,u.top_picture,t.mess_time " +
                    "from talk_table t left join user_information_table u on t.user_id = u.user_id " +
                    "where t.chat_id = ? ";

            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            rs = st.executeQuery();
            while(rs.next()){
                TalkBean tb = new TalkBean();
                chat_id = rs.getString(1);
                tb.setUser_id(rs.getString(2));
                tb.setContent(rs.getString(3));
                Blob blob = rs.getBlob(4);
                tb.setName(rs.getString(5));
                Blob blobTop_Picture = rs.getBlob(6);
                tb.setMess_time(rs.getString(7));
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String contentPicture = acquisitionImage.getImagePath(rs.getString(2),chat_id+1,blob);
                String top_picture = acquisitionImage.getImagePath(rs.getString(2),chat_id,blobTop_Picture);
                tb.setImage(contentPicture);
                tb.setTop_picture(top_picture);

                talkList.add(tb);
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
        return talkList;
    }
    public void addTalk(TalkBean tb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="insert into talk_table(chat_id,user_id,content) values(?,?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,tb.getChat_id());
            st.setString(2,tb.getUser_id());
            st.setString(3,tb.getContent());
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
}