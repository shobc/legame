import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import Bean.TimeLineBean;
import DB.CommentSearchDB;
import DB.TimeLineSearchOneDB;

public class CommentSearchServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String timeline_id = req.getParameter("timeline_id");
        TimeLineSearchOneDB timelinesearchonedb = new TimeLineSearchOneDB();
        TimeLineBean tlb = timelinesearchonedb.getTimeLine(timeline_id);

        CommentSearchDB commentsearchdb = new CommentSearchDB();
        ArrayList commentArray = commentsearchdb.getCommentList(timeline_id);

        req.setAttribute("tlb",tlb);
        req.setAttribute("commentArray",commentArray);
        RequestDispatcher dis = req.getRequestDispatcher("comment");
        dis.forward(req,res);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}