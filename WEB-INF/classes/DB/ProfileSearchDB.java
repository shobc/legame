package DB;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import bean.UserBean;
import DB.selectSentence.SelectPutTogether;
import DB.function.AcquisitionImage;

public class ProfileSearchDB{
    public static UserBean searchProfile(String id){
        UserBean ub = new UserBean();
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("ê⁄ë±äÆóπ");

            String sql = "select search_id,nickname,single_word,top_picture,user_id" +
                    " from user_information_table where user_id = '" +id+"'";

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
            System.out.println("ProfileSearchDBÇÃuser_id="+user_id);

            ub.setSearch_id(search_id);
            ub.setName(nickname);
            ub.setSingle_word(single_word);
            ub.setTop_picture(top_picture);
            ub.setUser_id(user_id);

        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("SQLÇÃó·äO");
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("ëSÇƒÇèEÇ§ó·äO");
        }
        return ub;
    }
}