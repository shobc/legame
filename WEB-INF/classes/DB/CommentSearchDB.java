package DB;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Blob;

import DB.function.AcquisitionImage;
import Bean.CommentListBean;
import DB.selectSentence.SelectPutTogether;

public class CommentSearchDB{
    public ArrayList getCommentList(String timeline_id){
        ArrayList commentArray = new ArrayList();
        try{
            Connection cn = OracleConnector.getConnection("learn","learn");
            System.out.println("ê⁄ë±äÆóπ");

            String sql= "select u.USER_ID,u.NICKNAME,u.TOP_PICTURE,c.comment_sentence,comment_time from USER_INFORMATION_TABLE u left join COMMENT_TABLE c " +
                    "on u.USER_ID = c.USER_ID " +
                    "where c.TIMELINE_ID = '"+timeline_id+"' ";

            SelectPutTogether spt = new SelectPutTogether(cn,sql);
            ResultSet rs = spt.getRs();

            while(rs.next()){
                String user_id = rs.getString(1);
                String name = rs.getString(2);
                Blob blob = rs.getBlob(3);
                String comment_sentence = rs.getString(4);
                String comment_time = rs.getString(5);
                AcquisitionImage acquisitionImage = new AcquisitionImage();
                String top_picture = acquisitionImage.getImagePath(user_id,name,blob);
                CommentListBean clb = new CommentListBean();
                clb.setUser_id(user_id);
                clb.setName(name);
                clb.setTop_picture(top_picture);
                clb.setComment_sentence(comment_sentence);
                clb.setComment_time(comment_time);
                commentArray.add(clb);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQLÇÃó·äO");
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ëSÇƒÇèEÇ§ó·äO");
        }
        return commentArray;
    }
}