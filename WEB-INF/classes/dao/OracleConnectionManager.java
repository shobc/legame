package dao;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

//Connection�𕡐��̃f�[�^�x�[�X�A�N�Z�X���ɂ����������Ȃ�������N���X
public class OracleConnectionManager{
    //�N���X�ϐ��Ƃ��ĕێ������邱�Ƃɂ��g���܂킵������
    private static OracleConnectionManager oracon = null;
    private Connection cn = null;
    //Singleton�p�^�[�����������邱�Ƃɂ��new�������Ȃ�
    private OracleConnectionManager(){}
    //�N���X���\�b�h�Ŏ��g�̃N���X��new������
    public static OracleConnectionManager getInstance(){
        if(oracon==null){
            oracon = new OracleConnectionManager();
        }
        return oracon;
    }
    //Connection���擾����
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