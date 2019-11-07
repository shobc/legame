package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import Bean.ChatBean;

import DB.selectSentence.FriendIdSelect;
import DB.function.AcquisitionImage;

public class ChatSearchDB{
    public static UserBean searchChat(String my_user_id){
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("接続完了");
            FriendIdSelect friendidselect = new FriendIdSelect(cn,my_user_id);
            ResultSet rs = friendidselect.getRs();

            while(rs.next()){

                String search_id = rs.getString(1);
                String nickname = rs.getString(2);
                String single_word = rs.getString(3);
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String top_picture = acquisitionImage.getImagePath(rs);
                String user_id = rs.getString(5);
                ChatBean cb = new ChatBean();

            }



        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLの例外");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("全てを拾う例外");
        }
        return ub;
    }
}