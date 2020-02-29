package adminShop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import adminShop.dao.ShopAdminAbstractDaoFactory;
import adminShop.dao.ShopAdminPropertyDao;
import adminShop.bean.ShopAdminUserBean;
import adminShop.bean.PropertyBean;

@WebServlet("/shopAdmin/SettlementServlet")
public class SettlementServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        HttpSession session = req.getSession();
        ShopAdminUserBean saub = (ShopAdminUserBean)session.getAttribute("saub");
        String history = req.getParameter("history");
        String price = req.getParameter("price");
        String randomString = req.getParameter("qrcode");

        PropertyBean pb = new PropertyBean();
        pb.setShop_admin_user_id(saub.getShop_admin_user_id());
        pb.setHistory(history);
        pb.setMoney(Integer.parseInt(price));
        pb.setRandomString(randomString);

        ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
        ShopAdminPropertyDao dao = factory.getShopAdminPropertyDao();
        dao.employMoney(pb);

        req.setAttribute("title","åàçœäÆóπ");
        req.setAttribute("caption","éxï•Ç¢Ç™äÆóπÇµÇ‹ÇµÇΩ");
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/clear.jsp");
        dis.forward(req,res);
    }
}