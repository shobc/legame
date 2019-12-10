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

import DB.ProfileSearchDB;
import DB.LoginUserIdDB;
import bean.UserBean;

public class LoginCheckFilter  extends HttpServlet implements Filter{
    public void init(FilterConfig config)throws ServletException{}
    public void destroy(){}
    public void doFilter(ServletRequest req,ServletResponse res, FilterChain chain)throws IOException,ServletException{
        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        HttpSession session = ((HttpServletRequest)req).getSession();
        if(mail!=null&&pass!=null){
            ProfileSearchDB psd = new ProfileSearchDB();
            UserBean ub = psd.searchProfile(LoginUserIdDB.loginUserId(mail,pass));
            session.setAttribute("token","OK");
            session.setAttribute("ub",ub);
        }
        chain.doFilter(req,res);
    }
}