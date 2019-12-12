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
import dao.TalkDao;
import bean.UserBean;
import bean.TalkBean;

public class RegisterTalkServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String chat_id = req.getParameter("chat_id");
        String content = req.getParameter("content");
//        Part part = req.getPart("contentPicture");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        TalkBean tb = new TalkBean();
        tb.setChat_id(chat_id);
        tb.setUser_id(ub.getUser_id());
        tb.setContent(content);
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TalkDao dao = factory.getOraTalkDao();

        dao.addTalk(tb);

        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        res.sendRedirect("TalkPageServlet?chat_id="+chat_id);
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
