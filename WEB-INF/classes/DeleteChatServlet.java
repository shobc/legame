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
import dao.ChatDao;

import bean.UserBean;

import javax.servlet.annotation.WebServlet;
@WebServlet("/DeleteChatServlet")
public class DeleteChatServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String chat_id = req.getParameter("chat_id");

        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ChatDao dao = factory.getOraChatDao();

        dao.deleteChat(chat_id);

        res.sendRedirect("TalkListPageServlet");
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
