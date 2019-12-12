package dao;

import dao.function.AcquisitionImage;
import bean.UserBean;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;


public class OraProfileDao implements ProfileDao{

    public UserBean getProfile(String mail,String pass){
        PreparedStatement st = null;
        ResultSet rs = null;
        UserBean ub = new UserBean();
        Connection cn = null;
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
            AcquisitionImage acquisitionImage = new AcquisitionImage();
            String top_picture = acquisitionImage.getImagePath(user_id,search_id,blob);

            ub.setSearch_id(search_id);
            ub.setName(nickname);
            ub.setSingle_word(single_word);
            ub.setTop_picture(top_picture);
            ub.setUser_id(user_id);

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
    public void addProfile(UserBean ub){
        PreparedStatement st = null;
        FileInputStream fip = null;
        Connection cn = null;
        try {
            cn = OracleConnectionManager.getInstance().getConnection();
            File file = new File(ub.getTop_picture());
            System.out.println(file);
            fip = new FileInputStream(file);
            st = cn.prepareStatement("insert into user_information_table(user_id,search_id,nickname,single_word,top_picture) values(?,?,?,?,?)");
            st.setString(1,ub.getUser_id());
            st.setString(2,ub.getSearch_id());
            st.setString(3,ub.getName());
            st.setString(4,ub.getSingle_word());
            st.setBinaryStream(5, fip,(int)file.length());
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
    public UserBean getProfile(UserBean ub){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        try{

            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select user_id,search_id,nickname,single_word,top_picture" +
                    " from user_information_table where user_id = ?)";
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
            AcquisitionImage acquisitionImage = new AcquisitionImage();
            String top_picture = acquisitionImage.getImagePath(user_id,search_id,blob);

            ub.setSearch_id(search_id);
            ub.setName(nickname);
            ub.setSingle_word(single_word);
            ub.setTop_picture(top_picture);
            ub.setUser_id(user_id);

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
}