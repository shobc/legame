import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import bean.LoginUserBean;
import dao.OracleConnectionManager;
import dao.UserDao;
import dao.AbstractDaoFactory;

public class PassChangServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        ServletContext sc = getServletContext();
        String pass = req.getParameter("pass");
        String Random = req.getParameter("Random");

        OracleConnectionManager.getInstance().beginTransaction();
        LoginUserBean lub = (LoginUserBean)sc.getAttribute(Random);
        lub.setPass(pass);

        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        UserDao dao = factory.getOraUserDao();
        dao.UpdateUserPassWord(lub);
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        sc.removeAttribute(Random);
        RequestDispatcher dis = req.getRequestDispatcher("loginPage");
        dis.forward(req,res);
    }
}
