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

@WebServlet("/admin/ShopApprovalListServlet")
public class ShopApprovalListServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
        ShopAdminUserDao dao = factory.getShopAdminUserDao();
        ArrayList shopList = dao.getShopAdminApprovalList();

        req.setAttribute("shopList",shopList);
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/admin/approval-shop-list.jsp");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}