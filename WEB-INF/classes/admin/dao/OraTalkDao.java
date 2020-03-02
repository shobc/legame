package admin.dao;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

import dao.function.Base64Image;
import admin.bean.TalkBean;
import java.util.ArrayList;

public class OraTalkDao implements TalkDao{
    public ArrayList getUserTalk(String chat_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList talkList = new ArrayList();
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql = "select u.SEARCH_ID,t.content,TO_CHAR(t.mess_time, 'MM/DD HH24:MI'),TO_CHAR(t.mess_time,'HH24\"時\"MI\"分\"'),u.TOP_PICTURE,t.talk_picture from TALK_TABLE t\n" +
                    "                    left join USER_INFORMATION_TABLE u\n" +
                    "                    on u.USER_ID = (select CHAT_TABLE.USER_CHAT_ID from CHAT_TABLE where chat_id = t.chat_id)\n" +
                    "                    where t.CHAT1_ID = ? order by t.mess_time asc";
            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            rs = st.executeQuery();
            while(rs.next()){
                TalkBean tb = new TalkBean();
                tb.setSearch_id(rs.getString(1));
                if(rs.getString(2)!=null){
                    tb.setContent(""+rs.getString(2)+"");
                }else{
                    Blob blob = rs.getBlob(6);
                    Base64Image bi = new Base64Image();
                    tb.setContent("<img src='data:image;base64,"+bi.getBase64(blob)+"'>");
                }
                tb.setDate(rs.getString(3));
                tb.setTime(rs.getString(4));
                Blob blob = rs.getBlob(5);
                Base64Image bi = new Base64Image();
                tb.setTop_picture(bi.getBase64(blob));
                talkList.add(tb);
            }
            aoc.closeConnection();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
        return talkList;
    }
    public void sendMessage(String user_id){
        PreparedStatement st = null;
        Connection cn = null;
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql="insert into TALK_TABLE(talk_id, chat_id, chat1_id, content) \n" +
                    "values(TALK_SEQUESNCE.nextval,(select CHAT_TABLE.CHAT_ID from CHAT_TABLE where USER_CHAT_ID = 0 and USER_CHAT1_ID = ?)\n" +
                    ",(select CHAT_TABLE.CHAT_ID from CHAT_TABLE where USER_CHAT_ID = ? and USER_CHAT1_ID = 0)\n" +
                    ",'不適切なタイムラインの内容があったため削除しました')";
            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            st.setString(2,user_id);
            int count = st.executeUpdate();
            System.out.println(count+"件処理しました");
            st.close();
            aoc.commit();
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
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
}