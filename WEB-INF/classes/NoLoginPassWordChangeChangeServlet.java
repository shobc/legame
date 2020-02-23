import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

import bean.LoginUserBean;
import dao.UserDao;
import dao.AbstractDaoFactory;
import function.EscapeString;

public class NoLoginPassWordChangeChangeServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        ServletContext sc = getServletContext();
        String pass = EscapeString.escape(req.getParameter("pass"));
        String Random = req.getParameter("Random");

        LoginUserBean lub = (LoginUserBean)sc.getAttribute(Random);
        lub.setPass(pass);

        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao dao = factory.getOraUserDao();
        dao.UpdateUserPassWord(lub);

        sc.removeAttribute(Random);

        req.setAttribute("message","パスワード変更されました");
        RequestDispatcher dis = req.getRequestDispatcher("confirm");
        dis.forward(req,res);
    }
}
