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
            String sql="update TALK_TABLE set ALREADY_READ_FLAG = 1\n" +
                    "where TALK_ID IN(select TALK_ID from TALK_TABLE where block_flag = 0)\n" +
                    "and MESS_TIME>=(select CHAT_TABLE.DELETE_TIME from CHAT_TABLE where chat_id =?)\n" +
                    "and CHAT1_ID = ? and CHAT_ID =\n" +
                    "(select CHAT_TABLE.CHAT_ID from CHAT_TABLE where USER_CHAT_ID =\n" +
                    "(select USER_CHAT1_ID from CHAT_TABLE where CHAT_TABLE.CHAT_ID = ?)\n" +
                    "and USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_TABLE.CHAT_ID = ?))";
            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            st.setString(2,chat_id);
            st.setString(3,chat_id);
            st.setString(4,chat_id);
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
            String sql = "select t.TALK_ID,t.chat_id,u.USER_ID,t.content,u.TOP_PICTURE,u.nickname,t.mess_time,ALREADY_READ_FLAG,BLOCK_FLAG from TALK_TABLE t\n" +
                    "left join USER_INFORMATION_TABLE u on  u.USER_ID = (select CHAT_TABLE.USER_CHAT_ID from CHAT_TABLE where chat_id = t.chat_id)\n" +
                    "where TALK_ID NOT IN(select TALK_ID from TALK_TABLE where CHAT1_ID = ? and block_flag = 1)\n" +
                    "and t.MESS_TIME >= (select CHAT_TABLE.DELETE_TIME from CHAT_TABLE where chat_id = (select max(chat_id) from CHAT_TABLE where USER_CHAT_ID = (select USER_CHAT_ID from CHAT_TABLE where chat_id = ?)\n" +
                    "and USER_CHAT1_ID = (select USER_CHAT1_ID from CHAT_TABLE where chat_id = ?))) and (t.CHAT_ID = ? or t.CHAT1_ID = ?)";

            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            st.setString(2,chat_id);
            st.setString(3,chat_id);
            st.setString(4,chat_id);
            st.setString(5,chat_id);
            rs = st.executeQuery();
            while(rs.next()){
                TalkBean tb = new TalkBean();
                tb.setTalk_id(rs.getString(1));
                tb.setChat_id(rs.getString(2));
                tb.setUser_id(rs.getString(3));
                tb.setContent(rs.getString(4));
                Blob blob = rs.getBlob(5);
                tb.setName(rs.getString(6));
                tb.setMess_time(rs.getString(7));
                if(rs.getInt(8)==1){
                    tb.setRead_flag("ä˘ì«");
                }
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String contentPicture = acquisitionImage.getImagePath(rs.getString(2),chat_id+1,blob);
                tb.setImage(contentPicture);

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
                    String sql = "begin\n" +
                    "INSERT INTO TALK_TABLE (talk_id,chat_id,chat1_id,content,block_flag)\n" +
                            "values(talk_sequesnce.nextval,?,(SELECT CHAT_ID FROM CHAT_TABLE\n" +
                            "where CHAT_ID = (select CHAT_ID from CHAT_TABLE where USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where chat_id = ?) and USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where chat_id = ?))),?,?)\n" +
                            "returning talk_id into ?;\n" +
                    "end;";
            stat = cn.prepareCall(sql);
            stat.setString(1,tb.getChat_id());
            stat.setString(2,tb.getChat_id());
            stat.setString(3,tb.getChat_id());
            stat.setString(4,tb.getContent());
            stat.setString(5,tb.getBlock_flag());
            stat.registerOutParameter(6, Types.INTEGER);
            stat.execute();

            id = stat.getString(6);
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
    public boolean blockJudge(String chat_id){
        boolean judge = false;
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select count(user_id) from friend_table where user_id = " +
                    "(select CHAT_TABLE.USER_CHAT1_ID from CHAT_TABLE where chat_id = ?) " +
                    "and friend_id = (select CHAT_TABLE.USER_CHAT_ID from CHAT_TABLE where chat_id = ?) and friend_flag = 1";
            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            st.setString(2,chat_id);
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
}