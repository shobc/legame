import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import DB.CommentAddDB;
import Bean.UserBean;

public class CommentAddServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String comment = req.getParameter("comment");
        String timeline_id = req.getParameter("timeline_id");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        CommentAddDB commentadddb = new CommentAddDB();
        commentadddb.commentAdd(user_id,timeline_id,comment);
        res.sendRedirect("CommentSearchServlet?timeline_id="+timeline_id);
    }
}