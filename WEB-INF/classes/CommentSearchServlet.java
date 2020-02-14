import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import bean.TimeLineBean;
import bean.CommentBean;
import bean.UserBean;
import bean.TimeLinePictureBean;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.TimeLineDao;
import dao.CommentDao;

public class CommentSearchServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String timeline_id = req.getParameter("timeline_id");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        TimeLineBean tlb = new TimeLineBean();
        tlb.setUser_id(user_id);
        tlb.setTimeline_id(timeline_id);
        CommentBean cb = new CommentBean();
        cb.setTimeline_id(timeline_id);
        cb.setUser_id(user_id);
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TimeLineDao timelineDao = factory.getOraTimeLineDao();
        CommentDao commentDao = factory.getOraCommentDao();
        tlb =  timelineDao.getTimeLine(tlb);
        ArrayList list =  timelineDao.getOneTimelinePicture(timeline_id);
        Iterator itr = list.iterator();
        while(itr.hasNext()){
            TimeLinePictureBean tlpb = (TimeLinePictureBean)itr.next();
            tlb.add(tlpb);
        }
        ArrayList commentList = commentDao.getComment(cb);
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        req.setAttribute("tlb",tlb);
        req.setAttribute("commentArray",commentList);
        RequestDispatcher dis = req.getRequestDispatcher("comment");
        dis.forward(req,res);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
       this.doGet(req,res);
    }
}