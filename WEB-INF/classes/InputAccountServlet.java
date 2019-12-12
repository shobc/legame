import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import function.PathHolder;
import function.RandomString;
import function.SendMail;
import bean.LoginUserBean;

public class InputAccountServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        PathHolder.pathName = getServletContext().getRealPath("/");
        ServletContext  sc = getServletContext();
        req.setCharacterEncoding("windows-31j");
        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        String pass1 = req.getParameter("pass1");

        if(!pass.equals(pass1)){
            req.setAttribute("error","パスワードが一致してません");
            RequestDispatcher dis = req.getRequestDispatcher("registerAccount");
            dis.forward(req,res);
        }else{
            String RString = RandomString.getString();
            System.out.println("RString="+RString);
            LoginUserBean lub = new LoginUserBean();
            lub.setMail(mail);
            lub.setPass(pass);
            lub.setRandomCode(RString);
            sc.setAttribute(RString,lub);
            String url = "http://localhost:8080/legame/AuthAccountServlet?RandomCode="+RString;
            SendMail sendmail = new SendMail();
            sendmail.send(mail,url);
            res.sendRedirect("mailConfirmation");
        }
    }
}