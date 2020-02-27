package adminShop;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;

import adminShop.dao.ShopAdminAbstractDaoFactory;
import adminShop.dao.ShopAdminPropertyDao;
import adminShop.bean.ShopAdminUserBean;

@WebServlet("/shopAdmin/CancelItemServlet")
public class CancelItemServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String property_id = req.getParameter("property_id");
        ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
        ShopAdminPropertyDao dao = factory.getShopAdminPropertyDao();
        dao.cancelItem(property_id);
        dao.cancelAdd(property_id);
        res.sendRedirect("ShopAdminHomeServlet");
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
