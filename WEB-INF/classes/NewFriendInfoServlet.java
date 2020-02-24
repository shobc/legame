import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import dao.AbstractDaoFactory;
import dao.FriendDao;
import bean.FriendBean;

@WebServlet("/NewFriendInfoServlet")
public class NewFriendInfoServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String friend_id = req.getParameter("friend_id");
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FriendDao dao = factory.getOraFriendDao();
        FriendBean fb = dao.getFriendInfo(friend_id);
        req.setAttribute("fb",fb);
        RequestDispatcher dis = req.getRequestDispatcher("no-friend-profile");
        dis.forward(req,res);
    }
}