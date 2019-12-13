import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.FriendDao;
import bean.UserBean;
import bean.FriendBean;

public class FriendAddServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String friend_id = req.getParameter("friend_id");
        HttpSession session = req.getSession();
        UserBean ub =(UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        FriendBean fb = new FriendBean();
        fb.setUser_id(user_id);
        fb.setFriend_id(friend_id);
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FriendDao dao = factory.getOraFriendDao();

        dao.addFriend(fb);

        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        res.sendRedirect("NewFriendListServlet");

    }
}