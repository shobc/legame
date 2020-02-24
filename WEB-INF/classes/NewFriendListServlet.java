import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import dao.AbstractDaoFactory;
import dao.FriendDao;
import bean.UserBean;

@WebServlet("/NewFriendListServlet")
public class NewFriendListServlet extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FriendDao dao = factory.getOraFriendDao();
        ArrayList newFriendList =dao.getNewFriend(ub.getUser_id());
        req.setAttribute("newFrinedList",newFriendList);
        RequestDispatcher dis = req.getRequestDispatcher("search-friend");
        dis.forward(req,res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doGet(req,res);
    }
}
