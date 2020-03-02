package adminShop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.io.IOException;

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
            String sql="select shop_admin_user_id,name,mail from shop_admin_table where mail = ? and name = ? and password = ? ";
            st = cn.prepareStatement(sql);
            st.setString(1,saub.getMail());
            st.setString(2,saub.getUser_name());
            st.setString(3,saub.getPassword());
            rs = st.executeQuery();
            rs.next();
            saub.setShop_admin_user_id(rs.getString(1));
            saub.setUser_name(rs.getString(2));
            saub.setMail(rs.getString(3));
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
    public void provisionalRegisterShopAdminUser(ShopAdminUserBean saub) {
        PreparedStatement st = null;
        Connection cn = null;
        ShopAdminOracleConnecter aoc = new ShopAdminOracleConnecter();
        try{
            FileInputStream fis = new FileInputStream(saub.getPicture());
            cn = aoc.getConnection();
            String sql="insert into SHOP_ADMIN_TABLE(shop_admin_user_id,name,mail,password,approval_image) values(shop_admin_sequesnce.nextval,?,?,?,?)";
            st = cn.prepareStatement(sql);
            st.setString(1,saub.getUser_name());
            st.setString(2,saub.getMail());
            st.setString(3,saub.getPassword());
            st.setBinaryStream(4,fis);
            int count = st.executeUpdate();
            System.out.println(count+"åèèàóùÇµÇ‹ÇµÇΩ");
            st.close();
            aoc.commit();
            aoc.closeConnection();
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            aoc.rollback();
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
    public String getUser_id(String mail){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        String shop_admin_user_id = null;
        ShopAdminOracleConnecter oc = new ShopAdminOracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="select shop_admin_user_id from shop_admin_table where mail = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,mail);
            rs = st.executeQuery();
            rs.next();
            shop_admin_user_id = rs.getString(1);
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
        return shop_admin_user_id;
    }
    public void changePassWord(ShopAdminUserBean saub){
        PreparedStatement st = null;
        Connection cn = null;
        ShopAdminOracleConnecter aoc = new ShopAdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql="update SHOP_ADMIN_TABLE set password = ? where shop_admin_user_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,saub.getPassword());
            st.setString(2,saub.getShop_admin_user_id());
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
    public void removeAccount(String shop_admin_user_id){
        PreparedStatement st = null;
        Connection cn = null;
        ShopAdminOracleConnecter aoc = new ShopAdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql="update SHOP_ADMIN_TABLE set delete_flag = 1 where shop_admin_user_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,shop_admin_user_id);
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