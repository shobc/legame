import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.DeleteFriendDB;
import Bean.UserBean;

public class DeleteFriendServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        String friend_id = req.getParameter("friend_id");
        UserBean ub = (UserBean) session.getAttribute("ub");
        String user_id = ub.getUser_id();
        DeleteFriendDB deletefrienddb = new DeleteFriendDB();
        deletefrienddb.friendDelete(user_id,friend_id);
        res.sendRedirect("BlockUserListServlet");
    }
}