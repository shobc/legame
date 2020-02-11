package ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import bean.FriendBean;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.FriendDao;


public class AjaxFriendReleaseServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String chat_id = req.getParameter("chat_id");
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FriendDao dao = factory.getOraFriendDao();
        dao.releaseFriend(chat_id);
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}