package admin.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import admin.bean.AdminUserBean;

public class OraAdminUserDao implements AdminUserDao{
    public boolean getAdminUserJudge(AdminUserBean aub){
        PreparedStatement st = null;
        boolean judge = false;
        ResultSet rs = null;
        Connection cn = null;
        AdminOracleConnecter oc = new AdminOracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="select count(*) from legame_admin_table where mail = ? and name = ? and password = ? ";
            st = cn.prepareStatement(sql);
            st.setString(1,aub.getMail());
            st.setString(2,aub.getUser_name());
            st.setString(3,aub.getPassword());
            rs = st.executeQuery();
            rs.next();
            if(rs.getInt(1)==1){
                judge = true;
            }
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
    public AdminUserBean getAdminUser(AdminUserBean aub){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        AdminOracleConnecter oc = new AdminOracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="select * from legame_admin_table where mail = ? and name = ? and password = ? ";
            st = cn.prepareStatement(sql);
            st.setString(1,aub.getMail());
            st.setString(2,aub.getUser_name());
            st.setString(3,aub.getPassword());
            rs = st.executeQuery();
            rs.next();
            aub.setAdmin_user_id(rs.getString(1));
            aub.setUser_id(rs.getString(2));
            aub.setUser_name(rs.getString(3));
            aub.setMail(rs.getString(4));
            aub.setPassword(rs.getString(5));
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
        return aub;
    }
}