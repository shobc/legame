package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.io.InputStream;
import java.io.IOException;

//写真ファイルをBlobから作成するためのクラス
import dao.function.AcquisitionImage;
import bean.FriendBean;
import java.util.ArrayList;

//ログインユーザーの友達のデータを取り扱うためのDaoクラス
public class OraFriendDao implements FriendDao{
    //相手側が友達追加したかカウントする
    public String getNewFriendCount(String user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        String count = "";
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select count(*) from friend_table " +
                    "where user_id IN(select user_id from friend_table where friend_id = ?) " +
                    "and user_id NOT IN (select friend_id from friend_table where user_id = ?) " +
                    "and friend_id = ? and friend_flag= 0";

            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            st.setString(2,user_id);
            st.setString(3,user_id);
            rs = st.executeQuery();
            rs.next();

            //0以外を抱けを通知として表示する
            if(!rs.getString(1).equals("0")){
                count = rs.getString(1);
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
        return count;
    }
    //友達追加した相手を取得する
    public ArrayList getNewFriend(String user_id){
        ArrayList newFriendList = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql ="select u.search_id,u.nickname,u.single_word,u.top_picture,u.user_id from user_information_table u left join friend_table f " +
                    "on u.user_id = f.user_id and u.user_id IN(select user_id from friend_table where friend_id =?) and f.friend_id = ? " +
                    "where f.user_id NOT IN(select friend_id from friend_table where user_id=?) and f.friend_flag= 0 ";

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
//                AcquisitionImage acquisitionImage = new AcquisitionImage();
//                String top_picture = acquisitionImage.getImagePath(rs.getString(5),rs.getString(1),blob);
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);

                fb.setTop_picture(base64Image);
                newFriendList.add(fb);
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
        return newFriendList;
    }
    //友達をブロックしていたのを解除する
    public void releaseBlockFriend(FriendBean fb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="update friend_table set friend_flag = 0 where user_id = ? and friend_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,fb.getUser_id());
            st.setString(2,fb.getFriend_id());
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
    //友達をブロックする
    public void blockFriend(FriendBean fb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="update friend_table set friend_flag = 1 where user_id = ? and friend_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,fb.getUser_id());
            st.setString(2,fb.getFriend_id());
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
    //ブロックした友達を取得する
    public ArrayList getBlockFriend(String user_id){
        ArrayList blockFriendList = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select u.nickname,u.user_id,u.top_picture from user_information_table u left join friend_table f " +
                    "on u.user_id = f.friend_id and f.friend_id IN(select friend_id from friend_table where user_id = ?) " +
                    "where f.USER_ID = ? and f.friend_flag = 1";
            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            st.setString(2,user_id);
            rs = st.executeQuery();
            while (rs.next()){
                FriendBean fb = new FriendBean();
                fb.setName(rs.getString(1));
                fb.setUser_id(rs.getString(2));
                Blob blob = rs.getBlob(3);
//                AcquisitionImage acquisitionImage = new AcquisitionImage();
//                String top_picture = acquisitionImage.getImagePath(rs.getString(1),rs.getString(2),blob);
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                fb.setTop_picture(base64Image);
                blockFriendList.add(fb);
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
        return blockFriendList;
    }
    //友達を削除する
    public void deleteFriend(FriendBean fb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="delete from friend_table where user_id = ? and friend_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,fb.getUser_id());
            st.setString(2,fb.getFriend_id());
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
    //友達を追加する
    public void addFriend(FriendBean fb){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="insert into friend_table(user_id ,friend_id) values(?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,fb.getUser_id());
            st.setString(2,fb.getFriend_id());
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
    //友達をsearch_idで検索をかけてプロフィールを取得する
    public FriendBean getSearchFriend(FriendBean fb){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select search_id,nickname,single_word,top_picture,user_id from " +
                    "user_information_table where user_id " +
                    "not in(select friend_id from FRIEND_TABLE where USER_ID = ? and FRIEND_FLAG = 1 or FRIEND_FLAG = 0) " +
                    "and SEARCH_ID  = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,fb.getUser_id());
            st.setString(2,fb.getSearch_id());
            rs = st.executeQuery();
            rs.next();
            fb = new FriendBean();
            fb.setSearch_id(rs.getString(1));
            fb.setName(rs.getString(2));
            fb.setSingle_word(rs.getString(3));
            Blob blob = rs.getBlob(4);
            fb.setUser_id(rs.getString(5));
//            AcquisitionImage acquisitionImage = new AcquisitionImage();
//            String top_picture = acquisitionImage.getImagePath(rs.getString(1),rs.getString(5),blob);
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            fb.setTop_picture(base64Image);

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
        return fb;
    }
    //友達リストを取得する
    public ArrayList getFriend(String user_id){
        ArrayList friendList = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select u.nickname,u.single_word,u.top_picture,u.user_id from user_information_table u " +
                    "left join friend_table f on u.user_id = f.friend_id and f.friend_flag = 0" +
                    "where f.user_id = ? ";
            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            rs = st.executeQuery();
            while (rs.next()){
                FriendBean fb = new FriendBean();
                fb.setName(rs.getString(1));
                fb.setSingle_word(rs.getString(2));
                Blob blob = rs.getBlob(3);
                fb.setUser_id(rs.getString(4));
//                AcquisitionImage acquisitionImage = new AcquisitionImage();
//                String top_picture = acquisitionImage.getImagePath(rs.getString(4),rs.getString(1),blob);
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                fb.setTop_picture(base64Image);
                friendList.add(fb);
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
        return friendList;
    }
}