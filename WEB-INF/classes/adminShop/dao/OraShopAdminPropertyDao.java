package adminShop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import adminShop.bean.PropertyBean;
import java.util.ArrayList;

public class OraShopAdminPropertyDao implements ShopAdminPropertyDao{
    public ArrayList getMyShopHistory(String shop_admin_user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList propertyList = new ArrayList();
        ShopAdminOracleConnecter oc = new ShopAdminOracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="select property_id,shop_admin_user_id,money,history," +
                    "TO_CHAR(history_date,'YYYY/MM/DD'),cancel_flag" +
                    " from PROPERTY_TABLE where SHOP_ADMIN_USER_ID = ? and cancel_flag = 0";
            st = cn.prepareStatement(sql);
            st.setString(1,shop_admin_user_id);
            rs = st.executeQuery();
            while(rs.next()){
                PropertyBean pb = new PropertyBean();
                pb.setProperty_id(rs.getString(1));
                pb.setShop_admin_user_id(rs.getString(2));
                pb.setMoney(rs.getInt(3));
                pb.setHistory(rs.getString(4));
                pb.setDate(rs.getString(5));
                pb.setCancel_flag(rs.getString(6));
                propertyList.add(pb);
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
        return propertyList;
    }
    public void addPropery(PropertyBean p){
        PreparedStatement st = null;
        Connection cn = null;
        ShopAdminOracleConnecter oc = new ShopAdminOracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="insert into property_table(property_id,user_id,money,history,shop_admin_user_id) values(PROPERTY_SEQUESNCE.nextval,?,?,'É`ÉÉÅ[ÉWÇµÇ‹ÇµÇΩ',?)";
            st = cn.prepareStatement(sql);
            st.setString(1,p.getUser_id());
            st.setInt(2,p.getMoney());
            st.setString(3,p.getShop_admin_user_id());
            int count = st.executeUpdate();
            System.out.println(count+"åèèàóùÇµÇ‹ÇµÇΩ");
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
    public void employMoney(PropertyBean p){

    }
    public ArrayList getDateSort(PropertyBean p){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList propertyList = new ArrayList();
        ShopAdminOracleConnecter oc = new ShopAdminOracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="select property_id,shop_admin_user_id,money,history,\n" +
                    " TO_CHAR(history_date,'YYYY/MM/DD'),cancel_flag from PROPERTY_TABLE" +
                    " where SHOP_ADMIN_USER_ID = ? and TO_CHAR(history_date,'YYYY/MM') = ? and cancel_flag = 0";
            st = cn.prepareStatement(sql);
            st.setString(1,p.getShop_admin_user_id());
            st.setString(2,p.getDate());
            rs = st.executeQuery();
            while(rs.next()){
                PropertyBean pb = new PropertyBean();
                pb.setProperty_id(rs.getString(1));
                pb.setShop_admin_user_id(rs.getString(2));
                pb.setMoney(rs.getInt(3));
                pb.setHistory(rs.getString(4));
                pb.setDate(rs.getString(5));
                pb.setCancel_flag(rs.getString(6));
                propertyList.add(pb);
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
        return propertyList;
    }
    public ArrayList getDateSortCancel(PropertyBean p){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList propertyList = new ArrayList();
        ShopAdminOracleConnecter oc = new ShopAdminOracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="select property_id,shop_admin_user_id,money,history,\n" +
                    " TO_CHAR(history_date,'YYYY/MM/DD'),cancel_flag from PROPERTY_TABLE" +
                    " where SHOP_ADMIN_USER_ID = ? and TO_CHAR(history_date,'YYYY/MM') = ? and cancel_flag = 1 and cancel_confirm_flag = 0";
            st = cn.prepareStatement(sql);
            st.setString(1,p.getShop_admin_user_id());
            st.setString(2,p.getDate());
            rs = st.executeQuery();
            while(rs.next()){
                PropertyBean pb = new PropertyBean();
                pb.setProperty_id(rs.getString(1));
                pb.setShop_admin_user_id(rs.getString(2));
                pb.setMoney(rs.getInt(3));
                pb.setHistory(rs.getString(4));
                pb.setDate(rs.getString(5));
                pb.setCancel_flag(rs.getString(6));
                propertyList.add(pb);
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
        return propertyList;
    }
    public void cancelItem(String property_id){
        PreparedStatement st = null;
        Connection cn = null;
        ShopAdminOracleConnecter aoc = new ShopAdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql="update property_table set cancel_flag = 1 where property_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,property_id);
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
    public void cancelAdd(String property_id){
        PreparedStatement st = null;
        Connection cn = null;
        ShopAdminOracleConnecter aoc = new ShopAdminOracleConnecter();
        try{
            cn = aoc.getConnection();
            String sql="insert into PROPERTY_TABLE values(PROPERTY_SEQUESNCE.nextval,(select user_id from PROPERTY_TABLE where PROPERTY_ID = ?),\n" +
                    "(select SHOP_ADMIN_USER_ID from PROPERTY_TABLE where PROPERTY_ID = ?),\n" +
                    "(select PROPERTY_TABLE.MONEY*-1 from PROPERTY_TABLE where PROPERTY_ID = ?),\n" +
                    "(select PROPERTY_TABLE.POINT*-1 from PROPERTY_TABLE where PROPERTY_ID = ?),'ï‘ã‡Ç≥ÇÍÇ‹ÇµÇΩ',Sysdate,1,1)";
            st = cn.prepareStatement(sql);
            st.setString(1,property_id);
            st.setString(2,property_id);
            st.setString(3,property_id);
            st.setString(4,property_id);
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
    public ArrayList getCancelList(String shop_admin_user_id){
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection cn = null;
        ArrayList propertyList = new ArrayList();
        ShopAdminOracleConnecter oc = new ShopAdminOracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="select property_id,shop_admin_user_id,money,history," +
                    "TO_CHAR(history_date,'YYYY/MM/DD'),cancel_flag" +
                    " from PROPERTY_TABLE where SHOP_ADMIN_USER_ID = ? and cancel_flag = 1 and cancel_confirm_flag = 0";
            st = cn.prepareStatement(sql);
            st.setString(1,shop_admin_user_id);
            rs = st.executeQuery();
            while(rs.next()){
                PropertyBean pb = new PropertyBean();
                pb.setProperty_id(rs.getString(1));
                pb.setShop_admin_user_id(rs.getString(2));
                pb.setMoney(rs.getInt(3));
                pb.setHistory(rs.getString(4));
                pb.setDate(rs.getString(5));
                pb.setCancel_flag(rs.getString(6));
                propertyList.add(pb);
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
        return propertyList;
    }
}