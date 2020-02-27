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

@WebServlet("/shopAdmin/ShopAdminHomeServlet")
public class ShopAdminHomeServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
        ShopAdminPropertyDao dao = factory.getShopAdminPropertyDao();
        HttpSession session = req.getSession();
        ShopAdminUserBean saub = (ShopAdminUserBean)session.getAttribute("saub");
        ArrayList propertyList = dao.getMyShopHistory(saub.getShop_admin_user_id());
        req.setAttribute("caption","çwì¸ÉäÉXÉg");
        req.setAttribute("reverseMoney","ï‘ã‡");
        req.setAttribute("uri","DateSortHistoryServlet");
        req.setAttribute("propertyList",propertyList);
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/home.jsp");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
