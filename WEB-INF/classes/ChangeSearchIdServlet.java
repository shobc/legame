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


public class ChangeSearchIdServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String search_id = req.getParameter("search_id");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        ub.setSearch_id(search_id);

        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ProfileDao dao = factory.getOraProfileDao();

        dao.updateSearchIdProfile(ub);

        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();
        res.sendRedirect("profile-setting");
    }
}