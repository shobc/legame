package adminShop.dao;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class ShopAdminOracleConnecter {
    private Connection cn = null;
    public Connection getConnection(){
        if(cn == null){
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");
                cn.setAutoCommit(false);
            }catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }catch(SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return cn;
    }
    //Connectionを閉じる
    public void closeConnection(){
        try{
            if(cn !=null){
                cn.close();
                cn =null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //Connectionをクローズさせる
    public void commit(){
        try{
            cn.commit();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    //Connectionをロールバックさせる
    public void rollback(){
        try{
            cn.rollback();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}