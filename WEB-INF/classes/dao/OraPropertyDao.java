package dao;

import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import bean.PropertyBean;
import bean.BalanceBean;

//お金関連用のDaoクラス
public class OraPropertyDao implements PropertyDao{
//お金をチャージする
    public void addPropery(PropertyBean p){
        PreparedStatement st = null;
        try{
            Connection cn = null;
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="insert into property_table(user_id,money,history) values(?,?,'チャージしました')";
            st = cn.prepareStatement(sql);
            st.setString(1,p.getUser_id());
            st.setInt(2,p.getMoney());
            int count = st.executeUpdate();
            System.out.println(count+"件処理しました");
        }catch(SQLException e){
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
//お金を使う
    public void employMoney(PropertyBean p){
        PreparedStatement st = null;
        try{
            Connection cn = null;
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="insert into PROPERTY_TABLE(USER_ID,money,point,HISTORY) " +
                    "select (select user_id from USER_INFORMATION_TABLE where QRCODE = ?) ,-?,?,? " +
                    "from PROPERTY_TABLE where USER_ID = (select user_id from USER_INFORMATION_TABLE where QRCODE = ?) " +
                    "group by USER_ID having SUM(MONEY)>=?";
            st = cn.prepareStatement(sql);
            st.setString(1,p.getRandomString());
            st.setInt(2,p.getMoney());
            st.setInt(3,p.getMoney()/100);
            st.setString(4,p.getHistory());
            st.setString(5,p.getRandomString());
            st.setInt(6,p.getMoney());
            st.executeUpdate();
        }catch(SQLException e){
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

//合計残高と合計ポイントを取得する
    public BalanceBean getBalanceProperty(String id){
        PreparedStatement st = null;
        ResultSet rs = null;
        BalanceBean bb = new BalanceBean();
        try{
            Connection cn = null;
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select sum(money),sum(point) from property_table where user_id = ? ";
            st = cn.prepareStatement(sql);
            st.setString(1,id);
            rs = st.executeQuery();
            rs.next();
            bb.setMoneyTotal(rs.getInt(1));
            bb.setPointTotal(rs.getInt(2));


        }catch(SQLException e){
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
        return bb;
    }
//ユーザーのIDにあたる履歴を取得する
    public List getAllProperty(String id){
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList propertyList = new ArrayList();
        try{
            Connection cn = null;
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql = "select money, point, history, history_date from PROPERTY_TABLE where user_id = ? order by history_date desc";
            st = cn.prepareStatement(sql);
            st.setString(1,id);
            rs = st.executeQuery();
            while(rs.next()){
                PropertyBean pb = new PropertyBean();
                pb.setMoney(rs.getInt(1));
                pb.setPoint(rs.getInt(2));
                pb.setHistory(rs.getString(3));
                pb.setHistorydate(rs.getString(4));
                propertyList.add(pb);
            }
        }catch(SQLException e){
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
        return propertyList;
    }
//QRの文字列を書き換える
    public void updateQRCode(String user_id,String randomString){
        PreparedStatement st = null;
        Connection cn = null;
        try{
            cn = OracleConnectionManager.getInstance().getConnection();
            String sql="update user_information_table set qrcode= ? where user_id = ?";
            st = cn.prepareStatement(sql);
            st.setString(1,randomString);
            st.setString(2,user_id);
            st.executeUpdate();
        }catch(SQLException e){
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