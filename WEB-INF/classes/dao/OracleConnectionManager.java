package dao;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

//Connectionを複数のデータベースアクセス時にいちいち作らなくさせるクラス
public class OracleConnectionManager{
    //クラス変数として保持させることにより使いまわしがきく
    private static OracleConnectionManager oracon = null;
    private Connection cn = null;
    //Singletonパターンを実装することによりnewをさせない
    private OracleConnectionManager(){}
    //クラスメソッドで自身のクラスをnewさせる
    public static OracleConnectionManager getInstance(){
        if(oracon==null){
            oracon = new OracleConnectionManager();
        }
        return oracon;
    }
    //Connectionを取得する
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
    //トランザクションを開始させる
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