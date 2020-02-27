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
import adminShop.bean.PropertyBean;

@WebServlet("/shopAdmin/DateSortHistoryServlet")
public class DateSortHistoryServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String year = req.getParameter("year");
        String mon = req.getParameter("mon");
        if(mon.length()==1){
            mon ="0"+mon;
        }
        String date = year+"/"+mon;
        ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
        ShopAdminPropertyDao dao = factory.getShopAdminPropertyDao();
        HttpSession session = req.getSession();
        ShopAdminUserBean saub = (ShopAdminUserBean)session.getAttribute("saub");
        PropertyBean pb = new PropertyBean();
        pb.setShop_admin_user_id(saub.getShop_admin_user_id());
        pb.setDate(date);
        ArrayList propertyList = dao.getDateSort(pb);
        req.setAttribute("caption",date+" çwì¸ÉäÉXÉg");
        req.setAttribute("propertyList",propertyList);
        req.setAttribute("uri","DateSortHistoryServlet");
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/home.jsp");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
