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

import exception.NoRegisterAccountException;
import exception.StopAccountException;
import dao.AbstractDaoFactory;
import dao.ProfileDao;
import dao.UserDao;
import bean.UserBean;
import bean.LoginUserBean;

public class LoginCheckFilter  extends HttpServlet implements Filter{
    public void init(FilterConfig config)throws ServletException{}
    public void destroy(){}
    public void doFilter(ServletRequest req,ServletResponse res, FilterChain chain)throws IOException,ServletException{
        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        if(mail!=null&&pass!=null){
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
            UserDao Udao = factory.getOraUserDao();
            LoginUserBean lub = new LoginUserBean();
            lub.setMail(mail);
            lub.setPass(pass);
            if(Udao.judgeUserAccount(lub)){
                throw new NoRegisterAccountException("メールアドレスまたはパスワードが違います");
            }else if(Udao.judgeStopUserAccount(lub)){
                throw new StopAccountException("アカウントが停止されています");
            }else{
                ProfileDao Pdao = factory.getOraProfileDao();
                UserBean ub = Pdao.getProfile(mail,pass);
                if(ub==null){
                    String user_id = Udao.getUserId(lub);
                    HttpServletRequest hreq = (HttpServletRequest)req;
                    hreq.setAttribute("user_id",user_id);
                    RequestDispatcher dis = req.getRequestDispatcher("register-profile");
                    dis.forward(req,res);
                }
                HttpSession session = ((HttpServletRequest)req).getSession();
                session.setAttribute("token","OK");
                session.setAttribute("ub",ub);
                session.setAttribute("user_id",ub.getUser_id());
            }
        }
        chain.doFilter(req,res);
    }
}