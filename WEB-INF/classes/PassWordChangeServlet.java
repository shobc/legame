import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import dao.UserDao;
import dao.AbstractDaoFactory;

public class PassWordChangeServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String old_pass = req.getParameter("old_pass");
        String new_pass = req.getParameter("new_pass");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao dao = factory.getOraUserDao();

        if(dao.searchPassWord(old_pass,user_id)){
            req.setAttribute("error","前回のパスワードと一致しません");
        }else{
            dao.UpdateUserPassWord(new_pass,user_id);
            req.setAttribute("OK","パスワードが変更されました");
        }
        RequestDispatcher dis = req.getRequestDispatcher("change-password");
        dis.forward(req,res);
    }
}