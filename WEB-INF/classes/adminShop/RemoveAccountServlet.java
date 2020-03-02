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

import adminShop.dao.ShopAdminAbstractDaoFactory;
import adminShop.dao.ShopAdminUserDao;
import adminShop.bean.ShopAdminUserBean;

@WebServlet("/shopAdmin/RemoveAccountServlet")
public class RemoveAccountServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        HttpSession session = req.getSession();
        ShopAdminUserBean saub = (ShopAdminUserBean)session.getAttribute("saub");
        ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
        ShopAdminUserDao dao = factory.getShopAdminUserDao();
        dao.removeAccount(saub.getShop_admin_user_id());
        req.setAttribute("message","アカウント削除しました");
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/confirm.jsp");
        dis.forward(req,res);
    }
}