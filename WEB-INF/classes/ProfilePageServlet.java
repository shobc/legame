import java.io.IOException;
import java.util.ArrayList;

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

//profile画面に必要なサーブレット
public class ProfilePageServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        System.out.println("user_id="+user_id);

        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FriendDao dao = factory.getOraFriendDao();

        ArrayList friendList =dao.getFriend(user_id);

        String noticeCount = dao.getNewFriendCount(user_id);

        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        req.setAttribute("friendList",friendList);
        req.setAttribute("noticeCount",noticeCount);
        //URLを指定する
        RequestDispatcher dis = req.getRequestDispatcher("profile");
        dis.forward(req,res);
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
