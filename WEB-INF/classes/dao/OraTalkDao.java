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
import java.sql.CallableStatement;
import java.sql.Types;

import dao.function.Base64Image;
import bean.TalkBean;
import bean.TalkPictureBean;
import java.util.ArrayList;

public class OraTalkDao implements TalkDao{
    public void addRead_flag(String chat_id,String user_id){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
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
            System.out.println(count+"Œˆ—‚µ‚Ü‚µ‚½");
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

    public ArrayList getTalk(String chat_id){
        ArrayList talkList = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select t.TALK_ID,t.chat_id,u.USER_ID,t.content,u.TOP_PICTURE,u.nickname,TO_CHAR(t.mess_time,'YYYY/MM/DD HH24:Mi'),ALREADY_READ_FLAG,BLOCK_FLAG,t.talk_picture from TALK_TABLE t\n" +
                    "left join USER_INFORMATION_TABLE u on  u.USER_ID = (select CHAT_TABLE.USER_CHAT_ID from CHAT_TABLE where chat_id = t.chat_id)\n" +
                    "where TALK_ID NOT IN(select TALK_ID from TALK_TABLE where CHAT1_ID = ? and block_flag = 1)\n" +
                    "and t.MESS_TIME >= (select CHAT_TABLE.DELETE_TIME from CHAT_TABLE where chat_id = (select max(chat_id) from CHAT_TABLE where USER_CHAT_ID = (select USER_CHAT_ID from CHAT_TABLE where chat_id = ?)\n" +
                    "and USER_CHAT1_ID = (select USER_CHAT1_ID from CHAT_TABLE where chat_id = ?))) and (t.CHAT_ID = ? or t.CHAT1_ID = ?) " +
                    "order by t.TALK_ID asc";

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
                if(rs.getString(4)!=null){
                    tb.setContent(rs.getString(4).replaceAll("\n", "<br/>"));
                }else{
                    Blob blob = rs.getBlob(10);
                    Base64Image bi = new Base64Image();
                    tb.setContent("<img src='data:image;base64,"+bi.getBase64(blob)+"'");
                }
                Blob blob = rs.getBlob(5);
                Base64Image bi = new Base64Image();
                tb.setImage(bi.getBase64(blob));
                tb.setName(rs.getString(6));
                tb.setMess_time(rs.getString(7));
                if(rs.getInt(8)==1){
                    tb.setRead_flag("Šù“Ç");
                }
                talkList.add(tb);
            }
            rs.close();
            st.close();
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
        return talkList;
    }
    public boolean getBlockJudge(String chat_id,String user_id){
        boolean judge = false;
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
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
            rs.close();
            st.close();
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
        return judge;
    }
    public void addTalk(TalkBean tb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
                    String sql = "INSERT INTO TALK_TABLE (talk_id,chat_id,chat1_id,content,block_flag)\n" +
                            "values(talk_sequesnce.nextval,?,(SELECT CHAT_ID FROM CHAT_TABLE\n" +
                            "where CHAT_ID = (select CHAT_ID from CHAT_TABLE where USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where chat_id = ?)\n" +
                            "and USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where chat_id = ?))),?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,tb.getChat_id());
            st.setString(2,tb.getChat_id());
            st.setString(3,tb.getChat_id());
            st.setString(4,tb.getContent());
            st.setString(5,tb.getBlock_flag());
            int count =st.executeUpdate();
            System.out.println(count+"Œˆ—‚µ‚Ü‚µ‚½");
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
//    public void addTalkPicture(String chat_id,String imagePath){
    public void addTalkPicture(TalkBean tb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            FileInputStream fis = new FileInputStream(tb.getContent());
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO TALK_TABLE (talk_id,chat_id,chat1_id,talk_picture,block_flag)\n" +
                    "values(talk_sequesnce.nextval,?,(SELECT CHAT_ID FROM CHAT_TABLE\n" +
                    "where CHAT_ID = (select CHAT_ID from CHAT_TABLE where USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where chat_id = ?)\n" +
                    "and USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where chat_id = ?))),?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,tb.getChat_id());
            st.setString(2,tb.getChat_id());
            st.setString(3,tb.getChat_id());
            st.setBinaryStream(4,fis);
//            st.setString(4,tb.getContent());
            st.setString(5,tb.getBlock_flag());
            int count =st.executeUpdate();
            System.out.println(count+"Œˆ—‚µ‚Ü‚µ‚½");
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
}