package adminShop.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OraUserDao implements UserDao{
    public boolean judgeQRCode(String qr){
        PreparedStatement st = null;
        boolean judge = false;
        ResultSet rs = null;
        Connection cn = null;
        ShopAdminOracleConnecter oc = new ShopAdminOracleConnecter();
        try{
            cn = oc.getConnection();
            String sql="select count(*) from USER_INFORMATION_TABLE where QRCODE = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,qr);
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
}