import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import Bean.UserBean;
import DB.FriendSearchDB;

public class FriendSearchServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String id = req.getParameter("id");

        FriendSearchDB friendsearchdb = new FriendSearchDB();
        UserBean ub = friendsearchdb.searchFriendId(id);
        req.setAttribute("ub",ub);
        RequestDispatcher dis = req.getRequestDispatcher("/friendSearch");
        dis.forward(req,res);
    }
}