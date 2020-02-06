package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

//写真ファイルをBlobから作成するためのクラス
import dao.function.AcquisitionImage;
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
            String sql="insert into chat_table(chat_id,user_chat_id,user_chat1_id) values(chat_sequesnce.nextval,?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,cb.getUser_id());
            st.setString(2,cb.getFriend_id());
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
            String sql = "select count(chat_id) from chat_table " +
                    "where user_chat_id= ? and user_chat1_id = ?";

            st = cn.prepareStatement(sql);
            st.setString(1,cb.getUser_id());
            st.setString(2,cb.getFriend_id());
            rs = st.executeQuery();
            rs.next();
            String j = rs.getString(1);
            //０だった場合チャットルームを作成する
            if(j.equals("0")){
                judge = true;
                System.out.println("j="+j+"judge="+judge);
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
            String sql = "select u.user_id,u.nickname,u.top_picture,c.chat_id from CHAT_TABLE c left join USER_INFORMATION_TABLE u "+
                    " on c.USER_CHAT1_ID = u.USER_ID where c.USER_CHAT_ID = ? and delete_flag = 0";

            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            rs = st.executeQuery();
            while(rs.next()){
                ChatBean cb = new ChatBean();
                cb.setUser_id(rs.getString(1));
                cb.setName(rs.getString(2));
                Blob blob = rs.getBlob(3);
                cb.setChat_id(rs.getString(4));
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String top_picture = acquisitionImage.getImagePath(rs.getString(2),rs.getString(1),blob);
                cb.setTop_picture(top_picture);
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
            String sql = "select count(chat_id) from chat_table\n" +
                    "where chat_id = (select CHAT_ID from CHAT_TABLE where USER_CHAT_ID =(select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?) and USER_CHAT1_ID =(select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = ?))  and user_chat1_id = ? and delete_flag = 1";

            st = cn.prepareStatement(sql);
            st.setString(1,cb.getChat_id());
            st.setString(2,cb.getChat_id());
            st.setString(3,cb.getFriend_id());
            rs = st.executeQuery();
            rs.next();
            if(rs.getInt(1)==1){
                judge = true;
            }
            System.out.println("judge"+judge);
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
            String sql="update chat_table set delete_flag = 0 ,delete_time = sysdate where chat_id = (select CHAT_ID " +
                    "from CHAT_TABLE where USER_CHAT_ID =(select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?) " +
                    "and USER_CHAT1_ID =(select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = ?))  and user_chat1_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,cb.getChat_id());
            st.setString(2,cb.getChat_id());
            System.out.println("cb.getChat_id()"+cb.getChat_id());
            st.setString(3,cb.getFriend_id());
            System.out.println("cb.getFriend_id()"+cb.getFriend_id());
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
}