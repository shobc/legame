import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.BlockUserDB;
import Bean.UserBean;

public class BlockUserServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String user_id = req.getParameter("user_id");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String my_user_id = ub.getUser_id();
        BlockUserDB blockuserdb = new BlockUserDB();
        blockuserdb.userBlock(user_id,my_user_id);
        res.sendRedirect("profilePage");
    }
}
