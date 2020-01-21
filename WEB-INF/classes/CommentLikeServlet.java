import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import bean.CommentBean;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.CommentDao;

public class CommentLikeServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String timeline_id = req.getParameter("timeline_id");
        String comment_id = req.getParameter("comment_id");
        String likeJudge = req.getParameter("likeJudge");
        String user_id = ub.getUser_id();
        CommentBean cb = new CommentBean();
        cb.setUser_id(user_id);
        cb.setTimeline_id(timeline_id);
        cb.setComment_id(comment_id);
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        CommentDao dao = factory.getOraCommentDao();
        if(likeJudge.equals("0")){
            dao.addCommentLike(cb);
        }else{
            dao.deleteCommentLike(cb);
        }
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }

}