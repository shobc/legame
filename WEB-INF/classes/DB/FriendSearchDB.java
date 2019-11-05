package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import Bean.UserBean;
import DB.selectSentence.FriendIdSelect;
import DB.function.AcquisitionImage;

public class FriendSearchDB{
    public static UserBean searchFriendId(String id){
        UserBean ub = new UserBean();
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("ê⁄ë±äÆóπ");
            FriendIdSelect friendidselect = new FriendIdSelect(cn,id);
            ResultSet rs = friendidselect.getRs();

            rs.next();

            String search_id = rs.getString(1);
            String nickname = rs.getString(2);
            String single_word = rs.getString(3);
            AcquisitionImage acquisitionImage = new AcquisitionImage();
            String top_picture = acquisitionImage.getImagePath(rs);
            String user_id = rs.getString(5);

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