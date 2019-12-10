import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DB.RegisterAcountDB;
import DB.LoginUserIdDB;
import Bean.LoginUserBean;

public class AuthAccountServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String RandomCode = req.getParameter("RandomCode");
        ServletContext sc = getServletContext();
        LoginUserBean lb = (LoginUserBean)sc.getAttribute(RandomCode);
        if(RandomCode.equals(lb.getRandomCode())){
            RegisterAcountDB.registerAcount(lb.getMail(),lb.getPass());
            String user_id = LoginUserIdDB.loginUserId(lb.getMail(),lb.getPass());
            HttpSession session = req.getSession();
            session.setAttribute("user_id",user_id);
            sc.removeAttribute(RandomCode);
        }else{

        }
        RequestDispatcher dis = req.getRequestDispatcher("registerProfile");
        dis.forward(req,res);
    }
}