package adminShop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import adminShop.bean.ShopAdminUserBean;

public class OraShopAdminUserDao implements ShopAdminUserDao{
    public boolean getShopAdminUserJudge(ShopAdminUserBean saub){
        PreparedStatement st = null;
        boolean judge = false;
        ResultSet rs = null;
        Connection cn = null;
        ShopAdminOracleConnecter oc = new ShopAdminOracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="select count(*) from shop_admin_table where mail = ? and name = ? and password = ? ";
            st = cn.prepareStatement(sql);
            st.setString(1,saub.getMail());
            st.setString(2,saub.getUser_name());
            st.setString(3,saub.getPassword());
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
    public boolean getApprovalShopAdminUserJudge(ShopAdminUserBean saub){
        PreparedStatement st = null;
        boolean judge = false;
        ResultSet rs = null;
        Connection cn = null;
        ShopAdminOracleConnecter oc = new ShopAdminOracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="select count(*) from shop_admin_table where mail = ? and name = ? and password = ? and APPROVAL_FLAG = 0 ";
            st = cn.prepareStatement(sql);
            st.setString(1,saub.getMail());
            st.setString(2,saub.getUser_name());
            st.setString(3,saub.getPassword());
            rs = st.executeQuery();
            rs.next();
            if(rs.getInt(1)==0){
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
    public ShopAdminUserBean getShopAdminUser(ShopAdminUserBean saub){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ShopAdminOracleConnecter oc = new ShopAdminOracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="select * from shop_admin_table where mail = ? and name = ? and password = ? ";
            st = cn.prepareStatement(sql);
            st.setString(1,saub.getMail());
            st.setString(2,saub.getUser_name());
            st.setString(3,saub.getPassword());
            rs = st.executeQuery();
            rs.next();
            saub.setShop_admin_user_id(rs.getString(1));
            saub.setUser_id(rs.getString(2));
            saub.setUser_name(rs.getString(3));
            saub.setMail(rs.getString(4));
            saub.setPassword(rs.getString(5));
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
        return saub;
    }
    public void ProvisionalRegisterShopAdminUser(ShopAdminUserBean saub) {
        PreparedStatement st = null;
        Connection cn = null;
        ShopAdminOracleConnecter aoc = new ShopAdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql="insert into SHOP_ADMIN_TABLE values(shop_admin_sequesnce.nextval,SHOP_USER_SEQUESNCE.currval,?,?,?,0)";
            st = cn.prepareStatement(sql);
            st.setString(1,saub.getMail());
            st.setString(2,saub.getUser_name());
            st.setString(3,saub.getPassword());
            int count = st.executeUpdate();
            System.out.println(count+"åèèàóùÇµÇ‹ÇµÇΩ");
            st.close();
            aoc.commit();
            aoc.closeConnection();
        }catch(SQLException e){
            System.out.println(e.getMessage());
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