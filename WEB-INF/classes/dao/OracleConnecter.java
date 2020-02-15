package dao;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class OracleConnecter {
    private Connection cn = null;
//    private OracleConnecter(){}
    //Connection���擾����
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
    //Connection�����
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
    //�g�����U�N�V�������J�n������
//    public void beginTransaction(){
//        if(cn == null){
//            getConnection();
//        }
//        try{
//            cn.setAutoCommit(false);
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
//    }
    //Connection���N���[�Y������
    public void commit(){
        try{
            cn.commit();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    //Connection�����[���o�b�N������
    public void rollback(){
        try{
            cn.rollback();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}