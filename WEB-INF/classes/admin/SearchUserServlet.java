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


@WebServlet("/admin/SearchUserServlet")
public class SearchUserServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String search_id = req.getParameter("search_id");
        AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
        UserDao dao = factory.getUserDao();
        ArrayList userList = dao.searchUser(search_id);
        req.setAttribute("userList",userList);
        RequestDispatcher dis = req.getRequestDispatcher("user");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}