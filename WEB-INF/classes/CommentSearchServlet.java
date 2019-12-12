import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import bean.TimeLineBean;
import bean.CommentBean;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.TimeLineDao;
import dao.CommentDao;

public class CommentSearchServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String timeline_id = req.getParameter("timeline_id");
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TimeLineDao timelineDao = factory.getOraTimeLineDao();
        CommentDao commentDao = factory.getOraCommentDao();
        TimeLineBean tlb =  timelineDao.getTimeLine(timeline_id);
        ArrayList commentList = commentDao.getComment(timeline_id);
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        req.setAttribute("tlb",tlb);
        req.setAttribute("commentArray",commentList);
        RequestDispatcher dis = req.getRequestDispatcher("comment");
        dis.forward(req,res);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}