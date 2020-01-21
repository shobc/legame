import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.ProfileDao;
import bean.UserBean;

public class ProfilePageServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String user_id = req.getParameter("id");
        System.out.println("user_id="+user_id);

        UserBean ub = new UserBean();
        ub.setUser_id(user_id);

        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ProfileDao dao = factory.getOraProfileDao();

        ub = dao.getProfile(ub);

        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        req.setAttribute("ub",ub);

        RequestDispatcher dis = req.getRequestDispatcher("profile");
        dis.forward(req,res);
    }
}