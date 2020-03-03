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

@WebServlet("/admin/DeleteAdminUserAccountServlet")
public class DeleteAdminUserAccountServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String shop_admin_user_id = req.getParameter("shop_admin_user_id");
        String mail = req.getParameter("mail");
        AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
        ShopAdminUserDao dao = factory.getShopAdminUserDao();
        dao.deleteShopAdmin(shop_admin_user_id);
        SendMail sm = new SendMail();
        sm.send(mail,"Ç®ìXÇÃãñâ¬Ç™Ç®ÇËÇ‹ÇπÇÒÇ≈ÇµÇΩê\ÇµñÛÇ≤Ç¥Ç¢Ç‹ÇπÇÒ","");
        res.sendRedirect("ShopApprovalListServlet");
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}