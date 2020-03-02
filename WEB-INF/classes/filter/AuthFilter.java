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

import dao.AbstractDaoFactory;
import dao.UserDao;
import bean.UserBean;
import exception.LoginUserStopAccountException;

public class AuthFilter  extends HttpServlet implements Filter{
    public void init(FilterConfig config)throws ServletException{}
    public void destroy(){}
    public void doFilter(ServletRequest req,ServletResponse res, FilterChain chain)throws IOException,ServletException{
        HttpSession session = ((HttpServletRequest)req).getSession();
        String flag = (String)session.getAttribute("token");
//        if(){
//          アカウントが停止している場合例外を飛ばす（URLを指定してsessionを切らす）
//        }
        if(flag==null){
            HttpServletRequest hreq = (HttpServletRequest)req;
            RequestDispatcher dis = req.getRequestDispatcher("/login");
            dis.forward(req,res);
        }else{
            UserBean ub =(UserBean)session.getAttribute("ub");
            String user_id = ub.getUser_id();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
            UserDao dao = factory.getOraUserDao();
            if(dao.judgeStopUserAccount(user_id)){
                throw new LoginUserStopAccountException("アカウントが停止されました");
            }else{
                chain.doFilter(req,res);
            }
        }
    }
}