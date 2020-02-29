package admin.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;

import dao.function.Base64Image;
import admin.bean.ShopAdminUserBean;

public class OraShopAdminUserDao implements ShopAdminUserDao{
    public ArrayList getShopAdminApprovalList(){
        ArrayList shopList = new ArrayList();
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql = "select SHOP_ADMIN_USER_ID,name,mail,APPROVAL_IMAGE " +
                    "     from SHOP_ADMIN_TABLE where APPROVAL_FLAG = 0";
            st = cn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                ShopAdminUserBean saub = new ShopAdminUserBean();
                saub.setShop_admin_user_id(rs.getString(1));
                saub.setUser_name(rs.getString(2));
                saub.setMail(rs.getString(3));
                Blob blob = rs.getBlob(4);
                Base64Image bi = new Base64Image();
                saub.setPicture(bi.getBase64(blob));
                shopList.add(saub);
            }
            aoc.closeConnection();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
        return shopList;
    }
    public void deleteShopAdmin(String shop_admin_user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql = "delete SHOP_ADMIN_TABLE where SHOP_ADMIN_USER_ID = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,shop_admin_user_id);
            int count = st.executeUpdate();
            System.out.println(count+"åèèàóùÇµÇ‹ÇµÇΩ");
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
    public void addShopAdminApproval(String shop_admin_user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        AdminOracleConnecter aoc = new AdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql = "update SHOP_ADMIN_TABLE set APPROVAL_FLAG = 1 where SHOP_ADMIN_USER_ID = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,shop_admin_user_id);
            int count = st.executeUpdate();
            System.out.println(count+"åèèàóùÇµÇ‹ÇµÇΩ");
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