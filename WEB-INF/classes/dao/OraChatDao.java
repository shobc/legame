package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

import dao.function.Base64Image;

import bean.ChatBean;
import java.util.ArrayList;

//チャットのデータを取り扱うためのDaoクラス
public class OraChatDao implements ChatDao{
    //チャットを追加する
    public void addChat(ChatBean cb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            if(cb.getFriend_id()!=null){
                String sql="insert into chat_table(chat_id,user_chat_id,user_chat1_id) values(chat_sequesnce.nextval,?,?)";
                st = cn.prepareStatement(sql);
                st.setString(1,cb.getUser_id());
                st.setString(2,cb.getFriend_id());
            }else{
                String sql="insert into chat_table(chat_id,user_chat_id,user_chat1_id) " +
                        "values(chat_sequesnce.nextval,(select user_chat1_id from chat_table where chat_id = ? and user_chat_id = ?),?)";
                st = cn.prepareStatement(sql);
                st.setString(1,cb.getChat_id());
                st.setString(2,cb.getUser_id());
                st.setString(3,cb.getUser_id());
            }
            int count = st.executeUpdate();
            System.out.println(count+"件処理しました");
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
    //チャットが作られているか判定する
    public boolean getJudge(ChatBean cb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        boolean judge = false;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            if(cb.getFriend_id()!=null){
                String sql = "select count(chat_id) from chat_table " +
                        "where user_chat_id= ? and user_chat1_id = ?";
                st = cn.prepareStatement(sql);
                st.setString(1,cb.getUser_id());
                st.setString(2,cb.getFriend_id());
                System.out.println("上");
            }else{
                String sql = "select count(chat_id) from chat_table " +
                        "where user_chat1_id = ? and user_chat_id = " +
                        "(select user_chat1_id from chat_table where chat_id = ?)";
                st = cn.prepareStatement(sql);
                st.setString(1,cb.getUser_id());
                System.out.println(cb.getUser_id());
                st.setString(2,cb.getChat_id());
                System.out.println(cb.getChat_id());
                System.out.println("下");
            }
            rs = st.executeQuery();
            rs.next();
            String j = rs.getString(1);
            System.out.println("j="+j);
            //０だった場合チャットルームを作成する
            if(j.equals("0")){
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
    //チャットのIdを取得する
    public String getChatId(ChatBean cb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        String chat_id = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select chat_id from chat_table " +
                    "where user_chat_id= ?  and user_chat1_id = ?";

            st = cn.prepareStatement(sql);
            st.setString(1,cb.getUser_id());
            st.setString(2,cb.getFriend_id());
            rs = st.executeQuery();
            rs.next();
            chat_id = rs.getString(1);
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
        return chat_id;
    }
    //チャット一覧を取得する
    public ArrayList getChat(String user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList chatList = new ArrayList();
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select u.user_id,u.nickname,u.top_picture,c.chat_id\n" +
                    ",(select NVL(CONTENT,'画像があります') from TALK_TABLE where TALK_ID = (select MAX(TALK_ID) from TALK_TABLE where (CHAT_ID = c.chat_id and CHAT1_ID =\n" +
                    "(select CHAT_ID from CHAT_TABLE where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID) and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID)))\n" +
                    "or  (CHAT_ID = (select CHAT_ID from CHAT_TABLE where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID) and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID)) and  CHAT1_ID = c.chat_id))\n" +
                    "and BLOCK_FLAG = 0 )\n" +
                    ",(select count(TALK_TABLE.TALK_ID) from TALK_TABLE where ALREADY_READ_FLAG = 0 and BLOCK_FLAG = 0 and \n" +
                    "(CHAT_ID = (select CHAT_ID from CHAT_TABLE where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID) and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = c.CHAT_ID)) and  CHAT1_ID = c.chat_id))\n" +
                    "from CHAT_TABLE c left join USER_INFORMATION_TABLE u\n" +
                    "on c.USER_CHAT1_ID = u.USER_ID where c.USER_CHAT_ID = ? and delete_flag = 0";

            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            rs = st.executeQuery();
            while(rs.next()){
                ChatBean cb = new ChatBean();
                cb.setUser_id(rs.getString(1));
                cb.setName(rs.getString(2));
                Blob blob = rs.getBlob(3);
                Base64Image bi = new Base64Image();
                cb.setTop_picture(bi.getBase64(blob));
                cb.setChat_id(rs.getString(4));
                cb.setContent(rs.getString(5));
                cb.setNot_read_count(rs.getString(6));
                chatList.add(cb);
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
        return chatList;
    }
    public void deleteChat(String chat_id){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="update chat_table set delete_flag = 1,delete_time = sysdate where chat_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            int count = st.executeUpdate();
            System.out.println(count+"件処理しました");
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
    public boolean deleteJudge(ChatBean cb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        boolean judge = false;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
           if(cb.getFriend_id()!=null){
               String sql = "select count(chat_id) from chat_table\n" +
                       "where chat_id = (select CHAT_ID from CHAT_TABLE where USER_CHAT_ID =(select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?) and USER_CHAT1_ID =(select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = ?))  and user_chat1_id = ? and delete_flag = 1";
               st = cn.prepareStatement(sql);
               st.setString(1,cb.getChat_id());
               st.setString(2,cb.getChat_id());
               st.setString(3,cb.getFriend_id());
           }else{
               String sql = "select count(chat_id) from chat_table\n" +
                       "where chat_id = ? and user_chat_id = ? and delete_flag = 1";
               st = cn.prepareStatement(sql);
               st.setString(1,cb.getChat_id());
               st.setString(2,cb.getUser_id());
           }
            rs = st.executeQuery();
            rs.next();
            if(rs.getInt(1)==1){
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

    public void updateDeleteFlag(ChatBean cb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            if(cb.getFriend_id()!=null){
                String sql="update chat_table set delete_flag = 0 ,delete_time = sysdate where chat_id = (select CHAT_ID " +
                    "from CHAT_TABLE where USER_CHAT_ID =(select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?) " +
                    "and USER_CHAT1_ID =(select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = ?))  and user_chat1_id = ?";
                st = cn.prepareStatement(sql);
                st.setString(1,cb.getChat_id());
                st.setString(2,cb.getChat_id());
                st.setString(3,cb.getFriend_id());
            }else{
                String sql="update chat_table set delete_flag = 0 ,delete_time = sysdate where chat_id = ? and user_chat_id = ?";
                st = cn.prepareStatement(sql);
                st.setString(1,cb.getChat_id());
                st.setString(2,cb.getUser_id());
            }
            int count = st.executeUpdate();
            System.out.println(count+"件処理しました");
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
    public boolean blockJudge(ChatBean cb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        boolean judge = true;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select count(friend_table.friend_id) from friend_table where friend_flag = 1 " +
                         "and friend_id = ? and user_id = (select CHAT_TABLE.USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?)";

            st = cn.prepareStatement(sql);
            st.setString(1,cb.getFriend_id());
            st.setString(2,cb.getChat_id());
            rs = st.executeQuery();
            rs.next();
            if(rs.getInt(1)==1){
                judge = false;
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
    public String getReceiverChatId(String chat_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        String receiver_chat_id = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select chat_id from CHAT_TABLE where CHAT_ID = (select CHAT_ID from CHAT_TABLE \n" +
                    "where USER_CHAT1_ID = (select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = ?) \n" +
                    "and USER_CHAT_ID = (select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?))";

            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            st.setString(2,chat_id);
            rs = st.executeQuery();
            rs.next();
            receiver_chat_id = rs.getString(1);
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
        return receiver_chat_id;
    }
}