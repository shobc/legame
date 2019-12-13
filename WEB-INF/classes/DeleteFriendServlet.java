import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.FriendDao;
import bean.UserBean;
import bean.FriendBean;

public class DeleteFriendServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        String friend_id = req.getParameter("friend_id");
        UserBean ub = (UserBean) session.getAttribute("ub");
        String user_id = ub.getUser_id();
        FriendBean fb = new FriendBean();
        fb.setUser_id(user_id);
        fb.setFriend_id(friend_id);
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FriendDao dao = factory.getOraFriendDao();

        dao.deleteFriend(fb);

        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        res.sendRedirect("BlockUserListServlet");
    }
}