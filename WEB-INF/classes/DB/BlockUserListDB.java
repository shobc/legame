package DB;

import java.util.ArrayList;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import Bean.UserBean;
import DB.selectSentence.SelectPutTogether;
import DB.function.AcquisitionImage;

public class BlockUserListDB{
    ArrayList<UserBean> blocklist = new ArrayList<UserBean>();
    public ArrayList getBlockUserList(String my_user_id){
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("ê⁄ë±äÆóπ");

            String sql = "select u.nickname,u.user_id,u.top_picture from user_information_table u left join friend_table f " +
                    "on u.user_id = f.friend_id and f.friend_id IN(select friend_id from friend_table where user_id = '"+my_user_id+"') " +
                    "where f.USER_ID = '"+my_user_id+"' and f.friend_flag = 1 and delete_flag = 0";

            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();
            while(rs.next()){
                String name = rs.getString(1);
                String user_id = rs.getString(2);
                Blob blob = rs.getBlob(3);
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String top_picture = acquisitionImage.getImagePath(user_id,name,blob);
                System.out.println("name="+name);
                System.out.println("user_id="+user_id);
                System.out.println("top_picture="+top_picture);
                UserBean ub = new UserBean();
                ub.setName(name);
                ub.setUser_id(user_id);
                ub.setTop_picture(top_picture);
                blocklist.add(ub);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQLÇÃó·äO");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ëSÇƒÇèEÇ§ó·äO");
        }
        return blocklist;
    }
}