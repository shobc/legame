package dao;

import bean.UserBean;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import dao.function.Base64Image;
//ログインユーザーの情報を扱うためのDaoクラス
public class OraProfileDao implements ProfileDao{

    //ログイン時にユーザーのメールとパスでプロフィールを取得する
    public UserBean getProfile(String mail,String pass){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        UserBean ub = new UserBean();
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();

            String sql = "select user_id,search_id,nickname,single_word,top_picture" +
                    " from user_information_table where user_id = (select user_id from user_table where mail= ? and password = ?)";
            st = cn.prepareStatement(sql);
            st.setString(1,mail);
            st.setString(2,pass);
            rs = st.executeQuery();
            if(rs.next()){
                ub.setUser_id(rs.getString(1));
                ub.setSearch_id(rs.getString(2));
                ub.setName(rs.getString(3));
                ub.setSingle_word(rs.getString(4));
                Blob blob = rs.getBlob(5);
                Base64Image bi = new Base64Image();
                ub.setTop_picture(bi.getBase64(blob));
            }else{
                ub = null;
            }
            rs.close();
            st.close();
            oc.closeConnection();
        }catch(IOException e){
            System.out.println(e.getMessage());
            oc.rollback();
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
        return ub;
    }
    //新規登録してくるユーザーのプロフィールを追加する
    public void addProfile(UserBean ub){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try {
            cn = oc.getConnection();
            FileInputStream fis = new FileInputStream(ub.getTop_picture());
            st = cn.prepareStatement("insert into user_information_table(user_id,search_id,nickname,single_word,top_picture) values(?,?,?,?,?)");
            st.setString(1,ub.getUser_id());
            st.setString(2,ub.getSearch_id());
            st.setString(3,ub.getName());
            st.setString(4,ub.getSingle_word());
            st.setBinaryStream(5,fis);
            int count = st.executeUpdate();
            System.out.println(count+"件処理しました");
            oc.commit();
            oc.closeConnection();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            oc.rollback();
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
    //ユーザーのプロフィールを取得する
    public UserBean getProfile(UserBean ub){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select user_id,search_id,nickname,single_word,top_picture" +
                    " from user_information_table where user_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,ub.getUser_id());
            rs = st.executeQuery();
            rs.next();
            ub = new UserBean();
            ub.setUser_id(rs.getString(1));
            ub.setSearch_id(rs.getString(2));
            ub.setName(rs.getString(3));
            ub.setSingle_word(rs.getString(4));
            Blob blob = rs.getBlob(5);
            Base64Image bi = new Base64Image();
            ub.setTop_picture(bi.getBase64(blob));
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
        return ub;
    }
    //プロフィールを変更
    //トップ画像を変更
    public void updateTopPictureProfile(UserBean ub){
        PreparedStatement st = null;
        FileInputStream fip = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try {
            cn = oc.getConnection();


            File file = new File(ub.getTop_picture());
            System.out.println(file);
            fip = new FileInputStream(file);
            st = cn.prepareStatement("update USER_INFORMATION_TABLE set top_picture = ? where user_id = ?");
            st.setBinaryStream(1,fip,(int)file.length());
            st.setString(2,ub.getUser_id());
            st.executeUpdate();
            st.close();
            oc.commit();
            oc.closeConnection();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            oc.rollback();
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
    //一言を変更
    public void updateSingleWordProfile(UserBean ub){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try {
            cn = oc.getConnection();
            st = cn.prepareStatement("update USER_INFORMATION_TABLE set single_word = ? where user_id = ?");
            st.setString(1,ub.getSingle_word());
            st.setString(2,ub.getUser_id());
            st.executeUpdate();
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
    //名前を変更
    public void updateNameProfile(UserBean ub){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try {
            cn = oc.getConnection();
            st = cn.prepareStatement("update USER_INFORMATION_TABLE set nickname = ? where user_id = ?");
            st.setString(1,ub.getName());
            st.setString(2,ub.getUser_id());
            st.executeUpdate();
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
    //ユーザー検索IDを変更
    public void updateSearchIdProfile(UserBean ub){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try {
            cn = oc.getConnection();
            st = cn.prepareStatement("update USER_INFORMATION_TABLE set search_id = ? where user_id = ?");
            st.setString(1,ub.getSearch_id());
            st.setString(2,ub.getUser_id());
            st.executeUpdate();
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
    public void UpdateUserTopPicture(UserBean ub){
        PreparedStatement st = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try {
            FileInputStream fis = new FileInputStream(ub.getTop_picture());
            cn = oc.getConnection();
            st = cn.prepareStatement("update USER_INFORMATION_TABLE set Top_Picture = ? where user_id = ?");
            st.setBinaryStream(1,fis);
            st.setString(2,ub.getUser_id());
            int count = st.executeUpdate();
            System.out.println(count+"件処理しました");
            st.close();
            oc.commit();
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
    }
    public UserBean getProfile(String chat_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        UserBean ub = new UserBean();
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select user_id,nickname,single_word,top_picture from user_information_table " +
                    "where user_id = (select CHAT_TABLE.USER_CHAT1_ID from CHAT_TABLE where CHAT_ID = ?)";
            st = cn.prepareStatement(sql);
            st.setString(1,chat_id);
            rs = st.executeQuery();
            rs.next();
            ub.setUser_id(rs.getString(1));
            ub.setName(rs.getString(2));
            ub.setSingle_word(rs.getString(3));
            Blob blob = rs.getBlob(4);
            Base64Image bi = new Base64Image();
            ub.setTop_picture(bi.getBase64(blob));

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
        return ub;
    }
    public void UpdateFriendQRCode(String user_id,String QRCode){
        PreparedStatement st = null;
        FileInputStream fip = null;
        Connection cn = null;
        OracleConnecter oc = new OracleConnecter();
        try {
            cn = oc.getConnection();
            st = cn.prepareStatement("update USER_INFORMATION_TABLE set friend_qrcode = ? where user_id = ?");
            st.setString(1,QRCode);
            st.setString(2,user_id);
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
    public boolean sufferSearchId(String search_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        boolean judge = false;
        OracleConnecter oc = new OracleConnecter();
        try{
            cn = oc.getConnection();
            String sql = "select count(*) from USER_INFORMATION_TABLE where SEARCH_ID = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,search_id);
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
}