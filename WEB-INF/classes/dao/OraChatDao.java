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
            String sql="insert into chat_table(chat_id,user_chat_id,user_chat1_id) values(caht_sequesnce.nextval,?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,cb.getUser_id());
            st.setString(2,cb.getFriend_id());
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
    //チャットが作られているか判定する
    public boolean getJudge(ChatBean cb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        boolean judge = false;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select count(chat_id) from chat_table " +
                    "where (user_chat_id= ? and user_chat1_id = ?)" +
                    " or (user_chat_id= ? and user_chat1_id = ?)";

            st = cn.prepareStatement(sql);
            st.setString(1,cb.getFriend_id());
            st.setString(2,cb.getUser_id());
            st.setString(3,cb.getUser_id());
            st.setString(4,cb.getFriend_id());
            rs = st.executeQuery();
            rs.next();
            String j = rs.getString(1);
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
                    "where (user_chat_id= ?  and user_chat1_id = ?) " +
                    "or (user_chat_id= ?  and user_chat1_id = ?)";

            st = cn.prepareStatement(sql);
            st.setString(1,cb.getUser_id());
            st.setString(2,cb.getFriend_id());
            st.setString(3,cb.getFriend_id());
            st.setString(4,cb.getUser_id());
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
            String sql = "select u.search_id,u.user_id,u.nickname,u.top_picture,c.chat_id " +
                    "from user_information_table u left join chat_table c " +
                    "on u.user_id = c.user_chat1_id and c.user_chat_id = ? " +
                    " or u.user_id = c.user_chat_id and c.user_chat1_id = ? " +
                    "where c.user_chat_id = ? or c.user_chat1_id = ?";

            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            st.setString(2,user_id);
            st.setString(3,user_id);
            st.setString(4,user_id);
            rs = st.executeQuery();
            while(rs.next()){
                ChatBean cb = new ChatBean();
                String search_id = rs.getString(1);
                cb.setUser_id(rs.getString(2));
                cb.setName(rs.getString(3));
                Blob blob = rs.getBlob(4);
                cb.setChat_id(rs.getString(5));
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
}