package adminShop;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import function.EscapeString;
import adminShop.dao.ShopAdminAbstractDaoFactory;
import adminShop.dao.ShopAdminUserDao;
import adminShop.bean.ShopAdminUserBean;

@WebServlet("/shopAdmin/AuthAccountServlet")
public class AuthAccountServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        ServletContext  sc = getServletContext();
        String RandomCode = req.getParameter("RandomCode");
        ShopAdminUserBean saub = (ShopAdminUserBean)sc.getAttribute(RandomCode);
        ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
        ShopAdminUserDao dao = factory.getShopAdminUserDao();
        String shop_admin_user_id = dao.getUser_id(saub.getMail());
        req.setAttribute("shop_admin_user_id",shop_admin_user_id);
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/change-password.jsp");
        dis.forward(req,res);
    }
}