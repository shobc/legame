package adminShop;

import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import adminShop.dao.ShopAdminAbstractDaoFactory;
import adminShop.dao.ShopAdminPropertyDao;
import adminShop.bean.ShopAdminUserBean;
import adminShop.bean.PropertyBean;

@WebServlet("/shopAdmin/InputMoneyServlet")
public class InputMoneyServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        HttpSession session = req.getSession();
        ShopAdminUserBean saub = (ShopAdminUserBean)session.getAttribute("saub");
        PropertyBean pb = new PropertyBean();
        int pay = Integer.parseInt(req.getParameter("money"));
        String RandomString = req.getParameter("RandomString");

        ServletContext  sc = getServletContext();

        pb.setUser_id(String.valueOf(sc.getAttribute(RandomString)));
        pb.setShop_admin_user_id(saub.getShop_admin_user_id());
        pb.setMoney(pay);

        ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
        ShopAdminPropertyDao dao = factory.getShopAdminPropertyDao();
        dao.addPropery(pb);

        sc.removeAttribute(RandomString);
        req.setAttribute("title","“ü‹àŠ®—¹");
        req.setAttribute("caption","“ü‹à‚ªŠ®—¹‚µ‚Ü‚µ‚½");
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/clear.jsp");
        dis.forward(req,res);
    }
}