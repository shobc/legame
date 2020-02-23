package admin.dao;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

import dao.function.Base64Image;
import admin.bean.UserBean;
import java.util.ArrayList;

public class OraUserDao implements UserDao{
    public ArrayList getUserList(){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList userList = new ArrayList();
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql = "select u.USER_ID,uit.SEARCH_ID,uit.TOP_PICTURE," +
                    "(select count(user_id) from USER_TABLE where USER_ID= u.USER_ID and u.USER_TIME <= SYSDATE)\n" +
                    "from USER_TABLE u left join USER_INFORMATION_TABLE uit on u.USER_ID = uit.USER_ID " +
                    "where u.user_id != 0 and uit.SEARCH_ID IS NOT NUll";
            st = cn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                UserBean ub = new UserBean();
                ub.setUser_id(rs.getString(1));
                ub.setSearch_id(rs.getString(2));
                Blob blob = rs.getBlob(3);
                Base64Image bi = new Base64Image();
                ub.setTop_picture(bi.getBase64(blob));
                ub.setStop_flag(rs.getString(4));
                userList.add(ub);
            }
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
        return userList;
    }
    public ArrayList searchUser(String search_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList userList = new ArrayList();
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql = "select u.USER_ID,uit.SEARCH_ID,uit.TOP_PICTURE," +
                    "(select count(user_id) from USER_TABLE where USER_ID= u.USER_ID and u.USER_TIME <= SYSDATE)\n" +
                    "from USER_TABLE u left join USER_INFORMATION_TABLE uit on u.USER_ID = uit.USER_ID " +
                    "where u.user_id != 0 and search_id LIKE ?";
            st = cn.prepareStatement(sql);
            st.setString(1,"%"+search_id+"%");
            rs = st.executeQuery();
            while(rs.next()){
                UserBean ub = new UserBean();
                ub.setUser_id(rs.getString(1));
                ub.setSearch_id(rs.getString(2));
                Blob blob = rs.getBlob(3);
                Base64Image bi = new Base64Image();
                ub.setTop_picture(bi.getBase64(blob));
                ub.setStop_flag(rs.getString(4));
                userList.add(ub);
            }
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
        return userList;
    }

    public void stopUserAccount(String user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql = "update USER_TABLE set USER_TIME = sysdate + 1 where USER_ID = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            int count = st.executeUpdate();
            System.out.println("stopUserAccount"+count+"Œˆ—‚µ‚Ü‚µ‚½");
            aoc.commit();
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
    public void releaseUserAccout(String user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql = "update USER_TABLE set USER_TIME = sysdate where USER_ID = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,user_id);
            int count = st.executeUpdate();
            System.out.println("releaseUserAccout"+count+"Œˆ—‚µ‚Ü‚µ‚½");
            aoc.commit();
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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