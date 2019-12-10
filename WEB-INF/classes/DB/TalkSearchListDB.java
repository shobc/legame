package DB;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Bean.TalkBean;
import DB.selectSentence.SelectPutTogether;
import DB.function.AcquisitionImage;

public class TalkSearchListDB{
    public static ArrayList<TalkBean> searchTalkList(String chat_id){
        ArrayList<TalkBean> talkArray = new ArrayList<TalkBean>();
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("ê⁄ë±äÆóπ");

            String sql = "select t.chat_id,t.user_id,t.content,u.TOP_PICTURE,u.nickname,u.top_picture,t.mess_time " +
                    "from talk_table t left join user_information_table u on t.user_id = u.user_id " +
                    "where t.chat_id = '"+chat_id+"'";

            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();

            while(rs.next()){
                String Pchat_id = rs.getString(1);
                String user_id = rs.getString(2);
                String content = rs.getString(3);
                Blob blob = rs.getBlob(4);
                String name = rs.getString(5);
                Blob blobTop_Picture = rs.getBlob(6);
                String mess_time = rs.getString(7);
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String contentPicture = acquisitionImage.getImagePath(user_id,Pchat_id,blob);
                String top_picture = acquisitionImage.getImagePath(user_id,Pchat_id+name,blobTop_Picture);

                TalkBean tb = new TalkBean();
                tb.setUser_id(user_id);
                tb.setName(name);
                tb.setContent(content);
                tb.setImage(contentPicture);
                tb.setTop_picture(top_picture);
                tb.setMess_time(mess_time);
                talkArray.add(tb);

            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLÇÃó·äO");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ëSÇƒÇèEÇ§ó·äO");
        }
        return talkArray;
    }
}