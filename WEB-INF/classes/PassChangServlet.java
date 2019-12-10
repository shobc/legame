import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import Bean.LoginUserBean;
import DB.UpdatePasswordDB;

public class PassChangServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        ServletContext sc = getServletContext();
        String pass = req.getParameter("pass");
        String Random = req.getParameter("Random");
        LoginUserBean lub = (LoginUserBean)sc.getAttribute(Random);
        UpdatePasswordDB upd = new  UpdatePasswordDB();
        upd.updatePassword(pass,lub.getMail());
        sc.removeAttribute(Random);
        RequestDispatcher dis = req.getRequestDispatcher("loginPage");
        dis.forward(req,res);
    }
}
