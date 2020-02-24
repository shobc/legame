package ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import bean.UserBean;
import bean.ChatBean;
import dao.AbstractDaoFactory;
import dao.ChatDao;

@WebServlet("/AjaxReportFriendServlet")
public class AjaxReportFriendServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        ChatBean cb = new ChatBean();
        cb.setUser_id(ub.getUser_id());
        cb.setFriend_id(req.getParameter("friend_id"));
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ChatDao dao = factory.getOraChatDao();
        dao.reportFriend(cb);
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}