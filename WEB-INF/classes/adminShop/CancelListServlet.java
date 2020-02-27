package adminShop;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import adminShop.dao.ShopAdminAbstractDaoFactory;
import adminShop.dao.ShopAdminPropertyDao;
import adminShop.bean.ShopAdminUserBean;

@WebServlet("/shopAdmin/CancelListServlet")
public class CancelListServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
        ShopAdminPropertyDao dao = factory.getShopAdminPropertyDao();
        HttpSession session = req.getSession();
        ShopAdminUserBean saub = (ShopAdminUserBean)session.getAttribute("saub");
        ArrayList propertyList = dao.getCancelList(saub.getShop_admin_user_id());
        req.setAttribute("caption","•Ô‹àƒŠƒXƒg");
        req.setAttribute("uri","DateSortCancelServlet");
        req.setAttribute("propertyList",propertyList);
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/home.jsp");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
