package DB;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import Bean.UserBean;
import DB.selectSentence.SelectPutTogether;
import DB.function.AcquisitionImage;

public class FriendSearchDB{
    public static UserBean searchFriendId(String my_user_id,String id){
        UserBean ub = new UserBean();
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("ê⁄ë±äÆóπ");

            String sql = "select search_id,nickname,single_word,top_picture,user_id from " +
                    "user_information_table where user_id not in(select friend_id from friend_table where user_id = "+my_user_id+" and delete_flag = 0 ) " +
                    "and search_id = '"+id+"'";

            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();

            rs.next();
            String search_id = rs.getString(1);
            String nickname = rs.getString(2);
            String single_word = rs.getString(3);
            Blob blob = rs.getBlob(4);
            String user_id = rs.getString(5);
            AcquisitionImage acquisitionImage = new AcquisitionImage();
            String top_picture = acquisitionImage.getImagePath(user_id,search_id,blob);

            System.out.println("search_id"+search_id);
            System.out.println("nickname"+nickname);
            System.out.println("single_word"+single_word);
            System.out.println("top_picture"+top_picture);
            System.out.println("user_id"+user_id);
            ub.setSearch_id(search_id);
            ub.setName(nickname);
            ub.setSingle_word(single_word);
            ub.setTop_picture(top_picture);
            ub.setUser_id(user_id);

        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("SQLÇÃó·äO");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ëSÇƒÇèEÇ§ó·äO");
        }
        return ub;
    }
}