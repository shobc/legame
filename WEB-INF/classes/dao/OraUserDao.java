package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.LoginUserBean;

public class OraUserDao implements UserDao{
    public void RegisterUser(LoginUserBean lub){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="insert into user_table(user_id,mail ,password ) values(user_sequesnce.nextval,?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,lub.getMail());
            st.setString(2,lub.getPass());
            System.out.println(lub.getMail());
            System.out.println(lub.getPass());
            int count = st.executeUpdate();
            System.out.println(count+"åèèàóùÇµÇ‹ÇµÇΩ");
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

    public String getUserId(LoginUserBean lub){
        PreparedStatement st = null;
        String user_id = null;
        ResultSet rs = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="select user_id from user_table where mail = ? and password = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,lub.getMail());
            st.setString(2,lub.getPass());
            rs = st.executeQuery();
            rs.next();
            user_id = rs.getString(1);
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
        return user_id;
    }

    public void UpdateUserPassWord(LoginUserBean lub){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "update user_table set PASSWORD = ? where mail = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,lub.getPass());
            st.setString(2,lub.getMail());
            int count = st.executeUpdate();
            System.out.println(count+"åèèàóùÇµÇ‹ÇµÇΩ");
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