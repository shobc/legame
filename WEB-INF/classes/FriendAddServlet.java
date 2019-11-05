import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import Bean.UserBean;
import DB.FriendAddDB;

public class FriendAddServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String friend_id = req.getParameter("friend_id");
        HttpSession session = req.getSession();
        UserBean ub =(UserBean)session.getAttribute("ub");
        System.out.println("servlet‚Å‚ÌUserBean="+ub);
        System.out.println("getSearch_id="+ub.getSearch_id());
        System.out.println("getUser_id="+ub.getUser_id());
        String user_id = ub.getUser_id();
        System.out.println("Bean‚©‚çŽæ“¾‚µ‚½user_id"+user_id);

        FriendAddDB friendadddb = new FriendAddDB();
        friendadddb.friendAdd(user_id,friend_id);
        RequestDispatcher dis = req.getRequestDispatcher("/friendSearch");
        dis.forward(req,res);
    }
}