package dao;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class OracleConnectionManager{
    private static OracleConnectionManager oracon = null;
    private Connection cn = null;
    private OracleConnectionManager(){}
    public static OracleConnectionManager getInstance(){
        if(oracon==null){
            oracon = new OracleConnectionManager();
        }
        return oracon;
    }
    public Connection getConnection(){
        if(cn == null){
            try{
                Class.forName("oracle.jdbc.driver.OracleDriver");
                cn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","learn","learn");
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
    public void beginTransaction(){
        if(cn == null){
            getConnection();
        }
        try{
            cn.setAutoCommit(false);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void commit(){
        try{
            cn.commit();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void rollback(){
        try{
            cn.rollback();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}