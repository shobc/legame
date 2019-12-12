package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.ProfileDao;
import bean.UserBean;

public class LoginCheckFilter  extends HttpServlet implements Filter{
    public void init(FilterConfig config)throws ServletException{}
    public void destroy(){}
    public void doFilter(ServletRequest req,ServletResponse res, FilterChain chain)throws IOException,ServletException{
        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        HttpSession session = ((HttpServletRequest)req).getSession();
        if(mail!=null&&pass!=null){
            OracleConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
            ProfileDao dao = factory.getOraProfileDao();

            UserBean ub = dao.getProfile(mail,pass);

            OracleConnectionManager.getInstance().commit();
            OracleConnectionManager.getInstance().closeConnection();

            System.out.println("ub="+ub);
            System.out.println("ub.getUser_id()"+ub.getUser_id());
            if(ub.getUser_id()!=null){
                session.setAttribute("token","OK");
                session.setAttribute("ub",ub);
                session.setAttribute("user_id",ub.getUser_id());
                System.out.println(session.getId());
            }
        }
        chain.doFilter(req,res);
    }
}