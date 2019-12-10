import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import DB.ProfileSearchDB;
import DB.ChatSearchListDB;
import Bean.UserBean;

public class ChatPageServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        ChatSearchListDB chatsearchlistdb = new ChatSearchListDB();
        ArrayList chatList = chatsearchlistdb.searchChat(user_id);
        req.setAttribute("chatList",chatList);

        RequestDispatcher dis = req.getRequestDispatcher("chatList");
        dis.forward(req,res);
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
