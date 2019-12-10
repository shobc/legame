import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import Bean.UserBean;
import DB.TimeLineLikeChangeDB;


public class LikeServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String id = req.getParameter("timeline_id");
        String likeJudge = req.getParameter("likeJudge");
        String user_id = ub.getUser_id();
        System.out.println("id="+id);
        System.out.println("likeJudge="+likeJudge);
        System.out.println("user_id="+user_id);
        if(likeJudge.equals("0")){
            TimeLineLikeChangeDB lcb = new TimeLineLikeChangeDB(id,user_id);
            lcb.insertLikeRecord();
        }else{
            TimeLineLikeChangeDB lcb = new TimeLineLikeChangeDB(id,user_id);
            lcb.deleteLikeRecord();
        }
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }

}