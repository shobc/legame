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
import dao.ChatDao;
import bean.UserBean;
import bean.ChatBean;


@WebServlet("/RegisterChatServlet")
public class RegisterChatServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String friend_id = req.getParameter("friend_id");
        HttpSession session = req.getSession();
        UserBean ub= (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        ChatBean cb = new ChatBean();
        cb.setUser_id(friend_id);
        cb.setFriend_id(user_id);
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ChatDao dao = factory.getOraChatDao();
        cb.setUser_id(user_id);
        cb.setFriend_id(friend_id);
        if(dao.getJudge(cb)){
            dao.addChat(cb);
        }
        String chat_id = dao.getChatId(cb);
        res.sendRedirect("TalkPageServlet?chat_id="+chat_id);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException {
        String friend_id = req.getParameter("friend_id");
        HttpSession session = req.getSession();
        UserBean ub= (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        ChatBean cb = new ChatBean();
        cb.setUser_id(friend_id);
        cb.setFriend_id(user_id);
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ChatDao dao = factory.getOraChatDao();
        cb.setUser_id(user_id);
        cb.setFriend_id(friend_id);
        if(dao.getJudge(cb)){
            dao.addChat(cb);
        }
        String chat_id = dao.getChatId(cb);
        RequestDispatcher dis = req.getRequestDispatcher("TalkPageServlet?chat_id="+chat_id);
        dis.forward(req,res);
    }
}