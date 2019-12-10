import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import Bean.UserBean;
import DB.TimeLineCreateDB;

public class CreateTimeLineServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        String timeline_sentence = req.getParameter("timeline_sentence");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        TimeLineCreateDB timelinecreatedb = new TimeLineCreateDB();
        timelinecreatedb.createTimeline(user_id,timeline_sentence);

        res.sendRedirect("TimeLineServlet");
    }
}