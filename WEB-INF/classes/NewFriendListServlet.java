import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import DB.NewFrinedListDB;
import Bean.UserBean;

public class NewFriendListServlet extends HttpServlet{
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        NewFrinedListDB newfriendlistdb = new NewFrinedListDB();
        ArrayList newFriendList = newfriendlistdb.newFrinedList(ub.getUser_id());
        req.setAttribute("newFrinedList",newFriendList);
        RequestDispatcher dis = req.getRequestDispatcher("friendSearch");
        dis.forward(req,res);
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doGet(req,res);
    }
}
