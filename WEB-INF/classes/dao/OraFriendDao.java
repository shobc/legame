package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.io.FileInputStream;
import java.io.IOException;

import dao.function.Base64Image;
import bean.FriendBean;
import java.util.ArrayList;

//ログインユーザーの友達のデータを取り扱うためのDaoクラス
public class OraFriendDao implements FriendDao{
    //相手側が友達追加したかカウントする
    public String getNewFriendCount(String user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        String count = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select count(*) from friend_table " +
                    "where user_id IN(select user_id from friend_table where friend_id = ?) " +
                    "and user_id NOT IN (select friend_id from friend_table where user_id = ?) " +
                    "and friend_id = ? and friend_flag= 0 and delete_flag != 1";

            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            st.setString(2,user_id);
            st.setString(3,user_id);
            rs = st.executeQuery();
            rs.next();

            //0以外を抱けを通知として表示する
            if(rs.getInt(1)!=0){
                count = "new";
            }
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
        return count;
    }
    //友達追加した相手を取得する
    public ArrayList getNewFriend(String user_id){
        ArrayList newFriendList = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql ="select u.search_id,u.nickname,u.single_word,u.top_picture,u.user_id from user_information_table u left join friend_table f " +
                    "on u.user_id = f.user_id and u.user_id IN(select user_id from friend_table where friend_id =?) and f.friend_id = ? " +
                    "where f.user_id NOT IN(select friend_id from friend_table where user_id=?) and f.friend_flag= 0 and delete_flag = 0";
            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            st.setString(2,user_id);
            st.setString(3,user_id);
            rs = st.executeQuery();
            while (rs.next()){
                FriendBean fb = new FriendBean();
                fb.setSearch_id(rs.getString(1));
                fb.setName(rs.getString(2));
                fb.setSingle_word(rs.getString(3));
                Blob blob = rs.getBlob(4);
                fb.setUser_id(rs.getString(5));
                Base64Image bi = new Base64Image();
                fb.setTop_picture(bi.getBase64(blob));
                newFriendList.add(fb);
            }
            rs.close();
            st.close();
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
        return newFriendList;
    }
    //友達をブロックしていたのを解除する
    public void releaseBlockFriend(FriendBean fb){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();

            String sql="update friend_table set friend_flag = 0 where user_id = ? and friend_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,fb.getUser_id());
            st.setString(2,fb.getFriend_id());
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
    //友達をブロックする
    public void blockFriend(FriendBean fb){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="update friend_table set friend_flag = 1 where user_id = ? and friend_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,fb.getUser_id());
            st.setString(2,fb.getFriend_id());
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
    //ブロックした友達を取得する
    public ArrayList getBlockFriend(String user_id){
        ArrayList blockFriendList = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select u.nickname,u.user_id,u.top_picture from user_information_table u left join friend_table f " +
                    "on u.user_id = f.friend_id and f.friend_id IN(select friend_id from friend_table where user_id = ?) " +
                    "where f.USER_ID = ? and f.friend_flag = 1 and delete_flag = 0";
            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            st.setString(2,user_id);
            rs = st.executeQuery();
            while (rs.next()){
                FriendBean fb = new FriendBean();
                fb.setName(rs.getString(1));
                fb.setUser_id(rs.getString(2));
                Blob blob = rs.getBlob(3);
                Base64Image bi = new Base64Image();
                fb.setTop_picture(bi.getBase64(blob));

                blockFriendList.add(fb);
            }
            rs.close();
            st.close();
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
        return blockFriendList;
    }
    //友達を削除する
    public void deleteFriend(FriendBean fb){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="update friend_table set delete_flag = 1 where user_id = ? and friend_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,fb.getUser_id());
            st.setString(2,fb.getFriend_id());
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
    //友達を追加する
    public void addFriend(FriendBean fb){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="insert into friend_table(user_id ,friend_id) values(?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,fb.getUser_id());
            st.setString(2,fb.getFriend_id());
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
    //友達をsearch_idで検索をかけてプロフィールを取得する
    public FriendBean getSearchFriend(FriendBean fb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select search_id,nickname,single_word,top_picture,user_id from\n" +
                    "    user_information_table where user_id\n" +
                    "    not in(select FRIEND_ID from FRIEND_TABLE where USER_ID = ? and (FRIEND_FLAG = 1 or FRIEND_FLAG = 0))\n" +
                    "    and SEARCH_ID  = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,fb.getUser_id());
            st.setString(2,fb.getSearch_id());
            rs = st.executeQuery();
            if(rs.next()){
                fb = new FriendBean();
                fb.setSearch_id(rs.getString(1));
                fb.setName(rs.getString(2));
                fb.setSingle_word(rs.getString(3));
                Blob blob = rs.getBlob(4);
                fb.setUser_id(rs.getString(5));

                Base64Image bi = new Base64Image();
                fb.setTop_picture(bi.getBase64(blob));
            }else{
                fb = null;
            }
            oc.closeConnection();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }
        catch(SQLException e){
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
        return fb;
    }
    //友達リストを取得する
    public ArrayList getFriend(String user_id){
        ArrayList friendList = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select u.nickname,u.single_word,u.top_picture,u.user_id from user_information_table u " +
                    "left join friend_table f on u.user_id = f.friend_id and f.friend_flag = 0" +
                    "where f.user_id = ? and delete_flag = 0";
            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            rs = st.executeQuery();
            while (rs.next()){
                FriendBean fb = new FriendBean();
                fb.setName(rs.getString(1));
                fb.setSingle_word(rs.getString(2));
                Blob blob = rs.getBlob(3);
                fb.setUser_id(rs.getString(4));
                Base64Image bi = new Base64Image();
                fb.setTop_picture(bi.getBase64(blob));
                friendList.add(fb);
            }
            rs.close();
            st.close();
            oc.closeConnection();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }
        catch(SQLException e){
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
        return friendList;
    }
    public FriendBean getFriendQRUser_id(String QRCode){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        FriendBean fb = new FriendBean();
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select nickname,single_word,top_picture,user_id from\n" +
                    "    user_information_table user_information_table where friend_qrcode = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,QRCode);
            rs = st.executeQuery();
            rs.next();
            fb = new FriendBean();
            fb.setName(rs.getString(1));
            fb.setSingle_word(rs.getString(2));
            Blob blob = rs.getBlob(3);
            fb.setUser_id(rs.getString(4));
            Base64Image bi = new Base64Image();
            fb.setTop_picture(bi.getBase64(blob));
            rs.close();
            st.close();
            oc.closeConnection();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }
        catch(SQLException e){
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
        return fb;
    }
    public FriendBean getFriendInfo(String friend_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        FriendBean fb = new FriendBean();
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select nickname,single_word,top_picture,user_id from\n" +
                    "    user_information_table user_information_table where user_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,friend_id);
            rs = st.executeQuery();
            rs.next();
            fb = new FriendBean();
            fb.setName(rs.getString(1));
            fb.setSingle_word(rs.getString(2));
            Blob blob = rs.getBlob(3);
            fb.setUser_id(rs.getString(4));
            Base64Image bi = new Base64Image();
            fb.setTop_picture(bi.getBase64(blob));
            rs.close();
            st.close();
            oc.closeConnection();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            oc.rollback();
        }
        catch(SQLException e){
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
        return fb;
    }
    public boolean getFriendAddJudge(String chat_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        boolean judge = false;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select count(*) from FRIEND_TABLE " +
                    "where USER_ID =(select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = ?) " +
                    "and FRIEND_ID=(select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?) ";
            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            st.setString(2,chat_id);
            rs = st.executeQuery();
            rs.next();
            if(rs.getInt(1)==0){
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
    public boolean getFriendDeleteOrBlockJudge(String chat_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        boolean judge = false;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select count(*) from FRIEND_TABLE " +
                    "where USER_ID =(select USER_CHAT_ID from CHAT_TABLE where CHAT_ID = ?) " +
                    "and FRIEND_ID=(select USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?)\n" +
//                    " and (FRIEND_FLAG = 1 or DELETE_FLAG = 1) ";
                    " and (DELETE_FLAG = 1) ";
            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            st.setString(2,chat_id);
            rs = st.executeQuery();
            rs.next();
            if(rs.getInt(1)==1){
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
    public void addFriend(String chat_id){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="insert into friend_table(user_id ,friend_id)\n" +
                    "     values((select USER_CHAT_ID from CHAT_TABLE where chat_id = ?)" +
                    ",(select USER_CHAT1_ID from CHAT_TABLE where chat_id = ?))";
            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            st.setString(2,chat_id);
            int count = st.executeUpdate();
            st.close();
            System.out.println(count+"件処理しました");
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
    public void releaseFriend(String chat_id){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="update FRIEND_TABLE set FRIEND_FLAG = 0 ,DELETE_FLAG = 0 \n" +
                    "where USER_ID = (select USER_CHAT_ID from CHAT_TABLE where chat_id = ?) \n" +
                    "and FRIEND_ID = (select USER_CHAT1_ID from CHAT_TABLE where chat_id = ?)";
            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            st.setString(2,chat_id);
            int count = st.executeUpdate();
            System.out.println(count+"件処理しました");
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
    public void noFriendBlock(FriendBean fb){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="insert into friend_table(user_id ,friend_id,friend_flag)\n" +
                    "     values(?,?,1)";
            st = cn.prepareStatement(sql);
            st.setString(1,fb.getUser_id());
            st.setString(2,fb.getFriend_id());
            int count = st.executeUpdate();
            st.close();
            System.out.println(count+"件処理しました");

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
}