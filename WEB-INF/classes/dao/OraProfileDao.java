package dao;

import bean.UserBean;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

//写真ファイルをBlobから作成するためのクラス
import dao.function.AcquisitionImage;

//ログインユーザーの情報を扱うためのDaoクラス
public class OraProfileDao implements ProfileDao{

    //ログイン時にユーザーのメールとパスでプロフィールを取得する
    public UserBean getProfile(String mail,String pass){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        UserBean ub = new UserBean();
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select user_id,search_id,nickname,single_word,top_picture" +
                    " from user_information_table where user_id = (select user_id from user_table where mail= ? and password = ?)";
            st = cn.prepareStatement(sql);
            st.setString(1,mail);
            st.setString(2,pass);
            rs = st.executeQuery();
            rs.next();
            String user_id = rs.getString(1);
            String search_id = rs.getString(2);
            String nickname = rs.getString(3);
            String single_word = rs.getString(4);
            Blob blob = rs.getBlob(5);
//            AcquisitionImage acquisitionImage = new AcquisitionImage();
//            String top_picture = acquisitionImage.getImagePath(user_id,search_id,blob);
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            ub.setSearch_id(search_id);
            ub.setName(nickname);
            ub.setSingle_word(single_word);
            ub.setTop_picture(base64Image);
            ub.setUser_id(user_id);

        }catch(IOException e){
            System.out.println(e.getMessage());
            OracleConnectionManager.getInstance().rollback();
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
        return ub;
    }
    //新規登録してくるユーザーのプロフィールを追加する
    public void addProfile(UserBean ub){
        PreparedStatement st = null;
        FileInputStream fip = null;
        Connection cn = null;
        try {
            cn = OracleConnectionManager.getInstance().getConnection();
            FileInputStream fis = new FileInputStream(ub.getTop_picture());
//            File file = new File(ub.getTop_picture());
//            System.out.println(file);
//            fip = new FileInputStream(file);
            st = cn.prepareStatement("insert into user_information_table(user_id,search_id,nickname,single_word,top_picture) values(?,?,?,?,?)");
            st.setString(1,ub.getUser_id());
            st.setString(2,ub.getSearch_id());
            st.setString(3,ub.getName());
            st.setString(4,ub.getSingle_word());
            st.setBinaryStream(5,fis);
//            st.setBinaryStream(5, fip,(int)file.length());
            int count = st.executeUpdate();
            System.out.println(count+"件処理しました");
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            OracleConnectionManager.getInstance().rollback();
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
    //ユーザーのプロフィールを取得する
    public UserBean getProfile(UserBean ub){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select user_id,search_id,nickname,single_word,top_picture" +
                    " from user_information_table where user_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,ub.getUser_id());
            rs = st.executeQuery();
            rs.next();
            ub = new UserBean();
            String user_id = rs.getString(1);
            String search_id = rs.getString(2);
            String nickname = rs.getString(3);
            String single_word = rs.getString(4);
            Blob blob = rs.getBlob(5);
//            AcquisitionImage acquisitionImage = new AcquisitionImage();
//            String top_picture = acquisitionImage.getImagePath(user_id,search_id,blob);
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            ub.setSearch_id(search_id);
            ub.setName(nickname);
            ub.setSingle_word(single_word);
            ub.setTop_picture(base64Image);
            ub.setUser_id(user_id);

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
        return ub;
    }
    //プロフィールを変更
    //トップ画像を変更
    public void updateTopPictureProfile(UserBean ub){
        PreparedStatement st = null;
        FileInputStream fip = null;
        Connection cn = null;
        try {
            cn = OracleConnectionManager.getInstance().getConnection();
            File file = new File(ub.getTop_picture());
            System.out.println(file);
            fip = new FileInputStream(file);
            st = cn.prepareStatement("update USER_INFORMATION_TABLE set top_picture = ? where user_id = ?");
            st.setBinaryStream(1,fip,(int)file.length());
            st.setString(2,ub.getUser_id());
            st.executeUpdate();
            st.close();

        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            OracleConnectionManager.getInstance().rollback();
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
    //一言を変更
    public void updateSingleWordProfile(UserBean ub){
        PreparedStatement st = null;
        Connection cn = null;
        try {
            cn = OracleConnectionManager.getInstance().getConnection();
            st = cn.prepareStatement("update USER_INFORMATION_TABLE set single_word = ? where user_id = ?");
            st.setString(1,ub.getSingle_word());
            st.setString(2,ub.getUser_id());
            st.executeUpdate();
            st.close();
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
    //名前を変更
    public void updateNameProfile(UserBean ub){
        PreparedStatement st = null;
        Connection cn = null;
        try {
            cn = OracleConnectionManager.getInstance().getConnection();
            st = cn.prepareStatement("update USER_INFORMATION_TABLE set nickname = ? where user_id = ?");
            st.setString(1,ub.getName());
            st.setString(2,ub.getUser_id());
            st.executeUpdate();
            st.close();
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
    //ユーザー検索IDを変更
    public void updateSearchIdProfile(UserBean ub){
        PreparedStatement st = null;
        Connection cn = null;
        try {
            cn = OracleConnectionManager.getInstance().getConnection();
            st = cn.prepareStatement("update USER_INFORMATION_TABLE set search_id = ? where user_id = ?");
            st.setString(1,ub.getSearch_id());
            st.setString(2,ub.getUser_id());
            st.executeUpdate();
            st.close();
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