import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import dao.AbstractDaoFactory;
import dao.UserDao;
import bean.LoginUserBean;
import function.RandomString;
import function.SendMail;
import function.EscapeString;
import exception.NotMailException;

@WebServlet("/ReminderPassServlet")
public class ReminderPassServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        ServletContext  sc = getServletContext();
        req.setCharacterEncoding("windows-31j");
        String mail = EscapeString.escape(req.getParameter("mail"));
        String RString = RandomString.getString();
        String url = "http://localhost:8080/legame/GetValueServlet?RandomCode="+RString;
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao dao = factory.getOraUserDao();
        if(!dao.judgeSufferMail(mail)){
            throw new NotMailException("メールが登録されてません");
        }
        SendMail sm = new SendMail();
        String message = "URLにアクセスしてパスワード変更してください";
        sm.send(mail,message,url);

        LoginUserBean lub = new LoginUserBean();
        lub.setMail(mail);
        lub.setRandomCode(RString);
        sc.setAttribute(RString,lub);
        RequestDispatcher dis = req.getRequestDispatcher("mail-confirmation");
        dis.forward(req,res);
    }
}