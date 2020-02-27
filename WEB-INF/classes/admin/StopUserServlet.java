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
import admin.function.CutURL;

@WebServlet("/admin/StopUserServlet")
public class StopUserServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String user_id = req.getParameter("user_id");
        AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
        UserDao dao = factory.getUserDao();
        dao.stopUserAccount(user_id);
        res.sendRedirect(CutURL.getPath(req.getHeader("REFERER")));
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}