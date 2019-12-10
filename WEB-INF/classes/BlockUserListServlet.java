import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DB.BlockUserListDB;
import Bean.UserBean;

public class BlockUserListServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        UserBean ub = (UserBean) session.getAttribute("ub");
        String user_id = ub.getUser_id();
        BlockUserListDB blockuserlistdb = new BlockUserListDB();
        ArrayList blocklist = blockuserlistdb.getBlockUserList(user_id);
        req.setAttribute("blocklist",blocklist);
        RequestDispatcher dis = req.getRequestDispatcher("blocklist");
        dis.forward(req,res);
    }
}

