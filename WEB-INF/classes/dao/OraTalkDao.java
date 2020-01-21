package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Types;
import java.sql.CallableStatement;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import java.sql.CallableStatement;
import java.sql.Types;
import dao.function.AcquisitionImage;
import bean.TalkBean;
import bean.TalkPictureBean;
import java.util.ArrayList;

public class OraTalkDao implements TalkDao{
    public void addRead_flag(String chat_id,String user_id){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="update TALK_TABLE set ALREADY_READ_FLAG = 1 where CHAT_ID = ? and user_id != ?";
            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            st.setString(2,user_id);
            int count = st.executeUpdate();
            System.out.println(count+"åèèàóùÇµÇ‹ÇµÇΩ");
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

    public ArrayList getTalk(String chat_id){
        ArrayList talkList = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select t.chat_id,t.user_id,t.content,u.TOP_PICTURE,u.nickname,u.top_picture,t.mess_time,talk_id,ALREADY_READ_FLAG " +
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
                tb.setTalk_id(rs.getString(8));
                if(rs.getInt(9)==1){
                    tb.setRead_flag("ä˘ì«");
                }
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
    public boolean getBlockJudge(String chat_id,String user_id){
        boolean judge = false;
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select count(*) from FRIEND_TABLE where USER_ID = ? and FRIEND_FLAG = 1 and " +
                    "(FRIEND_ID = (select USER_CHAT_ID from CHAT_TABLE where USER_CHAT1_ID = ? and CHAT_ID = ?) or " +
                    "FRIEND_ID = (select USER_CHAT1_ID from CHAT_TABLE where USER_CHAT_ID = ? and CHAT_ID = ?))";
            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            st.setString(2,user_id);
            st.setString(3,chat_id);
            st.setString(4,user_id);
            st.setString(5,chat_id);
            rs = st.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if(count==1){
                judge = true;
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
        return judge;
    }
    public String addTalk(TalkBean tb){
        CallableStatement stat = null;
        Connection cn = null;
        String id = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "begin\n"
                    +"insert into talk_table(talk_id,chat_id,user_id,content)"
                    +" values(talk_sequesnce.nextval,?,?,?)"
                    + "returning talk_id into ?;\n"
                    + "end;";
            stat = cn.prepareCall(sql);
            stat.setString(1,tb.getChat_id());
            stat.setString(2,tb.getUser_id());
            stat.setString(3,tb.getContent());
            stat.registerOutParameter(4, Types.INTEGER);
            stat.execute();

            id = stat.getString(4);
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
    public void addTalkPicture(String talk_id,String imagePath){
        PreparedStatement st = null;
        Connection cn = null;
        String id = null;
        try{
            FileInputStream fis = new FileInputStream(imagePath);
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="insert into talk_picture_table(talk_id,talk_picture)" +
                    " values(?,?)";
            System.out.println(sql);
            st = cn.prepareStatement(sql);
            st.setString(1,talk_id);
            st.setBinaryStream(2,fis);
            int count = st.executeUpdate();
            System.out.println(count+"åèèàóùÇµÇ‹ÇµÇΩ");
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
    public ArrayList getPicture(String chat_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList talkPictureList = new ArrayList();
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select t.TALK_ID,t.TALK_PICTURE from TALK_PICTURE_TABLE t" +
                    " left join TALK_TABLE TT on t.TALK_ID = TT.TALK_ID where CHAT_ID = ?";

            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            rs = st.executeQuery();
            while(rs.next()){
                TalkPictureBean tpb = new TalkPictureBean();
                String talk_id = rs.getString(1);
                Blob blob = rs.getBlob(2);
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                tpb.setTalk_id(talk_id);
                tpb.setBase64Image(base64Image);
                talkPictureList.add(tpb);
            }
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
        return talkPictureList;
    }
}