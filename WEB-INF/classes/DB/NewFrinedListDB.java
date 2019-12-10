package DB;

import java.util.ArrayList;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import Bean.UserBean;
import DB.function.AcquisitionImage;
import DB.selectSentence.SelectPutTogether;

public class NewFrinedListDB{
    public static ArrayList newFrinedList(String my_user_id){
        ArrayList<UserBean> newfriendlist = new ArrayList<UserBean>();
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("ê⁄ë±äÆóπ");

            String sql ="select u.search_id,u.nickname,u.single_word,u.top_picture,u.user_id from user_information_table u left join friend_table f " +
                    "on u.user_id = f.user_id and u.user_id IN(select user_id from friend_table where friend_id ='"+my_user_id+"') and f.friend_id = '"+my_user_id+"' " +
                    "where f.user_id NOT IN(select friend_id from friend_table where user_id='"+my_user_id+"') and f.friend_flag= 0 and delete_flag = 0";

            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();

            while (rs.next()){
                String search_id = rs.getString(1);
                String nickname = rs.getString(2);
                String single_word = rs.getString(3);
                Blob blob = rs.getBlob(4);
                String user_id = rs.getString(5);
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String top_picture = acquisitionImage.getImagePath(user_id,search_id,blob);
                UserBean ub = new UserBean();
                ub.setSearch_id(search_id);
                ub.setName(nickname);
                ub.setSingle_word(single_word);
                ub.setTop_picture(top_picture);
                ub.setUser_id(user_id);
                newfriendlist.add(ub);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQLÇÃó·äO");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ëSÇƒÇèEÇ§ó·äO");
        }
        return newfriendlist;
    }
}