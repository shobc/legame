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
//���O�C�����[�U�[�̏����������߂�Dao�N���X
public class OraProfileDao implements ProfileDao{

    //���O�C�����Ƀ��[�U�[�̃��[���ƃp�X�Ńv���t�B�[�����擾����
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
            ub.setUser_id(rs.getString(1));
            ub.setSearch_id(rs.getString(2));
            ub.setName(rs.getString(3));
            ub.setSingle_word(rs.getString(4));
            Blob blob = rs.getBlob(5);
            Base64Image bi = new Base64Image();
            ub.setTop_picture(bi.getBase64(blob));
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
    //�V�K�o�^���Ă��郆�[�U�[�̃v���t�B�[����ǉ�����
    public void addProfile(UserBean ub){
        PreparedStatement st = null;
        Connection cn = null;
        try {
            cn = OracleConnectionManager.getInstance().getConnection();
            FileInputStream fis = new FileInputStream(ub.getTop_picture());
            st = cn.prepareStatement("insert into user_information_table(user_id,search_id,nickname,single_word,top_picture) values(?,?,?,?,?)");
            st.setString(1,ub.getUser_id());
            st.setString(2,ub.getSearch_id());
            st.setString(3,ub.getName());
            st.setString(4,ub.getSingle_word());
            st.setBinaryStream(5,fis);
            int count = st.executeUpdate();
            System.out.println(count+"���������܂���");
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
    //���[�U�[�̃v���t�B�[�����擾����
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
            ub.setUser_id(rs.getString(1));
            ub.setSearch_id(rs.getString(2));
            ub.setName(rs.getString(3));
            ub.setSingle_word(rs.getString(4));
            Blob blob = rs.getBlob(5);
            Base64Image bi = new Base64Image();
            ub.setTop_picture(bi.getBase64(blob));

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
    //�v���t�B�[����ύX
    //�g�b�v�摜��ύX
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
    //�ꌾ��ύX
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
    //���O��ύX
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
    //���[�U�[����ID��ύX
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
    public void UpdateUserTopPicture(UserBean ub){
        PreparedStatement st = null;
        Connection cn = null;
        try {
            FileInputStream fis = new FileInputStream(ub.getTop_picture());
            cn = OracleConnectionManager.getInstance().getConnection();
            st = cn.prepareStatement("update USER_INFORMATION_TABLE set Top_Picture = ? where user_id = ?");
            st.setBinaryStream(1,fis);
            st.setString(2,ub.getUser_id());
            st.executeUpdate();
            st.close();
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
    public UserBean getProfile(String chat_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        UserBean ub = new UserBean();
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
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
    public void UpdateFriendQRCode(String user_id,String QRCode){
        PreparedStatement st = null;
        FileInputStream fip = null;
        Connection cn = null;
        try {
            cn = OracleConnectionManager.getInstance().getConnection();
            st = cn.prepareStatement("update USER_INFORMATION_TABLE set friend_qrcode = ? where user_id = ?");
            st.setString(1,QRCode);
            st.setString(2,user_id);
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
}