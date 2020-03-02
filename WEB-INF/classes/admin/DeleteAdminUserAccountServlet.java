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

@WebServlet("/admin/DeleteAdminUserAccountServlet")
public class DeleteAdminUserAccountServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String shop_admin_user_id = req.getParameter("shop_admin_user_id");
        AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
        ShopAdminUserDao dao = factory.getShopAdminUserDao();
        dao.deleteShopAdmin(shop_admin_user_id);
        res.sendRedirect("ShopApprovalListServlet");
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}