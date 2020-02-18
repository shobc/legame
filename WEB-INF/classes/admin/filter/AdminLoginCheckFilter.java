package admin.filter;

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

import admin.dao.AdminAbstractDaoFactory;
import admin.dao.AdminUserDao;
import admin.bean.AdminUserBean;

public class AdminLoginCheckFilter  extends HttpServlet implements Filter{
    public void init(FilterConfig config)throws ServletException{}
    public void destroy(){}
    public void doFilter(ServletRequest req,ServletResponse res, FilterChain chain)throws IOException,ServletException{
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        HttpSession session = ((HttpServletRequest)req).getSession();
        if(mail!=null&&pass!=null&&name!=null){
            AdminUserBean aub = new AdminUserBean();
            aub.setUser_name(name);
            aub.setMail(mail);
            aub.setPassword(pass);
            System.out.println("name"+name);
            System.out.println("mail"+mail);
            System.out.println("pass"+pass);
            AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
            AdminUserDao dao = factory.getAdminUserDao();
            if(dao.getAdminUserJudge(aub)){
                aub = dao.getAdminUser(aub);
                session.setAttribute("adminToken","OK");
                session.setAttribute("aub",aub);
            }else{
//                —áŠO‚ª“ü‚é—\’è
            }
        }
        chain.doFilter(req,res);
    }
}