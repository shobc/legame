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

@WebServlet("/shopAdmin/ChangePassWordServlet")
public class ChangePassWordServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        String shop_admin_user_id = req.getParameter("shop_admin_user_id");
        String password = req.getParameter("password");
        ShopAdminUserBean saub = new ShopAdminUserBean();
        ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
        ShopAdminUserDao dao = factory.getShopAdminUserDao();
        saub.setShop_admin_user_id(shop_admin_user_id);
        saub.setPassword(password);
        dao.changePassWord(saub);
        req.setAttribute("message","パスワードを変更しました");
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/confirm.jsp");
        dis.forward(req,res);
    }
}