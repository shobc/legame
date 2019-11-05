package DB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DB.insertSentence.RegisterProfileInsert;


public class RegisterProfileDB{
    private Connection cn = null;
    private PreparedStatement st = null;
    public void RegisterProfile(String user_id,String name,String id,String comment,String filePath) {
        try {
            cn = OracleConnector.getConnection("learn", "learn");
            cn.setAutoCommit(false);
            st = RegisterProfileInsert.insertProfile(cn,user_id,name,id,comment,filePath);
            st.executeUpdate();
            System.out.println("�f�[�^�x�[�X�ɓo�^����");
            st.close();
            System.out.println("Commit�����O");
            cn.commit();
            System.out.println("Commit����܂���");
        } catch (SQLException e) {
        } catch (Exception e) {
        }
    }
}