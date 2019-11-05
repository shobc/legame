import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DB.RegisterAcountDB;
import DB.LoginUserIdDB;
import function.PathHolder;

public class RegisterAccountServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        PathHolder.pathName = getServletContext().getRealPath("/");
        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        String pass1 = req.getParameter("pass1");

        if(!pass.equals(pass1)){
            req.setAttribute("error","パスワードが一致してません");
            RequestDispatcher dis = req.getRequestDispatcher("registerAccount");
            dis.forward(req,res);
        }else{
            int count = RegisterAcountDB.registerAcount(mail,pass);
            int user_id = LoginUserIdDB.loginUserId(mail,pass);
            HttpSession session = req.getSession();
            session.setAttribute("user_id",user_id);
            session.setAttribute("token","OK");
            RequestDispatcher dis = req.getRequestDispatcher("registerProfile");
            dis.forward(req,res);
        }
    }
}