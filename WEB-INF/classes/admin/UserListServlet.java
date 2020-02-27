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
import admin.dao.UserDao;

@WebServlet("/admin/UserListServlet")
public class UserListServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
        UserDao dao = factory.getUserDao();
        ArrayList userList = dao.getUserList();
        req.setAttribute("userList",userList);
        RequestDispatcher dis = req.getRequestDispatcher("/admin/user");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}