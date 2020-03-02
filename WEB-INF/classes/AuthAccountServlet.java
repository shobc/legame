import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import bean.LoginUserBean;
import dao.UserDao;
import dao.AbstractDaoFactory;
import exception.NoMatchRandomException;

@WebServlet("/AuthAccountServlet")
public class AuthAccountServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String RandomCode = req.getParameter("RandomCode");
        ServletContext sc = getServletContext();
        LoginUserBean lb = (LoginUserBean)sc.getAttribute(RandomCode);
        if(RandomCode.equals("")||lb==null){
            throw new NoMatchRandomException("ŠY“–‚µ‚È‚¢•¶Žš—ñ‚ðŽó‚¯•t‚¯‚Ü‚µ‚½");
        }
        String random = lb.getRandomCode();
        if(!RandomCode.equals(random)){
            throw new NoMatchRandomException("ŠY“–‚µ‚È‚¢•¶Žš—ñ‚ðŽó‚¯•t‚¯‚Ü‚µ‚½");
        }
        System.out.println("OK");
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao dao = factory.getOraUserDao();
        dao.RegisterUser(lb);
        String user_id = dao.getUserId(lb);
        HttpSession session = req.getSession();
        session.setAttribute("user_id",user_id);
        sc.removeAttribute(RandomCode);
        RequestDispatcher dis = req.getRequestDispatcher("register-profile");
        dis.forward(req,res);
    }
}