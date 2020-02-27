package adminShop.filter;

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

import adminShop.dao.ShopAdminAbstractDaoFactory;
import adminShop.dao.ShopAdminUserDao;
import adminShop.bean.ShopAdminUserBean;

public class ShopAdminLoginCheckFilter  extends HttpServlet implements Filter{
    public void init(FilterConfig config)throws ServletException{}
    public void destroy(){}
    public void doFilter(ServletRequest req,ServletResponse res, FilterChain chain)throws IOException,ServletException{
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        String pass = req.getParameter("pass");
        HttpSession session = ((HttpServletRequest)req).getSession();
        if(mail!=null&&pass!=null&&name!=null){
            ShopAdminUserBean saub = new ShopAdminUserBean();
            saub.setUser_name(name);
            saub.setMail(mail);
            saub.setPassword(pass);
            System.out.println("name"+name);
            System.out.println("mail"+mail);
            System.out.println("pass"+pass);
            ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
            ShopAdminUserDao dao = factory.getShopAdminUserDao();
            if(dao.getApprovalShopAdminUserJudge(saub)){
//                まだお店でPayを使える許可を持っていないから例外
            }
            if(dao.getShopAdminUserJudge(saub)){
                saub = dao.getShopAdminUser(saub);
                session.setAttribute("adminShopToken","OK");
                session.setAttribute("saub",saub);
            }else{
//                ユーザーが登録されていない
            }
        }
        chain.doFilter(req,res);
    }
}