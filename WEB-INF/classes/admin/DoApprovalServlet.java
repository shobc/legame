package admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import admin.dao.AdminAbstractDaoFactory;
import admin.dao.ShopAdminUserDao;
import admin.bean.ShopAdminUserBean;
import function.SendMail;

@WebServlet("/admin/DoApprovalServlet")
public class DoApprovalServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String shop_admin_user_id = req.getParameter("shop_admin_user_id");
        String mail = req.getParameter("mail");
        AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
        ShopAdminUserDao dao = factory.getShopAdminUserDao();
        dao.addShopAdminApproval(shop_admin_user_id);
        SendMail sm = new SendMail();
        sm.send(mail,"Ç®ìXÇÃãñâ¬Ç™â∫ÇËÇ‹ÇµÇΩÉçÉOÉCÉìâ¬î\Ç≈Ç∑","http://localhost:8080/legame/shopAdmin/login");
        res.sendRedirect("ShopApprovalListServlet");
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}