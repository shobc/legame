package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import DB.updateSentence.QRCodeUpdate;

public class UpdateQRCodeDB{

    public static int updateQRCode(String user_id,String randomString){
        int count = 0;
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            cn.setAutoCommit(false);
            System.out.println("�ڑ�����");
            QRCodeUpdate qrcodeupdate = new QRCodeUpdate(cn,user_id,randomString);
            count=qrcodeupdate.getRegisterCount();

            System.out.println(count+"����������܂���");

            cn.commit();

            cn.close();

        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return count;
    }
}
