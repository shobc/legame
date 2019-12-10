import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import Bean.UserBean;
import DB.FriendSearchDB;

public class FriendSearchServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String id = req.getParameter("id");
        HttpSession session = req.getSession();
        UserBean ub =(UserBean)session.getAttribute("ub");
        if(id.equals(ub.getSearch_id())){
            System.out.println("if���̒��ɓ���܂���");
            res.sendRedirect("NewFriendListServlet");
        }else{
            FriendSearchDB friendsearchdb = new FriendSearchDB();
            System.out.println("�����Ŏ擾�����l="+id);
            ub = friendsearchdb.searchFriendId(ub.getUser_id(),id);
            req.setAttribute("ub",ub);
            RequestDispatcher dis = req.getRequestDispatcher("/NewFriendListServlet");
            dis.forward(req,res);
        }
    }
}