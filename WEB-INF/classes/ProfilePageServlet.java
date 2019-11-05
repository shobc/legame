import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import DB.ProfileSearchDB;
import DB.LoginUserIdDB;
import Bean.UserBean;


public class ProfilePageServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        ProfileSearchDB psd = new ProfileSearchDB();
        UserBean ub = psd.searchProfile(Integer.parseInt((String)session.getAttribute("user_id")));
        session.setAttribute("token","OK");
        session.setAttribute("ub",ub);
        RequestDispatcher dis = req.getRequestDispatcher("profile");
        dis.forward(req,res);
    }
}