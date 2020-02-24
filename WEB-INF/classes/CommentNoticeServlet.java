import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import bean.UserBean;
import bean.TimeLineBean;
import bean.TimeLinePictureBean;
import dao.AbstractDaoFactory;
import dao.TimeLineDao;

@WebServlet("/CommentNoticeServlet")
public class CommentNoticeServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();

        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TimeLineDao dao = factory.getOraTimeLineDao();

        dao.updateCommentNotice(user_id);
        ArrayList commentNotice = dao.getCommentNotice(user_id);

        req.setAttribute("commentNotice",commentNotice);
        RequestDispatcher dis = req.getRequestDispatcher("comment-notice");
        dis.forward(req,res);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doGet(req,res);
    }
}