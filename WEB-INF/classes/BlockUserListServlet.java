import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import dao.AbstractDaoFactory;
import dao.FriendDao;
import bean.UserBean;

@WebServlet("/BlockUserListServlet")
public class BlockUserListServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");
        String user_id = ub.getUser_id();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FriendDao dao = factory.getOraFriendDao();
        ArrayList blockList =dao.getBlockFriend(user_id);
        req.setAttribute("blocklist",blockList);
        RequestDispatcher dis = req.getRequestDispatcher("block-list");
        dis.forward(req,res);
    }
}

