import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.ProfileDao;
import bean.UserBean;


public class ChangeNameServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String name = req.getParameter("name");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        ub.setName(name);

        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ProfileDao dao = factory.getOraProfileDao();

        dao.updateNameProfile(ub);

        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();
        res.sendRedirect("profilesetting");
    }
}