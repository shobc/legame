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

public class AdminAuthFilter extends HttpServlet implements Filter{
    public void init(FilterConfig config)throws ServletException{}
    public void destroy(){}
    public void doFilter(ServletRequest req,ServletResponse res, FilterChain chain)throws IOException,ServletException{
        HttpSession session = ((HttpServletRequest)req).getSession();
        String flag = (String)session.getAttribute("adminToken");
        if(flag==null){
            RequestDispatcher dis = req.getRequestDispatcher("/admin/login");
            dis.forward(req,res);
        }else{
            chain.doFilter(req,res);
        }
    }
}