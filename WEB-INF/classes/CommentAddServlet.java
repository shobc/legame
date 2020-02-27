import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import function.EscapeString;
import dao.AbstractDaoFactory;
import dao.CommentDao;
import dao.TimeLineDao;
import bean.UserBean;
import bean.CommentBean;
import exception.DeleteTimeLineException;

@WebServlet("/CommentAddServlet")
public class CommentAddServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String comment = EscapeString.escape(req.getParameter("comment"));
        String timeline_id = req.getParameter("timeline_id");
        String reply_user_id = req.getParameter("reply_user_id");
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TimeLineDao Tdao = factory.getOraTimeLineDao();
        if(Tdao.deleteJudgeTimeline(timeline_id)){
            throw new DeleteTimeLineException("指定のタイムラインは削除されました");
        }
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        CommentBean cb = new CommentBean();
        cb.setUser_id(user_id);
        cb.setComment_sentence(comment);
        cb.setTimeline_id(timeline_id);
        cb.setReply_user_id(reply_user_id);
        CommentDao dao = factory.getOraCommentDao();
        dao.addComment(cb);
//        res.sendRedirect("CommentSearchServlet?timeline_id="+timeline_id);
        RequestDispatcher dis = req.getRequestDispatcher("CommentSearchServlet?timeline_id="+timeline_id);
        dis.forward(req,res);
    }
}