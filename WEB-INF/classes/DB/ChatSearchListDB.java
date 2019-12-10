package DB;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Bean.ChatBean;
import DB.selectSentence.SelectPutTogether;
import DB.function.AcquisitionImage;

public class ChatSearchListDB{
    public static ArrayList<ChatBean> searchChat(String my_user_id){
        ArrayList<ChatBean> ChatArray = new ArrayList<ChatBean>();
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("ê⁄ë±äÆóπ");

            String sql = "select u.search_id,u.user_id,u.nickname,u.top_picture,c.chat_id " +
                    "from user_information_table u left join chat_table c " +
                    "on u.user_id = c.user_chat1_id and c.user_chat_id = '"+my_user_id+"'" +
                    " or u.user_id = c.user_chat_id and c.user_chat1_id = '"+my_user_id+"' " +
                    "where c.user_chat_id = '"+my_user_id+"' or c.user_chat1_id = '"+my_user_id+"'";

            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();

            while(rs.next()){
                String search_id = rs.getString(1);
                String user_id = rs.getString(2);
                String name = rs.getString(3);
                Blob blob = rs.getBlob(4);
                String chat_id = rs.getString(5);
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String top_picture = acquisitionImage.getImagePath(user_id,search_id,blob);
                ChatBean cb = new ChatBean();
                cb.setUser_id(user_id);
                cb.setName(name);
                cb.setTop_picture(top_picture);
                cb.setChat_id(chat_id);
                ChatArray.add(cb);

            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLÇÃó·äO");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ëSÇƒÇèEÇ§ó·äO");
        }
        return ChatArray;
    }
}