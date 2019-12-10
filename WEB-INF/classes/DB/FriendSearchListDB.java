package DB;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import bean.FriendBean;
import DB.selectSentence.SelectPutTogether;
import DB.function.AcquisitionImage;

import java.util.ArrayList;

//友達リストを取得するためのクラス
public class FriendSearchListDB{
    public ArrayList<FriendBean> searchFriendProfile(String my_user_id){
        //JSPでリストを表示するためのArrayList
        ArrayList<FriendBean> array = new ArrayList<FriendBean>();
        try{
            //データベースにつなぐためのクラス
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("接続完了");

            String sql = "select u.nickname,u.single_word,u.top_picture,u.user_id from user_information_table u " +
                    "left join friend_table f on u.user_id = f.friend_id and f.friend_flag = 0 and f.delete_flag = 0" +
                    "where f.user_id = '"+my_user_id+"' ";

            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();

            while(rs.next()){
                String nickname = rs.getString(1);
                String single_word = rs.getString(2);
                Blob blob = rs.getBlob(3);
                String user_id = rs.getString(4);
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String top_picture = acquisitionImage.getImagePath(user_id,nickname,blob);
                FriendBean fb = new FriendBean();
                fb.setUser_id(user_id);
                fb.setName(nickname);
                fb.setSingle_word(single_word);
                fb.setTop_picture(top_picture);
                array.add(fb);
            }

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLの例外");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("全てを拾う例外");
        }
        return array;
    }
}